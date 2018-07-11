package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Date;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "personaldata")
public class PersonalData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "firstname")
    private String firstName;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "birthdate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(name = "identifier")
    private String identifier;
    @Column(name = "sex")
    private String sex;
    @Column(name = "civilstatus")
    private String civilStatus;
    @JoinColumn(name = "bloodtypeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private BloodType bloodType;
    @JsonIgnore
    @OneToMany( mappedBy = "personalData")
    private Collection<Company> companyCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "personalData")
    private Collection<User> userCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "personalData")
    private Collection<Patient> patientCollection;

    public PersonalData(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonalData that = (PersonalData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PersonalData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", identifier='" + identifier + '\'' +
                ", sex='" + sex + '\'' +
                ", civilStatus='" + civilStatus + '\'' +
                ", bloodType=" + bloodType +
                '}';
    }
}
