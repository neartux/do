package com.reliablesystems.doctoroffice.core.service.country;

import com.reliablesystems.doctoroffice.core.dao.country.CountryDAO;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class CountryServiceImpl implements CountryService {
    @Autowired
    private CountryDAO countryDAO;
}
