package com.reliablesystems.doctoroffice.core.service.company;

import com.reliablesystems.doctoroffice.core.domain.Company;
import com.reliablesystems.doctoroffice.core.to.company.CompanyTO;

import java.util.List;

public interface CompanyService {
    List<CompanyTO> findAllCompanies();

    Long createCompany(Company company);
}
