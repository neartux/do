package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;

import java.util.Date;
import java.util.List;

public interface ItineraryService {
    List<ItineraryTO> findItineraryByOfficeAndDate(long doctorsOfficeId, Date startDate, Date endDate);
}
