package com.reliablesystems.doctoroffice.core.dao.doctor;

import com.reliablesystems.doctoroffice.core.to.doctor.DoctorTO;

import java.util.List;

public interface DoctorDAO {
    List<DoctorTO> findDoctorsByCompany(long companyId, int ofset, int limit, String search);

    int findDoctorsCountByCompany(long companyId, String search);
}
