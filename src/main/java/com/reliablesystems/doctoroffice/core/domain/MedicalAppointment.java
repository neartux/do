package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "medicalappointment")
public class MedicalAppointment implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @JoinColumn(name = "patientid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Patient patient;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "doctorsofficeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DoctorsOffice doctorsOffice;
    @JoinColumn(name = "doctorid", referencedColumnName = "id")
    @ManyToOne
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
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @Column(name = "type")
    private String type;
    @Column(name = "viarequest")
    private String viarequest;
    @Column(name = "reason")
    private String reason;
    @JsonIgnore
    @OneToMany( mappedBy = "medicalAppointment")
    private Collection<MedicalAppointmentHistory> medicalAppointmentHistoryCollection;

    public MedicalAppointment(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MedicalAppointment that = (MedicalAppointment) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "MedicalAppointment{" +
                "id=" + id +
                ", patient=" + patient +
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
