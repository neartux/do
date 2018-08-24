package com.reliablesystems.doctoroffice.core.service.medicalappointment;

import com.reliablesystems.doctoroffice.core.domain.Patient;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.domain.VitalSigns;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.VitalSignsRepository;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class VitalSignsServiceImpl implements VitalSignsService {
    @Autowired
    private VitalSignsRepository vitalSignsRepository;

    /**
     * Method to create a new {@link VitalSigns} by patient
     *
     * @param patientId patient id
     * @return Id new element
     */
    @Override
    public Long create(Long patientId) {
        // Find last vitalsigns by patient
        VitalSigns vitalSigns = vitalSignsRepository.findTopByPatientIdAndStatusIdOrderByIdDesc(patientId, StatusKeys.ACTIVE_STATUS);
        VitalSigns newVitalSigns = new VitalSigns();
        // Verifi if is new or replicate
        if (vitalSigns == null) {
            newVitalSigns.setPatient(new Patient(patientId));
            newVitalSigns.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        } else {
            newVitalSigns = vitalSigns;
            newVitalSigns.setId(null);
        }
        vitalSignsRepository.save(newVitalSigns);

        return newVitalSigns.getId();
    }
}
