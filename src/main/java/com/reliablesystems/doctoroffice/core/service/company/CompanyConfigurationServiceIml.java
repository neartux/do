package com.reliablesystems.doctoroffice.core.service.company;

import com.reliablesystems.doctoroffice.core.domain.Company;
import com.reliablesystems.doctoroffice.core.domain.CompanyConfiguration;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.CompanyConfigurationRepository;
import com.reliablesystems.doctoroffice.core.utils.common.NumberUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class CompanyConfigurationServiceIml implements CompanyConfigurationService {
    @Autowired
    private CompanyConfigurationRepository companyConfigurationRepository;

    /**
     * Method to create a configuration of a company
     *
     * @param company Data
     * @return Id
     */
    @Override
    public Long createCompanyConfiguration(Company company) {
        CompanyConfiguration companyConfiguration = new CompanyConfiguration();
        companyConfiguration.setCompany(company);
        companyConfiguration.setOfficessNumer(NumberUtil.TWO_INT);

        companyConfigurationRepository.save(companyConfiguration);

        return companyConfiguration.getId();
    }
}
