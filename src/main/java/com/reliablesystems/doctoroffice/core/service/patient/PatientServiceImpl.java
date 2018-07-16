package com.reliablesystems.doctoroffice.core.service.patient;

import com.reliablesystems.doctoroffice.core.dao.patient.PatientDAO;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDAO patientDAO;

    /**
     * Method to find all patient of the system
     *
     * @param ofset Search start
     * @param limit Search limit
     * @param search Word
     * @return List of {@link PatientTO}
     */
    @Override
    public List<PatientTO> findAllPatients(int ofset, int limit, String search) {
        return patientDAO.findAllPatients(ofset, limit, search);
    }

    /**
     * Method to get total of patient in the systems, with parameters
     *
     * @param search Word to search
     * @return Number of elements
     */
    @Override
    public int finAllPatientsCount(String search) {
        return patientDAO.finAllPatientsCount(search);
    }
}
