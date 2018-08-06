package com.reliablesystems.doctoroffice.core.to.itinerary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItineraryDetailTO {
    private Long detailId;
    private Long itineraryId;
    private Long eventTypeId;
    private String eventType;
    private Date startsAt;
    private Date endsAt;
    private Date itineraryDate;
    private boolean draggable;
    private boolean resizable;
    private boolean incrementsBadgeTotal;
    private String actions;
    private String color;
    private String title;
    private String type; //info, success, warning, danger


}
