package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "itinerary")
public class Itinerary implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @JoinColumn(name = "doctorid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Doctor doctor;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "usercreateid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
    @Column(name = "itinerarydate")
    @Temporal(TemporalType.DATE)
    private Date itineraryDate;
    @JsonIgnore
    @OneToMany(mappedBy = "itinerary")
    private Collection<ItineraryDetail> itineraryDetailCollection;

    public Itinerary(long id) { this.id = id; }

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", status=" + status +
                ", user=" + user +
                ", itineraryDate=" + itineraryDate +
                '}';
    }
}
