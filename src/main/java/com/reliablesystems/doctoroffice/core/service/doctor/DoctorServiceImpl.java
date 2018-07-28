package com.reliablesystems.doctoroffice.core.service.doctor;

import com.reliablesystems.doctoroffice.core.dao.doctor.DoctorDAO;
import com.reliablesystems.doctoroffice.core.domain.Doctor;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.DoctorRepository;
import com.reliablesystems.doctoroffice.core.repository.LocationDataRepository;
import com.reliablesystems.doctoroffice.core.repository.PersonalDataRepository;
import com.reliablesystems.doctoroffice.core.service.company.CompanyService;
import com.reliablesystems.doctoroffice.core.service.user.UserService;
import com.reliablesystems.doctoroffice.core.to.doctor.DoctorTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import com.reliablesystems.doctoroffice.core.utils.doctor.DoctorUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private LocationDataRepository locationDataRepository;
    @Autowired
    private DoctorDAO doctorDAO;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyService companyService;

    /**
     * Method to find a doctor by id
     * @param id Doctor id
     * @return Doctor
     */
    @Override
    public Doctor findDoctorById(long id) {
        return doctorRepository.findOne(id);
    }

    /**
     * Method to find all doctors of the system
     *
     * @param companyId Company that doctor is
     * @param ofset Search start
     * @param limit Search limit
     * @param search Word
     * @return List of {@link DoctorTO}
     */
    @Override
    public List<DoctorTO> findDoctorsByCompany(long companyId, int ofset, int limit, String search) {
        return doctorDAO.findDoctorsByCompany(companyId, ofset, limit, search);
    }

    /**
     * Method to get total of doctors in the systems
     *
     * @param companyId Company that doctor is
     * @param search Word to search
     * @return Number of elements
     */
    @Override
    public int findDoctorsCountByCompany(long companyId, String search) {
        return doctorDAO.findDoctorsCountByCompany(companyId, search);
    }

    /**
     * Method to create a doctor
     *
     * @param doctor Doctor data
     * @param userName User name of a doctor
     * @param password Password of a doctor
     * @return Id of new doctor
     */
    @Override
    public Long createDoctor(Doctor doctor, String userName, String password) {
        // Validate if user exist
        if (userService.findUserById(doctor.getUser().getId()) == null) {
            throw new BackEndException("User Not Found");
        }
        // Validate if company exist
        if(companyService.findCompanyById(doctor.getCompany().getId()) == null) {
            throw new BackEndException("Company Not Found");
        }
        // Save personal data
        personalDataRepository.save(doctor.getPersonalData());
        // Save location data
        locationDataRepository.save(doctor.getLocationData());
        // Save doctor
        doctor.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        doctor.setCreatedAt(DateUtil.nowTimestamp());
        // Create a user doctor
        doctor.setUser(userService.createDoctorUser(doctor, userName, password));
        doctorRepository.save(doctor);

        return doctor.getId();
    }

    /**
     * Method to update a doctor
     *
     * @param doctorTO Doctor data
     */
    @Override
    public void updateDoctor(DoctorTO doctorTO) {
        // Find doctor by id
        Doctor doctor = findDoctorById(doctorTO.getId());
        // Validate doctor
        if (doctor == null) {
            throw new BackEndException("Doctor Not Found");
        }
        // Override doctor data
        DoctorUtil.updateDoctorData(doctorTO, doctor);
        // Save personal data
        personalDataRepository.save(doctor.getPersonalData());
        // Save location data
        locationDataRepository.save(doctor.getLocationData());
        // Save doctor
        doctorRepository.save(doctor);
    }

    /**
     * Method to inactive doctor by id
     *
     * @param id doctor id
     */
    @Override
    public void inactiveDoctor(long id) {
        // Find doctor by id
        Doctor doctor = findDoctorById(id);
        // Validate doctor
        if (doctor == null) {
            throw new BackEndException("Doctor Not Found");
        }
        doctor.setStatus(new Status(StatusKeys.INACTIVE_STATUS));
        doctorRepository.save(doctor);
    }

    /**
     * Method to update profile picture path
     *
     * @param id Id doctor
     * @param path New path of profile picture
     */
    @Override
    public void updateProfilePicturePath(long id, String path) {
        // Find doctor by id
        Doctor doctor = findDoctorById(id);
        // Validate doctor
        if (doctor == null) {
            throw new BackEndException("Doctor Not Found");
        }
        doctor.setProfileImage(path);
        doctorRepository.save(doctor);
    }
}
