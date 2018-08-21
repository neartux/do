package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.MedicalAppointmentHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalAppointmentHistoryRepository extends JpaRepository<MedicalAppointmentHistory, Long> {
}
