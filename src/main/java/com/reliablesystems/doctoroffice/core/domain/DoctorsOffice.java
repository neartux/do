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
@Table(name = "doctorsoffice")
public class DoctorsOffice implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "description")
    private String description;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "doctorid", referencedColumnName = "id")
    @OneToOne(optional = false)
    private Doctor doctor;
    @JoinColumn(name = "companyid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Company company;
    @JsonIgnore
    @OneToMany( mappedBy = "doctorsOffice")
    private Collection<Itinerary> itineraryCollection;

    public DoctorsOffice(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorsOffice that = (DoctorsOffice) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "DoctorsOffice{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", doctor=" + doctor +
                '}';
    }
}
