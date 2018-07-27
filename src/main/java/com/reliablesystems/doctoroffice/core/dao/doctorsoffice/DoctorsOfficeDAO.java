package com.reliablesystems.doctoroffice.core.dao.doctorsoffice;

import com.reliablesystems.doctoroffice.core.to.doctorsoffice.DoctorsOfficeTO;

import java.util.List;

public interface DoctorsOfficeDAO {
    List<DoctorsOfficeTO> findDoctorsOfficesByCompany(long companyId, int ofset, int limit, String search);

    int findDoctorsOfficesCountByCompany(long companyId, String search);
}
