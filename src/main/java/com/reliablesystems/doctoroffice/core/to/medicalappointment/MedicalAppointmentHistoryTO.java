package com.reliablesystems.doctoroffice.core.to.medicalappointment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class MedicalAppointmentHistoryTO {
    private Long id;
    private Long patientId;
    private Long statusId;
    private Long doctorsOfficeId;
    private Long doctorId;
    private Date startDate;
    private Date endDate;
    private String type;
    private String reason;
    private String doctorsoffice;
    private String doctor;
    private String patient;
}
