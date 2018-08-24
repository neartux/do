package com.reliablesystems.doctoroffice.core.service.medicalappointment;

import com.reliablesystems.doctoroffice.core.domain.LocationData;
import com.reliablesystems.doctoroffice.core.domain.MedicalAppointment;
import com.reliablesystems.doctoroffice.core.domain.MedicalAppointmentHistory;
import com.reliablesystems.doctoroffice.core.domain.PersonalData;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.LocationDataRepository;
import com.reliablesystems.doctoroffice.core.repository.MedicalAppointmentHistoryRepository;
import com.reliablesystems.doctoroffice.core.repository.PersonalDataRepository;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class MedicalAppointmentHistoryServiceImpl implements MedicalAppointmentHistoryService {
    @Autowired
    private MedicalAppointmentHistoryRepository medicalAppointmentHistoryRepository;
    @Autowired
    private PersonalDataRepository personalDataRepository;
    @Autowired
    private LocationDataRepository locationDataRepository;

    /**
     * Method to create a {@link MedicalAppointmentHistory}
     *
     * @param medicalAppointment Data
     * @return Id history
     */
    @Override
    public Long create(MedicalAppointment medicalAppointment) {
        MedicalAppointmentHistory mah = new MedicalAppointmentHistory();
        // Set patient
        mah.setPatient(medicalAppointment.getPatient());
        // Create personal data patient
        PersonalData personalData = medicalAppointment.getPatient().getPersonalData();
        personalData.setId(null);
        personalDataRepository.save(personalData);
        // Create location data patient
        mah.setPersonalData(personalData);
        LocationData locationData = medicalAppointment.getPatient().getLocationData();
        locationData.setId(null);
        locationDataRepository.save(locationData);
        // Set data
        mah.setLocationData(locationData);
        mah.setStatus(medicalAppointment.getStatus());
        mah.setDoctorsOffice(medicalAppointment.getDoctorsOffice());
        mah.setDoctor(medicalAppointment.getDoctor());
        mah.setUser(medicalAppointment.getUser());
        mah.setVitalSigns(medicalAppointment.getVitalSigns());
        mah.setStartDate(medicalAppointment.getStartDate());
        mah.setEndDate(medicalAppointment.getEndDate());
        mah.setCreatedAt(DateUtil.nowTimestamp());
        mah.setType(medicalAppointment.getType());
        mah.setViarequest(medicalAppointment.getViarequest());
        mah.setReason(medicalAppointment.getReason());

        medicalAppointmentHistoryRepository.save(mah);

        return mah.getId();
    }
}
