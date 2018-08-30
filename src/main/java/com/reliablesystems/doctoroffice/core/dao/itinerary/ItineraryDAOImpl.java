package com.reliablesystems.doctoroffice.core.dao.itinerary;

import com.reliablesystems.doctoroffice.core.to.itinerary.ItineraryTO;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class ItineraryDAOImpl implements ItineraryDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Query to find itineerary by doctorsoffice
     *
     * @param doctorOfficeId Dodctorsofficeid
     * @param startDate Start period
     * @param endDate End Perior
     * @return List itinerary
     */
    @Override // TODO esta mal, no debe ser asi el query, debe ser por detalle no por itinerario y al fecha de coparacion tambien esta mal
    public List<ItineraryTO> findItineraryByDoctorsOfficeAndDate(long doctorOfficeId, Date startDate, Date endDate) {
        String sql = "select itinerary.id as itineraryid,itinerary.itinerarydate,itinerarydetail.id as detailid,eventtype.id as eventTypeid,eventtype.description as title," +
                " itinerarydetail.startdate as startsAt,itinerarydetail.enddate as endsAt" +
                " from itinerary" +
                " left join itinerarydetail on itinerary.id = itinerarydetail.itineraryid and itinerarydetail.statusid = " + StatusKeys.ACTIVE_STATUS +
                " left join eventtype on itinerarydetail.eventtypeid = eventtype.id" +
                " where itinerary.doctorsofficeid = ?" +
                " and itinerary.itinerarydate between ? and ?" +
                " and itinerary.statusid = " + StatusKeys.ACTIVE_STATUS +
                " order by itinerarydetail.startdate";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ItineraryTO.class), doctorOfficeId, startDate, endDate);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
