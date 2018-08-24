package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.VitalSigns;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VitalSignsRepository extends JpaRepository<VitalSigns, Long> {

    VitalSigns findTopByPatientIdAndStatusIdOrderByIdDesc(Long patientId, Long statusId);
}
