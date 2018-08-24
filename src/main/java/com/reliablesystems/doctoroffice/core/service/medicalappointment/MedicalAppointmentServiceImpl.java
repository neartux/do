package com.reliablesystems.doctoroffice.core.service.medicalappointment;

import com.reliablesystems.doctoroffice.core.domain.*;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.MedicalAppointmentRepository;
import com.reliablesystems.doctoroffice.core.service.doctor.DoctorService;
import com.reliablesystems.doctoroffice.core.service.doctorsoffice.DoctorsOfficeService;
import com.reliablesystems.doctoroffice.core.service.patient.PatientService;
import com.reliablesystems.doctoroffice.core.to.medicalappointment.MedicalAppointmentTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class MedicalAppointmentServiceImpl implements MedicalAppointmentService {
    @Autowired
    private MedicalAppointmentRepository medicalAppointmentRepository;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorsOfficeService doctorsOfficeService;
    @Autowired
    private VitalSignsService vitalSignsService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private MedicalAppointmentHistoryService medicalAppointmentHistoryService;

    /**
     * Method to create a new cita
     *
     * @param medicalAppointmentTO Data
     * @return Id new medicalAppointment
     */
    @Override
    public Long create(MedicalAppointmentTO medicalAppointmentTO) {
        // Find patient
        Patient patient = patientService.findPatientById(medicalAppointmentTO.getPatientId());
        // Validate patient
        if (patient == null || patient.getStatus().getId().equals(StatusKeys.INACTIVE_STATUS)) {
            throw new BackEndException("Patient With Id: " + medicalAppointmentTO.getId() + " Not Found");
        }
        // Find office
        DoctorsOffice doctorsOffice = doctorsOfficeService.findById(medicalAppointmentTO.getDoctorsOfficeId());
        // Validate office
        if (doctorsOffice == null || doctorsOffice.getStatus().getId().equals(StatusKeys.INACTIVE_STATUS)) {
            throw new BackEndException("Office With Id: " + medicalAppointmentTO.getDoctorsOfficeId() + " Not Found");
        }
        MedicalAppointment ma = new MedicalAppointment();
        ma.setPatient(patient);
        ma.setStatus(new Status(medicalAppointmentTO.getStatusId()));
        ma.setDoctorsOffice(doctorsOffice);
        ma.setUser(new User(medicalAppointmentTO.getUserId()));
        // Create vitalsigns
        Long vSId = vitalSignsService.create(patient.getId());
        ma.setVitalSigns(new VitalSigns(vSId));
        ma.setStartDate(medicalAppointmentTO.getStartDate());
        ma.setType(medicalAppointmentTO.getType());
        ma.setViarequest(medicalAppointmentTO.getViarequest());
        ma.setReason(medicalAppointmentTO.getReason());

        medicalAppointmentRepository.save(ma);

        return ma.getId();
    }

    /**
     * Method to update status
     *
     * @param id id {@link MedicalAppointment}
     * @param statusId New status
     */
    @Override
    public void changeStatus(Long id, Long statusId) {
        // Find medicalAppointment
        MedicalAppointment ma = medicalAppointmentRepository.findOne(id);
        // Validate
        if (ma == null) {
            throw new BackEndException("Medical Appointment With Id: " + id + " Not Found");
        }
        ma.setStatus(new Status(statusId));
        medicalAppointmentRepository.save(ma);
    }

    /**
     * Method to update a {@link MedicalAppointment}
     *
     * @param medicalAppointmentTO Data
     */
    @Override
    public void update(MedicalAppointmentTO medicalAppointmentTO) {
        // Find patient
        Patient patient = patientService.findPatientById(medicalAppointmentTO.getPatientId());
        // Validate patient
        if (patient == null || patient.getStatus().getId().equals(StatusKeys.INACTIVE_STATUS)) {
            throw new BackEndException("Patient With Id: " + medicalAppointmentTO.getId() + " Not Found");
        }
        // Find office
        DoctorsOffice doctorsOffice = doctorsOfficeService.findById(medicalAppointmentTO.getDoctorsOfficeId());
        // Validate office
        if (doctorsOffice == null || doctorsOffice.getStatus().getId().equals(StatusKeys.INACTIVE_STATUS)) {
            throw new BackEndException("Office With Id: " + medicalAppointmentTO.getDoctorsOfficeId() + " Not Found");
        }
        // Find medical appointment
        MedicalAppointment ma = medicalAppointmentRepository.findOne(medicalAppointmentTO.getId());
        // Validate
        if (ma == null) {
            throw new BackEndException("Medical Appointment With Id: " + medicalAppointmentTO.getId() + " Not Found");
        }
        ma.setPatient(patient);
        ma.setStatus(new Status(medicalAppointmentTO.getStatusId()));
        ma.setDoctorsOffice(doctorsOffice);
        ma.setStartDate(medicalAppointmentTO.getStartDate());
        ma.setType(medicalAppointmentTO.getType());
        ma.setViarequest(medicalAppointmentTO.getViarequest());
        ma.setReason(medicalAppointmentTO.getReason());

        medicalAppointmentRepository.save(ma);
    }

    /**
     * Method to start a {@link MedicalAppointment}
     *
     * @param id Id medical appointment
     * @param doctorId Doctor id
     */
    @Override
    public void startMedicalAppointment(Long id, Long doctorId) {
        // Find medical appointment
        MedicalAppointment ma = medicalAppointmentRepository.findOne(id);
        // Validate
        if (ma == null) {
            throw new BackEndException("Medical Appointment With Id: " + doctorId + " Not Found");
        }
        // Find doctor
        Doctor doctor = doctorService.findDoctorById(doctorId);
        if (doctor == null) {
            throw new BackEndException("Doctor With Id: " + doctorId + " Not Found");
        }
        ma.setStatus(new Status(StatusKeys.START_STATUS));
        ma.setDoctor(doctor);
        ma.setStartDate(DateUtil.nowTimestamp());
        medicalAppointmentRepository.save(ma);
    }

    /**
     * Method to finaliza a {@link MedicalAppointment}
     *
     * @param id Id medical apointment
     */
    @Override
    public void closeMedicalAppointment(Long id) {
        // Find medical appointment
        MedicalAppointment ma = medicalAppointmentRepository.findOne(id);
        // Validate
        if (ma == null) {
            throw new BackEndException("Medical Appointment With Id: " + id + " Not Found");
        }
        if (!ma.getStatus().getId().equals(StatusKeys.START_STATUS)) {
            throw new BackEndException("Invalid status: " + ma.getStatus().getId() + " ");
        }
        ma.setStatus(new Status(StatusKeys.FINALIZE_STATUS));
        // Save
        medicalAppointmentRepository.save(ma);
        // Create history
        medicalAppointmentHistoryService.create(ma);
    }
}
