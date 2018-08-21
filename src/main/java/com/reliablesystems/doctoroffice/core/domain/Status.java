package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "status")
public class Status implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<Country> countryCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<State> stateCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<City> cityCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<UseCfdi> useCfdiCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<FiscalRegime> fiscalRegimeCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<BillingData> billingDataCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<Company> companyCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<User> userCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<Patient> patientCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<DoctorsOffice> doctorsOfficeCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<Itinerary> itineraryCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<ItineraryDetail> itineraryDetailCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<VitalSigns> vitalSignsCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<MedicalAppointment> medicalAppointmentCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "status")
    private Collection<MedicalAppointmentHistory> medicalAppointmentHistoryCollection;

    public Status(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Status status = (Status) o;
        return Objects.equals(id, status.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Status{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
