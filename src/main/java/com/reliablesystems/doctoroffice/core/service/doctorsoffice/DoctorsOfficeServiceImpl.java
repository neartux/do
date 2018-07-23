package com.reliablesystems.doctoroffice.core.service.doctorsoffice;

import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.DoctorsOfficeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class DoctorsOfficeServiceImpl implements DoctorsOfficeService {
    @Autowired
    private DoctorsOfficeRepository doctorsOfficeRepository;
}
