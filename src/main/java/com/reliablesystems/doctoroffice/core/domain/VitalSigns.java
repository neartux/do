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
@Table(name = "vitalsigns")
public class VitalSigns implements Serializable {
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
    @Column(name = "weight")
    private String weight;
    @Column(name = "temperature")
    private String temperature;
    @Column(name = "size")
    private String size;
    @Column(name = "bloodpressure1")
    private String bloodpressure1;
    @Column(name = "bloodpressure2")
    private String bloodpressure2;
    @Column(name = "heartfrecuency")
    private String heartfrecuency;
    @Column(name = "breatingfrecuency")
    private String breatingfrecuency;
    @JsonIgnore
    @OneToMany( mappedBy = "vitalSigns")
    private Collection<MedicalAppointment> medicalAppointmentCollection;
    @JsonIgnore
    @OneToMany( mappedBy = "vitalSigns")
    private Collection<MedicalAppointmentHistory> medicalAppointmentHistoryCollection;

    public VitalSigns(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VitalSigns that = (VitalSigns) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VitalSigns{" +
                "id=" + id +
                ", patient=" + patient +
                ", status=" + status +
                ", weight='" + weight + '\'' +
                ", temperature='" + temperature + '\'' +
                ", size='" + size + '\'' +
                ", bloodpressure1='" + bloodpressure1 + '\'' +
                ", bloodpressure2='" + bloodpressure2 + '\'' +
                ", heartfrecuency='" + heartfrecuency + '\'' +
                ", breatingfrecuency='" + breatingfrecuency + '\'' +
                '}';
    }
}
