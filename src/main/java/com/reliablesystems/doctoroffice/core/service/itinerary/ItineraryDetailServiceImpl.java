package com.reliablesystems.doctoroffice.core.service.itinerary;

import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.ItineraryDetailRepository;
import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class ItineraryDetailServiceImpl implements ItineraryDetailService {
    @Autowired
    private ItineraryDetailRepository itineraryDetailRepository;

    public void createItineraryDetail(ItineraryTO itineraryTO) {
        // Validate date
    }
}
