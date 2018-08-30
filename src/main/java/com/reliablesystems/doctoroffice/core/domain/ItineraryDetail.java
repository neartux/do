package com.reliablesystems.doctoroffice.core.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "itinerarydetail")
public class ItineraryDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @JoinColumn(name = "itineraryid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Itinerary itinerary;
    @JoinColumn(name = "statusid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Status status;
    @JoinColumn(name = "eventtypeid", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private EventType eventType;
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Column(name = "createdat")
    @Temporal(TemporalType.DATE)
    private Date createdAt;

    public ItineraryDetail(long id) { this.id = id; }

    @Override
    public String toString() {
        return "ItineraryDetail{" +
                "id=" + id +
                ", itinerary=" + itinerary +
                ", status=" + status +
                ", eventType=" + eventType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", createdat=" + createdAt +
                '}';
    }
}
