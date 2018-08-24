package com.reliablesystems.doctoroffice.core.service.medicalappointment;

import com.reliablesystems.doctoroffice.core.to.medicalappointment.MedicalAppointmentTO;

public interface MedicalAppointmentService {
    Long create(MedicalAppointmentTO medicalAppointmentTO);

    void changeStatus(Long id, Long statusId);

    void update(MedicalAppointmentTO medicalAppointmentTO);

    void startMedicalAppointment(Long id, Long doctorId);

    void closeMedicalAppointment(Long id);
}
