package com.reliablesystems.doctoroffice.core.utils.patient;

import com.reliablesystems.doctoroffice.core.domain.BloodType;
import com.reliablesystems.doctoroffice.core.domain.Patient;
import com.reliablesystems.doctoroffice.core.domain.PersonalData;
import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;
import com.reliablesystems.doctoroffice.core.utils.common.ApplicationKeys;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;

public final class PatientUtil {

    private PatientUtil() {}


    public static Patient getPatient(PatientTO patientTO) {
        PersonalData personalData = new PersonalData();
        personalData.setFirstName(patientTO.getFirstName());
        personalData.setLastName(patientTO.getLastName());
        personalData.setBirthDate(DateUtil.stringToDate(patientTO.getBirthDateS(), ApplicationKeys.DEFAULT_PATERN));
        personalData.setBloodType(new BloodType(patientTO.getBloodTypeId()));
        personalData.setCivilStatus(patientTO.getCivilStatus());

        Patient patient = new Patient();

        return patient;

    }
}
