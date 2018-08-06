package com.reliablesystems.doctoroffice.core.dao.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryDetailTO;

import java.util.Date;
import java.util.List;

public interface ItineraryDAO {
    List<ItineraryDetailTO> findItineraryByDoctorsOfficeAndDate(long doctorOfficeId, Date startDate, Date endDate);
}
