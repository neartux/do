package com.reliablesystems.doctoroffice.core.manager.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryDetailTO;
import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;
import com.reliablesystems.doctoroffice.core.utils.common.NumberUtil;

import java.util.List;

public class ItineraryManager {
    private List<ItineraryDetailTO> itineraryDetailTOList;
    private ItineraryTO itineraryTO;

    public ItineraryManager() {}

    /**
     * Set list of {@link ItineraryDetailTO}
     *
     * @param itineraryDetailTOList List
     */
    public void setItineraryDetailTOList(List<ItineraryDetailTO> itineraryDetailTOList) {
        this.itineraryDetailTOList = itineraryDetailTOList;
    }

    /**
     * Configure {@link ItineraryTO} and {@link ItineraryDetailTO}
     */
    public void process() {
        if (itineraryDetailTOList != null && itineraryDetailTOList.size() > NumberUtil.ZERO_INT) {
            for (ItineraryDetailTO itineraryDetailTO : itineraryDetailTOList) {
                if (this.itineraryTO == null) {
                    this.itineraryTO = new ItineraryTO();
                    this.itineraryTO.setId(itineraryDetailTO.getItineraryId());
                    this.itineraryTO.setItineraryDate(itineraryDetailTO.getItineraryDate());
                    break;
                }
            }
            this.itineraryTO.setItineraryDetailTOList(itineraryDetailTOList);
        }
    }

    /**
     * Return {@link ItineraryTO} with details
     *
     * @return ItineraryTO
     */
    public ItineraryTO getResult() {
        return this.itineraryTO;
    }
}
