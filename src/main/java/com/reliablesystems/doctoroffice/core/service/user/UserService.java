package com.reliablesystems.doctoroffice.core.service.user;

import com.reliablesystems.doctoroffice.core.domain.Company;
import com.reliablesystems.doctoroffice.core.domain.Doctor;
import com.reliablesystems.doctoroffice.core.domain.Role;
import com.reliablesystems.doctoroffice.core.domain.User;

import java.util.List;

public interface UserService {
    User findUserById(long id);

    User findUserByUserName(String userName);

    boolean existUserByUserName(String userName);

    List<Role> findRolesByUserId(long userId);

    User createDoctorUser(Doctor doctor, String userName, String password);

    Long createAdminUserForCompany(Company company);
}
