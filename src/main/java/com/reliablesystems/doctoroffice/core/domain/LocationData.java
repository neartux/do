package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "locationdata")
public class LocationData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "address")
    private String address;
    @Column(name = "zipcode")
    private String zipCode;
    @Column(name = "cellphone")
    private String cellPhone;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "cityid", referencedColumnName = "id")
    @ManyToOne
    private City city;
    @JsonIgnore
    @OneToMany( mappedBy = "locationData")
    private Collection<Company> companyCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "locationData")
    private Collection<User> userCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "locationData")
    private Collection<Patient> patientCollection;

    public LocationData(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LocationData that = (LocationData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", city=" + city +
                '}';
    }
}
