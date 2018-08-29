package com.reliablesystems.doctoroffice.core.dao.medicalappointment;

import com.reliablesystems.doctoroffice.core.to.medicalappointment.MedicalAppointmentHistoryTO;

import java.util.List;

public interface MedicalAppointmentHistoryDAO {

    List<MedicalAppointmentHistoryTO> findHistoryByPatientId(Long patientId);
}
