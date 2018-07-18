package com.reliablesystems.doctoroffice.core.service.patient;

import com.reliablesystems.doctoroffice.core.dao.patient.PatientDAO;
import com.reliablesystems.doctoroffice.core.domain.Patient;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.LocationDataRepository;
import com.reliablesystems.doctoroffice.core.repository.PatientRepository;
import com.reliablesystems.doctoroffice.core.repository.PersonalDataRepository;
import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.NumberUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import com.reliablesystems.doctoroffice.core.utils.patient.PatientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private LocationDataRepository locationDataRepository;
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

    /**
     * Method to find a patient by primary key
     *
     * @param id Primary key
     * @return The {@link Patient}
     */
    @Override
    public Patient findPatientById(long id) {
        return patientRepository.findOne(id);
    }

    /**
     * Method to create a new patient
     *
     * @param patient The object {@link Patient} to save
     * @return New patient id
     */
    @Override
    public long createPatient(Patient patient) {
        // Save personal data
        personalDataRepository.save(patient.getPersonalData());
        // Save location data
        locationDataRepository.save(patient.getLocationData());
        // Save patient
        patient.setCreatedAt(DateUtil.nowTimestamp());
        patient.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        patient.setExpedient(NumberUtil.longToString(NumberUtil.add(patientDAO.findMaxPatientId(), NumberUtil.ONE_LONG).longValue()));
        patientRepository.save(patient);

        return patient.getId();
    }

    /**
     * Method to update a patient
     *
     * @param patientTO Patient data
     */
    @Override
    public void updatePatient(PatientTO patientTO) {
        Patient patientToUpdate = findPatientById(patientTO.getId());
        if (patientToUpdate == null) {
            throw new BackEndException("Patient Not Found");
        }
        // Override patient data
        patientToUpdate = PatientUtil.getPatientToUpdate(patientTO, patientToUpdate);
        // Save personal data
        personalDataRepository.save(patientToUpdate.getPersonalData());
        // Save location data
        locationDataRepository.save(patientToUpdate.getLocationData());
        // Save patient
        patientRepository.save(patientToUpdate);
    }

    /**
     * Method to inactive patient by id
     *
     * @param id patient id
     */
    @Override
    public void inactivePatient(long id) {
        Patient patientInactive = findPatientById(id);
        if (patientInactive == null) {
            throw new BackEndException("Patient Not Found");
        }
        patientInactive.setStatus(new Status(StatusKeys.INACTIVE_STATUS));
        patientRepository.save(patientInactive);
    }
}
