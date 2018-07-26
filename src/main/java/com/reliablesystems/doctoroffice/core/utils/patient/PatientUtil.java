package com.reliablesystems.doctoroffice.core.utils.patient;

import com.reliablesystems.doctoroffice.core.domain.*;
import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;
import com.reliablesystems.doctoroffice.core.utils.common.ApplicationKeys;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.NumberUtil;

public final class PatientUtil {

    private PatientUtil() {}


    /**
     * Method that return {@link Patient} for create a new patient
     *
     * @param patientTO Data of new patient
     * @return Object {@link Patient}
     */
    public static Patient getPatientToCreate(PatientTO patientTO) {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName(patientTO.getFirstName());
        personalData.setLastName(patientTO.getLastName());
        personalData.setBirthDate(DateUtil.stringToDate(patientTO.getBirthDateS(), ApplicationKeys.DEFAULT_PATERN));
        personalData.setBloodType(new BloodType(patientTO.getBloodTypeId()));
        personalData.setCivilStatus(patientTO.getCivilStatus());
        personalData.setSex(patientTO.getSex());

        LocationData locationData = new LocationData();
        locationData.setAddress(patientTO.getAddress());
        locationData.setZipCode(patientTO.getZipCode());
        locationData.setCellPhone(patientTO.getCellPhone());
        locationData.setPhone(patientTO.getPhone());
        locationData.setEmail(patientTO.getEmail());

        Patient patient = new Patient();
        patient.setCompany(new Company(patientTO.getCompanyId()));
        patient.setPersonalData(personalData);
        patient.setLocationData(locationData);

        return patient;
    }

    /**
     * Method that return {@link Patient} for update an patient
     *
     * @param patientTO Data of patient
     */
    public static void updatePatientData(PatientTO patientTO, Patient patient) {
        patient.getPersonalData().setFirstName(patientTO.getFirstName());
        patient.getPersonalData().setLastName(patientTO.getLastName());
        patient.getPersonalData().setBirthDate(DateUtil.stringToDate(patientTO.getBirthDateS(), ApplicationKeys.DEFAULT_PATERN));
        patient.getPersonalData().setBloodType(new BloodType(patientTO.getBloodTypeId()));
        patient.getPersonalData().setCivilStatus(patientTO.getCivilStatus());
        patient.getPersonalData().setSex(patientTO.getSex());

        patient.getLocationData().setAddress(patientTO.getAddress());
        patient.getLocationData().setZipCode(patientTO.getZipCode());
        patient.getLocationData().setCellPhone(patientTO.getCellPhone());
        patient.getLocationData().setPhone(patientTO.getPhone());
        patient.getLocationData().setEmail(patientTO.getEmail());
    }
}
