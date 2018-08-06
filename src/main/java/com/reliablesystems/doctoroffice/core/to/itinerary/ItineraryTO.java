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
    private List<ItineraryDetailTO> itineraryDetailTOList;

    @Override
    public String toString() {
        return "ItineraryTO{" +
                "id=" + id +
                ", doctorTO=" + doctorTO +
                ", itineraryDate=" + itineraryDate +
                ", itineraryDetailTOList=" + itineraryDetailTOList +
                '}';
    }
}
