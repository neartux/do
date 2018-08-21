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
@Table(name = "doctor")
public class Doctor implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "professionalcard")
    private String professionalCard;
    @Column(name = "profileimage")
    private String profileImage;
    @Column(name = "signimage")
    private String signImage;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "personaldataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonalData personalData;
    @JoinColumn(name = "locationdataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LocationData locationData;
    @JoinColumn(name = "userid", referencedColumnName = "id")
    @OneToOne(optional = false)
    private User user;
    @JoinColumn(name = "companyid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Company company;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Collection<DoctorsOffice> doctorsOfficeCollection;
    @JsonIgnore
    @OneToMany(mappedBy = "doctor")
    private Collection<MedicalAppointment> medicalAppointmentCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "doctor")
    private Collection<MedicalAppointmentHistory> medicalAppointmentHistoryCollection;

    public Doctor(long id) { this.id = id; }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Doctor doctor = (Doctor) o;
        return Objects.equals(id, doctor.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "id=" + id +
                ", professionalCard='" + professionalCard + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", signImage='" + signImage + '\'' +
                ", status=" + status +
                ", personalData=" + personalData +
                ", locationData=" + locationData +
                ", user=" + user +
                '}';
    }
}
