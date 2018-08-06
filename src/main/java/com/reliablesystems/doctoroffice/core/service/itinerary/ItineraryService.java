package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;

import java.util.Date;

public interface ItineraryService {
    ItineraryTO findItineraryByOfficeAndDate(long doctorsOfficeId, Date startDate, Date endDate);
}
