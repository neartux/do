package com.reliablesystems.doctoroffice.core.service.user;

import com.reliablesystems.doctoroffice.core.domain.*;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.LocationDataRepository;
import com.reliablesystems.doctoroffice.core.repository.PersonalDataRepository;
import com.reliablesystems.doctoroffice.core.repository.UserRepository;
import com.reliablesystems.doctoroffice.core.utils.common.*;
import com.reliablesystems.doctoroffice.core.utils.user.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private LocationDataRepository locationDataRepository;

    /**
     * Method to find a user by id
     * @param id Id user
     * @return User
     */
    @Override
    public User findUserById(long id) {
        return userRepository.findOne(id);
    }

    /**
     * Method to find user by username and status is active
     *
     * @param userName User name
     * @return User
     */
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserNameAndStatusId(userName, StatusKeys.ACTIVE_STATUS);
    }

    /**
     * Method to verify if an user exist by userName
     *
     * @param userName User name to verify
     * @return Boolean with the result
     */
    @Override
    public boolean existUserByUserName(String userName) {
        Long numElem = userRepository.countByUserName(userName);
        return numElem > NumberUtil.ZERO_LONG;
    }

    /**
     * Find roles by userid
     *
     * @param userId Id of user
     * @return List of roles
     */
    @Override
    public List<Role> findRolesByUserId(long userId) {
        // Find user by id
        User user = userRepository.findOne(userId);
        // Validate user
        if (user == null) {
            throw new BackEndException("User Not Found");
        }
        // Validate if user has roles
        if (user.getRoleList() != null && user.getRoleList().size() > NumberUtil.ZERO_INT) {
            return user.getRoleList();
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Method to create a user for a doctor
     *
     * @param doctor Doctor data
     * @param userName Username of doctor
     * @param password Password of doctor
     * @return New user
     */
    @Override
    public User createDoctorUser(Doctor doctor, String userName, String password) {
        // Verify if is valid username
        if (existUserByUserName(userName)){
            throw new BackEndException("El nombre de usuario ya existe en el sistema");
        }
        User user = new User();
        user.setPersonalData(UserUtil.getNewPersonalData(doctor.getPersonalData()));
        user.setLocationData(UserUtil.getNewLocationData(doctor.getLocationData()));
        // Save personal data
        personalDataRepository.save(user.getPersonalData());
        // Save location data
        locationDataRepository.save(user.getLocationData());
        // Save user
        user.setCreatedAt(DateUtil.nowTimestamp());
        user.setCompany(doctor.getCompany());
        user.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        user.setUserName(userName);
        user.setPassword(UserUtil.digestMD5(password));
        // Add roles
        user.setRoleList(new ArrayList<Role>());
        user.getRoleList().add(new Role(ApplicationKeys.ROLE_GENERIC_ID));
        user.getRoleList().add(new Role(ApplicationKeys.ROLE_DOCTOR_ID));

        userRepository.save(user);

        // TODO in a future send a email with credentials

        return user;
    }

    /**
     * Method to create a new user for a new company
     *
     * @param company Company data
     */
    @Override
    public Long createAdminUserForCompany(Company company) {
        User user = new User();
        user.setPersonalData(UserUtil.getNewPersonalData(company.getPersonalData()));
        user.setLocationData(UserUtil.getNewLocationData(company.getLocationData()));
        // Save personal data
        personalDataRepository.save(user.getPersonalData());
        // Save location data
        locationDataRepository.save(user.getLocationData());
        // Save user
        user.setCreatedAt(DateUtil.nowTimestamp());
        user.setCompany(company);
        user.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        user.setUserName(company.getDescription() + StringUtil.DOT + company.getId());
        user.setPassword(UserUtil.digestMD5("demo"));
        // Add roles
        user.getRoleList().add(new Role(ApplicationKeys.ROLE_GENERIC_ID));
        user.getRoleList().add(new Role(ApplicationKeys.ROLE_ADMINISTRATOR_ID));

        userRepository.save(user);

        // TODO in a future send a email with credentials

        return user.getId();
    }


}
