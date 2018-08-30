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

    public long createItinerary(ItineraryTO itineraryTO, long userId) {
        // Find itinerary
        Itinerary itinerary = itineraryRepository.findByDoctorsOfficeIdAndStatusId(itineraryTO.getDoctorOfficeId(), StatusKeys.ACTIVE_STATUS);
        if (itinerary == null) {
            itinerary = create(itineraryTO.getDoctorOfficeId(), userId);
        }
        itineraryTO.setItineraryId(itinerary.getId());
        // Create details itinerary TODO pendiente

        return itinerary.getId();

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
}
