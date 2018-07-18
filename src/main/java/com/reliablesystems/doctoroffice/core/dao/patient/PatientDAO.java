package com.reliablesystems.doctoroffice.core.dao.patient;

import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;

import java.util.List;

public interface PatientDAO {
    List<PatientTO> findAllPatients(int ofset, int limit, String search);

    int finAllPatientsCount(String search);

    Long findMaxPatientId();
}
