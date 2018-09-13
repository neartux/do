package com.reliablesystems.doctoroffice.core.dao.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;

import java.util.Date;
import java.util.List;

public interface ItineraryDAO {
    List<ItineraryTO> findItineraryByDoctorsOfficeAndDate(long doctorOfficeId, Date startDate, Date endDate);

    Boolean isItineraryTimeAvailable(Long itineraryId, Date startDate, Date endDate);
}
