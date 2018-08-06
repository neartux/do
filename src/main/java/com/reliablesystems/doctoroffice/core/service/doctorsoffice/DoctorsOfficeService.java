package com.reliablesystems.doctoroffice.core.service.doctorsoffice;

import com.reliablesystems.doctoroffice.core.domain.DoctorsOffice;
import com.reliablesystems.doctoroffice.core.to.doctorsoffice.DoctorsOfficeTO;

import java.util.List;

public interface DoctorsOfficeService {
    DoctorsOffice findById(long id);

    List<DoctorsOfficeTO> findDoctorsByCompany(long companyId, int ofset, int limit, String search);

    List<DoctorsOfficeTO> findDoctorsOfficesByCompany(long companyId);

    int findDoctorsOfficesCountByCompany(long companyId, String search);

    Long createDoctorsOffcie(DoctorsOffice doctorsOffice);

    void updateDoctorsOffice(DoctorsOfficeTO doctorsOfficeTO);

    void inactiveDoctorsOffice(long id);
}
