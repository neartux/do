package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;

import java.util.Date;
import java.util.List;

public interface ItineraryService {
    Long createItinerary(ItineraryTO itineraryTO, long userId);

    void updateItinerary(ItineraryTO itineraryTO);

    void deleteItinerary(Long id);

    List<ItineraryTO> findItineraryByOfficeAndDate(long doctorsOfficeId, Date startDate, Date endDate);

    Boolean isItineraryTimeAvailable(Long itineraryId, Date startDate, Date endDate);
}
