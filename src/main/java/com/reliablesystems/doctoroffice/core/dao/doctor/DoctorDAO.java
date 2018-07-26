package com.reliablesystems.doctoroffice.core.dao.doctor;

import com.reliablesystems.doctoroffice.core.domain.Doctor;

import java.util.List;

public interface DoctorDAO {
    List<Doctor> findDoctorsByCompany(long companyId, int ofset, int limit, String search);

    int findDoctorsCountByCompany(long companyId, String search);
}
