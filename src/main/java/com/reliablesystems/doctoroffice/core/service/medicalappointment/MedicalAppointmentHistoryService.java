package com.reliablesystems.doctoroffice.core.service.medicalappointment;

import com.reliablesystems.doctoroffice.core.domain.MedicalAppointment;
import com.reliablesystems.doctoroffice.core.to.medicalappointment.MedicalAppointmentHistoryTO;

import java.util.List;

public interface MedicalAppointmentHistoryService {
    List<MedicalAppointmentHistoryTO> findHistoryByPatientId(Long patientId);

    Long create(MedicalAppointment medicalAppointment);
}
