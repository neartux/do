package com.reliablesystems.doctoroffice.core.utils.doctorsoffice;

import com.reliablesystems.doctoroffice.core.domain.*;
import com.reliablesystems.doctoroffice.core.to.doctorsoffice.DoctorsOfficeTO;

public final class DoctorsOfficeUtil {

    private DoctorsOfficeUtil() {}

    /**
     * Method that return {@link DoctorsOffice} for create a new doctorsoffice
     *
     * @param doctorsOfficeTO Data
     * @return Object {@link DoctorsOffice}
     */
    public static DoctorsOffice getDoctorsOfficeToCreate(DoctorsOfficeTO doctorsOfficeTO) {

        DoctorsOffice doctorsOffice = new DoctorsOffice();
        doctorsOffice.setCompany(new Company(doctorsOfficeTO.getCompanyId()));
        doctorsOffice.setDescription(doctorsOfficeTO.getDescription());
        doctorsOffice.setDoctor(new Doctor(doctorsOfficeTO.getDoctorId()));

        return doctorsOffice;
    }
}
