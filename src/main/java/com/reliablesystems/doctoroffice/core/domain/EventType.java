package com.reliablesystems.doctoroffice.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "eventtype")
public class EventType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "description")
    private String description;
    @JsonIgnore
    @OneToMany(mappedBy = "eventType")
    private Collection<ItineraryDetail> itineraryDetailCollection;

    public EventType(long id) { this.id = id; }

    @Override
    public String toString() {
        return "EventType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
