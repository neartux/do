package com.reliablesystems.doctoroffice.core.service.medicalappointment;

import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.MedicalAppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class MedicalAppointmentServiceImpl implements MedicalAppointmentService {
    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;
}
