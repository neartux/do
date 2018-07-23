package com.reliablesystems.doctoroffice.core.dao.company;

import com.reliablesystems.doctoroffice.core.to.company.CompanyTO;

import java.util.List;

public interface CompanyDAO {
    List<CompanyTO> findAllCompanies();
}
