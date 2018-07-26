package com.reliablesystems.doctoroffice.core.bridge;

import com.reliablesystems.doctoroffice.core.service.company.CompanyService;
import com.reliablesystems.doctoroffice.core.service.user.UserService;

public interface BusinessSpringContextBridgeService {
    UserService getUserService();

    CompanyService getCompanyService();
}
