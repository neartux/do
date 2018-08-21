package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
}
