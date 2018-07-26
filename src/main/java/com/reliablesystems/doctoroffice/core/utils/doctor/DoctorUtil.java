package com.reliablesystems.doctoroffice.core.utils.doctor;

import com.reliablesystems.doctoroffice.core.domain.*;
import com.reliablesystems.doctoroffice.core.to.doctor.DoctorTO;
import com.reliablesystems.doctoroffice.core.utils.common.ApplicationKeys;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;

public final class DoctorUtil {
    private DoctorUtil() {}

    /**
     * Method that return {@link Doctor} for create a new doctor
     *
     * @param doctorTO Data of new doctor
     * @return Object {@link Doctor}
     */
    public static Doctor getDoctorToCreate(DoctorTO doctorTO) {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName(doctorTO.getFirstName());
        personalData.setLastName(doctorTO.getLastName());
        personalData.setBirthDate(DateUtil.stringToDate(doctorTO.getBirthDateS(), ApplicationKeys.DEFAULT_PATERN));
        personalData.setSex(doctorTO.getSex());

        LocationData locationData = new LocationData();
        locationData.setAddress(doctorTO.getAddress());
        locationData.setZipCode(doctorTO.getZipCode());
        locationData.setCellPhone(doctorTO.getCellPhone());
        locationData.setPhone(doctorTO.getPhone());
        locationData.setEmail(doctorTO.getEmail());

        Doctor doctor = new Doctor();
        doctor.setUser(new User(doctorTO.getUserId()));
        doctor.setCompany(new Company(doctorTO.getCompanyId()));
        doctor.setPersonalData(personalData);
        doctor.setLocationData(locationData);
        doctor.setProfessionalCard(doctorTO.getProfessionalCard());

        return doctor;
    }

    /**
     * Method that return {@link Doctor} for update an doctor
     *
     * @param doctorTO Data of doctor
     */
    public static void updateDoctorData(DoctorTO doctorTO, Doctor doctor) {
        doctor.getPersonalData().setFirstName(doctorTO.getFirstName());
        doctor.getPersonalData().setLastName(doctorTO.getLastName());
        doctor.getPersonalData().setBirthDate(DateUtil.stringToDate(doctorTO.getBirthDateS(), ApplicationKeys.DEFAULT_PATERN));
        doctor.getPersonalData().setSex(doctorTO.getSex());

        doctor.getLocationData().setAddress(doctorTO.getAddress());
        doctor.getLocationData().setZipCode(doctorTO.getZipCode());
        doctor.getLocationData().setCellPhone(doctorTO.getCellPhone());
        doctor.getLocationData().setPhone(doctorTO.getPhone());
        doctor.getLocationData().setEmail(doctorTO.getEmail());

        doctor.setProfessionalCard(doctorTO.getProfessionalCard());
    }
}
