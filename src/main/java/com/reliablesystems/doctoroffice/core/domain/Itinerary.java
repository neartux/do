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
    @JoinColumn(name = "doctorsofficeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DoctorsOffice doctorsOffice;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "usercreateid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User user;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdAt;
    @JsonIgnore
    @OneToMany(mappedBy = "itinerary")
    private Collection<ItineraryDetail> itineraryDetailCollection;

    public Itinerary(long id) { this.id = id; }

    @Override
    public String toString() {
        return "Itinerary{" +
                "id=" + id +
                ", doctorsOffice=" + doctorsOffice +
                ", status=" + status +
                ", user=" + user +
                ", createdAt=" + createdAt +
                '}';
    }
}
