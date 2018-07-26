package com.reliablesystems.doctoroffice.core.dao.patient;

import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;

import java.util.List;

public interface PatientDAO {
    List<PatientTO> findAllPatients(long companyId, int ofset, int limit, String search);

    int finAllPatientsCount(long companyId, String search);

    Long findMaxPatientIdByCompany(long companyId);
}
