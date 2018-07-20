package com.reliablesystems.doctoroffice.core.dao.patient;

import com.reliablesystems.doctoroffice.core.to.patient.PatientTO;
import com.reliablesystems.doctoroffice.core.utils.common.NumberUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PatientDAOImpl implements PatientDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Query to find all patient of the system
     *
     * @param ofset Search start
     * @param limit Search limit
     * @param search Word
     * @return List of {@link PatientTO}
     */
    @Override
    public List<PatientTO> findAllPatients(int ofset, int limit, String search) {
        String sql = "select patient.id,patient.expedient,patient.profileimage,personaldata.firstname,personaldata.lastname,personaldata.birthdate,personaldata.sex,personaldata.civilstatus," +
                "bloodtype.description as bloodtype,bloodtype.id as bloodTypeId,locationdata.address,locationdata.zipcode,locationdata.cellphone,locationdata.phone,locationdata.email,city.description" +
                " from patient" +
                " inner join personaldata on patient.personaldataid = personaldata.id" +
                " left join bloodtype on personaldata.bloodtypeid = bloodtype.id" +
                " inner join locationdata on patient.locationdataid = locationdata.id" +
                " left join city on locationdata.cityid = city.id" +
                " where patient.statusid = " + StatusKeys.ACTIVE_STATUS;
        if(search != null && !search.isEmpty()) {
            sql += " and ((upper(personaldata.firstname) like upper('%"+search+"%')) or (upper(personaldata.lastname) like upper('%"+search+"%')) or (upper(patient.expedient) like upper('%"+search+"%')))";
        }
        sql += " order by personaldata.firstname" +
                " offset " + ofset + " limit " + limit;

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(PatientTO.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Query to get total of patient in the systems, with parameters
     *
     * @param search Word to search
     * @return Number of elements
     */
    @Override
    public int finAllPatientsCount(String search) {
        String sql = "select count(*)" +
                " from patient" +
                " inner join personaldata on patient.personaldataid = personaldata.id" +
                " left join bloodtype on personaldata.bloodtypeid = bloodtype.id" +
                " inner join locationdata on patient.locationdataid = locationdata.id" +
                " left join city on locationdata.cityid = city.id" +
                " where patient.statusid = " + StatusKeys.ACTIVE_STATUS;
        if(search != null && !search.isEmpty()) {
            sql += " and ((upper(personaldata.firstname) like upper('%"+search+"%')) or (upper(personaldata.lastname) like upper('%"+search+"%')) or (upper(patient.expedient) like upper('%"+search+"%')))";
        }

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class);
        } catch (EmptyResultDataAccessException e) {
            return NumberUtil.ZERO_INT;
        }
    }

    /**
     * Query to find max id of patients
     *
     * @return Max id
     */
    @Override
    public Long findMaxPatientId() {
        String sql = "select case when max(id) is null then " + NumberUtil.ZERO_LONG + " else max(id) end" +
                " from patient";

        try {
            return jdbcTemplate.queryForObject(sql, Long.class);
        } catch (EmptyResultDataAccessException e) {
            return NumberUtil.ZERO_LONG;
        }
    }
}
