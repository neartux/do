package com.reliablesystems.doctoroffice.core.service.user;

import com.reliablesystems.doctoroffice.core.domain.Company;
import com.reliablesystems.doctoroffice.core.domain.Role;
import com.reliablesystems.doctoroffice.core.domain.User;

import java.util.List;

public interface UserService {
    User findUserByUserName(String userName);

    List<Role> findRolesByUserId(long userId);

    Long createAdminUserForCompany(Company company);
}
