package com.reliablesystems.doctoroffice.core.dao.doctor;

import com.reliablesystems.doctoroffice.core.to.doctor.DoctorTO;
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
public class DoctorDAOImpl implements DoctorDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Query to find all doctors of the system
     *
     * @param companyId Company that doctor is
     * @param ofset Search start
     * @param limit Search limit
     * @param search Word
     * @return List of {@link DoctorTO}
     */
    @Override
    public List<DoctorTO> findDoctorsByCompany(long companyId, int ofset, int limit, String search) {
        String sql = "select doctor.id,doctor.personaldataid,doctor.locationdataid,doctor.professionalcard,doctor.profileimage," +
                " personaldata.firstname,personaldata.lastname,personaldata.birthdate,personaldata.sex," +
                " locationdata.address,locationdata.zipcode,locationdata.phone,locationdata.cellphone,locationdata.email" +
                " from doctor" +
                " inner join personaldata on doctor.personaldataid = personaldata.id" +
                " inner join locationdata on doctor.locationdataid = locationdata.id" +
                " where doctor.companyid = ?" +
                " and doctor.statusid = " + StatusKeys.ACTIVE_STATUS;
        if(search != null && !search.isEmpty()) {
            sql += " and ((upper(personaldata.firstname) like upper('%"+search+"%')) or (upper(personaldata.lastname) like upper('%"+search+"%')) or (upper(doctor.professionalcard) like upper('%"+search+"%')))";
        }
        sql += " order by personaldata.firstname" +
                " offset " + ofset + " limit " + limit;

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DoctorTO.class), companyId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Query to get total of doctors in the systems
     *
     * @param companyId Company that doctor is
     * @param search Word to search
     * @return Number of elements
     */
    @Override
    public int findDoctorsCountByCompany(long companyId, String search) {
        String sql = "select count(*)" +
                " from doctor" +
                " inner join personaldata on doctor.personaldataid = personaldata.id" +
                " inner join locationdata on doctor.locationdataid = locationdata.id" +
                " where doctor.companyid = ?" +
                " and doctor.statusid = " + StatusKeys.ACTIVE_STATUS;
        if(search != null && !search.isEmpty()) {
            sql += " and ((upper(personaldata.firstname) like upper('%"+search+"%')) or (upper(personaldata.lastname) like upper('%"+search+"%')) or (upper(doctor.professionalcard) like upper('%"+search+"%')))";
        }

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, companyId);
        } catch (EmptyResultDataAccessException e) {
            return NumberUtil.ZERO_INT;
        }
    }
}
