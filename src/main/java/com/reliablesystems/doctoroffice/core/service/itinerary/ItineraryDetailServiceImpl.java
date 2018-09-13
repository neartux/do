package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.dao.itinerary.ItineraryDAO;
import com.reliablesystems.doctoroffice.core.domain.EventType;
import com.reliablesystems.doctoroffice.core.domain.Itinerary;
import com.reliablesystems.doctoroffice.core.domain.ItineraryDetail;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.EventTypeRespository;
import com.reliablesystems.doctoroffice.core.repository.ItineraryDetailRepository;
import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class ItineraryDetailServiceImpl implements ItineraryDetailService {
    @Autowired
    private ItineraryDetailRepository itineraryDetailRepository;
    @Autowired
    private EventTypeRespository eventTypeRespository;
    @Autowired
    private ItineraryDAO itineraryDAO;

    /**
     * Validate and create detail of a itinerary
     *
     * @param itineraryTO Information of itinerary detail
     */
    @Override
    public void createItineraryDetail(ItineraryTO itineraryTO) {
        // Find Type of Event
        EventType eventType = eventTypeRespository.findOne(itineraryTO.getEventTypeId());
        // Validate event is not null
        if (eventType == null) {
            throw new BackEndException("Event Not Found");
        }
        // Validate dates
        Boolean isValidDates = isItineraryTimeAvailable(itineraryTO.getItineraryId(), itineraryTO.getStartsAt(), itineraryTO.getEndsAt());
        if (isValidDates == null || !isValidDates) {
            throw new BackEndException("Itinerary dates are invalids");
        }
        // Create detail
        createDetail(itineraryTO);
    }

    /**
     * Method to create itinerary detail
     *
     * @param itineraryTO Itinerary detail data
     */
    private void createDetail(ItineraryTO itineraryTO) {
        ItineraryDetail itineraryDetail = new ItineraryDetail();
        itineraryDetail.setItinerary(new Itinerary(itineraryTO.getItineraryId()));
        itineraryDetail.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        itineraryDetail.setEventType(new EventType(itineraryTO.getEventTypeId()));
        itineraryDetail.setStartDate(itineraryTO.getStartsAt());
        itineraryDetail.setEndDate(itineraryTO.getEndsAt());
        itineraryDetail.setCreatedAt(DateUtil.nowTimestamp());
        itineraryDetailRepository.save(itineraryDetail);
    }

    /**
     * Method to update a itinerary detail
     *
     * @param itineraryTO Information of itinerary
     */
    @Override
    public void updateDetail(ItineraryTO itineraryTO) {
        // Validate dates
        Boolean isValidDates = isItineraryTimeAvailable(itineraryTO.getItineraryId(), itineraryTO.getStartsAt(), itineraryTO.getEndsAt());
        if (isValidDates == null || !isValidDates) {
            throw new BackEndException("Itinerary dates are invalids");
        }
        // Find itinerary detail
        ItineraryDetail itineraryDetail = itineraryDetailRepository.findOne(itineraryTO.getItineraryDetailId());
        // Validate detail
        if (itineraryDetail == null) {
            throw new BackEndException("Invalid itinerary with id:" + itineraryTO.getItineraryDetailId());
        }
        itineraryDetail.setEventType(new EventType(itineraryTO.getEventTypeId()));
        itineraryDetail.setStartDate(itineraryTO.getStartsAt());
        itineraryDetail.setEndDate(itineraryTO.getEndsAt());
        itineraryDetailRepository.save(itineraryDetail);
    }

    /**
     * Method to inactivate detail by id
     *
     * @param id Id detail
     */
    @Override
    public void deleteDetail(Long id) {
        // Find itinerary detail
        ItineraryDetail itineraryDetail = itineraryDetailRepository.findOne(id);
        // Validate detail
        if (itineraryDetail == null) {
            throw new BackEndException("Invalid itinerary with id:" + id);
        }
        itineraryDetail.setStatus(new Status(StatusKeys.INACTIVE_STATUS));
        itineraryDetailRepository.save(itineraryDetail);
    }

    /**
     * Method to verify if a itinerary date is available
     *
     * @param itineraryId Itinerary id
     * @param startDate Start date of period
     * @param endDate End date of period
     * @return Boolean with the result
     */
    @Override
    public Boolean isItineraryTimeAvailable(Long itineraryId, Date startDate, Date endDate) {
        return itineraryDAO.isItineraryTimeAvailable(itineraryId, startDate, endDate);
    }
}
