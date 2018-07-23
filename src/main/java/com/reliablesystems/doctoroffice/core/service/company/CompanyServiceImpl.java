package com.reliablesystems.doctoroffice.core.service.company;

import com.reliablesystems.doctoroffice.core.dao.company.CompanyDAO;
import com.reliablesystems.doctoroffice.core.domain.Company;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.CompanyRepository;
import com.reliablesystems.doctoroffice.core.repository.LocationDataRepository;
import com.reliablesystems.doctoroffice.core.repository.PersonalDataRepository;
import com.reliablesystems.doctoroffice.core.service.user.UserService;
import com.reliablesystems.doctoroffice.core.to.company.CompanyTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyDAO companyDAO;
    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private LocationDataRepository locationDataRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyConfigurationService companyConfigurationService;

    /**
     * Query to find all companies
     *
     * @return List of companies
     */
    @Override
    public List<CompanyTO> findAllCompanies() {
        return companyDAO.findAllCompanies();
    }

    /**
     * Method to create a new company
     *
     * @param company Company data
     * @return New company id
     */
    @Override
    public Long createCompany(Company company) {
        // Save personal data
        personalDataRepository.save(company.getPersonalData());
        // Save location data
        locationDataRepository.save(company.getLocationData());
        // Save company
        company.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        company.setCreatedAt(DateUtil.nowTimestamp());
        companyRepository.save(company);
        // Create a administrator user
        userService.createAdminUserForCompany(company);
        // Create company configuration
        companyConfigurationService.createCompanyConfiguration(company);
        return company.getId();
    }
}
