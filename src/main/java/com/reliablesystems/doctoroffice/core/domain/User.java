package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "username", nullable = false, unique = true)
    private String userName;
    @Column(name = "password")
    private String password;
    @Column(name = "createdat", nullable = false)
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
    @OneToOne( mappedBy = "user")
    private Doctor doctor;
    @JoinColumn(name = "companyid", referencedColumnName = "id")
    @ManyToOne
    private Company company;
    @JsonIgnore
    @JoinTable(name = "roleuser", joinColumns = {
            @JoinColumn(name = "iduser", referencedColumnName = "id")}, inverseJoinColumns = {
            @JoinColumn(name = "idrole", referencedColumnName = "id")})
    @ManyToMany
    private List<Role> roleList;
    @JsonIgnore
    @OneToMany( mappedBy = "user")
    private Collection<Itinerary> itineraryCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "user")
    private Collection<MedicalAppointment> medicalAppointmentCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "user")
    private Collection<MedicalAppointmentHistory> medicalAppointmentHistoryCollection;

    public User(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", createdAt=" + createdAt +
                ", status=" + status +
                ", personalData=" + personalData +
                ", locationData=" + locationData +
                ", company=" + company +
                '}';
    }
}
