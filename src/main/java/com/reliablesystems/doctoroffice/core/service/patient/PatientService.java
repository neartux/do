package com.reliablesystems.doctoroffice.core.service.patient;

import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;

import java.util.List;

public interface PatientService {
    List<PatientTO> findAllPatients(int ofset, int limit, String search);

    int finAllPatientsCount(String search);
}
