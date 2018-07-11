package com.reliablesystems.doctoroffice.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "expedient")
    private String expedient;
    @Column(name = "profileimage")
    private String profileImage;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "personaldataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private PersonalData personalData;
    @JoinColumn(name = "locationdataid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private LocationData locationData;

    public Patient(long id) { this.id = id; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", expedient='" + expedient + '\'' +
                ", status=" + status +
                ", personalData=" + personalData +
                ", locationData=" + locationData +
                '}';
    }
}
