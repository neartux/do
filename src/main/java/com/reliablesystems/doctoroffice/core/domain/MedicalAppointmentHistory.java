package com.reliablesystems.doctoroffice.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicalappointmenthistory")
public class MedicalAppointmentHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @JoinColumn(name = "medicalappointmentid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private MedicalAppointment medicalAppointment;
    @JoinColumn(name = "patientid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "personaldataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonalData personalData;
    @JoinColumn(name = "locationdataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LocationData locationData;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "doctorsofficeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DoctorsOffice doctorsOffice;
    @JoinColumn(name = "doctorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Doctor doctor;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "vitalsignsid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private VitalSigns vitalSigns;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "type")
    private String type;
    @Column(name = "viarequest")
    private String viarequest;

    public MedicalAppointmentHistory(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalAppointmentHistory that = (MedicalAppointmentHistory) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MedicalAppointmentHistory{" +
                "id=" + id +
                ", medicalAppointment=" + medicalAppointment +
                ", patient=" + patient +
                ", personalData=" + personalData +
                ", locationData=" + locationData +
                ", status=" + status +
                ", doctorsOffice=" + doctorsOffice +
                ", doctor=" + doctor +
                ", user=" + user +
                ", vitalSigns=" + vitalSigns +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdAt=" + createdAt +
                ", type='" + type + '\'' +
                ", viarequest='" + viarequest + '\'' +
                '}';
    }
}
