package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.dao.itinerary.ItineraryDAO;
import com.reliablesystems.doctoroffice.core.domain.DoctorsOffice;
import com.reliablesystems.doctoroffice.core.domain.Itinerary;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.domain.User;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.ItineraryRepository;
import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class ItineraryServiceImpl implements ItineraryService {
    @Autowired
    private ItineraryRepository itineraryRepository;
    @Autowired
    private ItineraryDAO itineraryDAO;
    @Autowired
    private ItineraryDetailService itineraryDetailService;

    /**
     * Method to validate and create a itinerary and detail
     *
     * @param itineraryTO Itinerary information
     * @param userId User that create itinerary
     * @return Id itinerary
     */
    @Override
    public Long createItinerary(ItineraryTO itineraryTO, long userId) {
        // Find itinerary
        Itinerary itinerary = itineraryRepository.findByDoctorsOfficeIdAndStatusId(itineraryTO.getDoctorOfficeId(), StatusKeys.ACTIVE_STATUS);
        // If itinerary not exist, create
        if (itinerary == null) {
            itinerary = create(itineraryTO.getDoctorOfficeId(), userId);
        }
        itineraryTO.setItineraryId(itinerary.getId());
        // Create details itinerary
        itineraryDetailService.createItineraryDetail(itineraryTO);

        return itinerary.getId();
    }

    /**
     * Method to update a itinerary detail
     *
     * @param itineraryTO Information itinerary
     */
    @Override
    public void updateItinerary(ItineraryTO itineraryTO) {
       itineraryDetailService.updateDetail(itineraryTO);
    }

    /**
     * Method to inactivate detail by id
     *
     * @param id Id detail
     */
    @Override
    public void deleteItinerary(Long id) {
        itineraryDetailService.deleteDetail(id);
    }

    /**
     * Method to create a itinerary head
     *
     * @param doctorOfficeId Office doctor
     * @param userId User created
     * @return Itinerary created
     */
    private Itinerary create(long doctorOfficeId, long userId) {
        Itinerary itinerary = new Itinerary();
        itinerary.setDoctorsOffice(new DoctorsOffice(doctorOfficeId));
        itinerary.setUser(new User(userId));
        itinerary.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        itinerary.setCreatedAt(DateUtil.now());
        itineraryRepository.save(itinerary);
        return itinerary;
    }

    /**
     * Method to find a itinerary of a office by id and date
     *
     * @param doctorsOfficeId Id office
     * @param startDate Star date of the period
     * @param endDate End date of the period
     * @return Itinerary
     */
    @Override
    public List<ItineraryTO> findItineraryByOfficeAndDate(long doctorsOfficeId, Date startDate, Date endDate) {
        return itineraryDAO.findItineraryByDoctorsOfficeAndDate(doctorsOfficeId, startDate, endDate);
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
        return itineraryDetailService.isItineraryTimeAvailable(itineraryId, startDate, endDate);
    }
}
