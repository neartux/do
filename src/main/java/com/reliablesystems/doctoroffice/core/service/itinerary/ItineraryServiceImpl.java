package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.dao.itinerary.ItineraryDAO;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.manager.itinerary.ItineraryManager;
import com.reliablesystems.doctoroffice.core.repository.ItineraryRepository;
import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryDetailTO;
import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;
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

    /**
     * Method to find a itinerary of a office by id and date
     *
     * @param doctorsOfficeId Id office
     * @param startDate Star date of the period
     * @param endDate End date of the period
     * @return Itinerary
     */
    @Override
    public ItineraryTO findItineraryByOfficeAndDate(long doctorsOfficeId, Date startDate, Date endDate) {
        List<ItineraryDetailTO> list = itineraryDAO.findItineraryByDoctorsOfficeAndDate(doctorsOfficeId, startDate, endDate);
        System.out.println("list.size() = " + list.size());
        ItineraryManager itineraryManager = new ItineraryManager();
        itineraryManager.setItineraryDetailTOList(list);
        itineraryManager.process();
        return itineraryManager.getResult();
    }
}
