package com.reliablesystems.doctoroffice.core.service.doctor;

import com.reliablesystems.doctoroffice.core.domain.Doctor;
import com.reliablesystems.doctoroffice.core.to.doctor.DoctorTO;

import java.util.List;

public interface DoctorService {
    Doctor findDoctorById(long id);

    List<DoctorTO> findDoctorsByCompany(long companyId, int ofset, int limit, String search);

    int findDoctorsCountByCompany(long companyId, String search);

    Long createDoctor(Doctor doctor, String userName, String password);

    void updateDoctor(DoctorTO doctorTO);

    void inactiveDoctor(long id);

    void updateProfilePicturePath(long id, String path);
}
