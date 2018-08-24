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
public class MedicalAppointmentTO {
    private Long id;
    private Long patientId;
    private Long statusId;
    private Long doctorsOfficeId;
    private Long userId;
    private Date startDate;
    private Date endDate;
    private String type;
    private String viarequest;
    private String reason;
}
