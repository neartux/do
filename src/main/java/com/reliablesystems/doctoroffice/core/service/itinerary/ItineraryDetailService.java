package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;

import java.util.Date;

public interface ItineraryDetailService {
    void createItineraryDetail(ItineraryTO itineraryTO);

    void updateDetail(ItineraryTO itineraryTO);

    void deleteDetail(Long id);

    Boolean isItineraryTimeAvailable(Long itineraryId, Date startDate, Date endDate);
}
