package com.reliablesystems.doctoroffice.core.service.user;

import com.reliablesystems.doctoroffice.core.domain.Role;
import com.reliablesystems.doctoroffice.core.domain.User;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.UserRepository;
import com.reliablesystems.doctoroffice.core.utils.common.NumberUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
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
}
