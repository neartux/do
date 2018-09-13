package com.reliablesystems.doctoroffice.core.to.itinerary;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.reliablesystems.doctoroffice.core.to.doctor.DoctorTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItineraryTO {
    private Long id;
    private DoctorTO doctorTO;
    private Date itineraryDate;
    private Long detailId;
    private Long itineraryId;
    private Long itineraryDetailId;
    private Long eventTypeId;
    private String eventType;
    private Date startsAt;
    private Date endsAt;
    private boolean draggable;
    private boolean resizable;
    private boolean incrementsBadgeTotal;
    private String actions;
    private String color;
    private String title;
    private String type; //info, success, warning, danger
    private Long doctorOfficeId;

    @Override
    public String toString() {
        return "ItineraryTO{" +
                "id=" + id +
                ", doctorTO=" + doctorTO +
                ", itineraryDate=" + itineraryDate +
                ", detailId=" + detailId +
                ", itineraryId=" + itineraryId +
                ", eventTypeId=" + eventTypeId +
                ", eventType='" + eventType + '\'' +
                ", startsAt=" + startsAt +
                ", endsAt=" + endsAt +
                ", draggable=" + draggable +
                ", resizable=" + resizable +
                ", incrementsBadgeTotal=" + incrementsBadgeTotal +
                ", actions='" + actions + '\'' +
                ", color='" + color + '\'' +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", doctorOfficeId=" + doctorOfficeId +
                '}';
    }
}
