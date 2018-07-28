package com.reliablesystems.doctoroffice.core.dao.doctorsoffice;

import com.reliablesystems.doctoroffice.core.to.doctorsoffice.DoctorsOfficeTO;
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
public class DoctorsOfficeDAOImpl implements DoctorsOfficeDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Query to find all doctorsoffice of the system
     *
     * @param companyId Company that doctorsoffice is
     * @param ofset Search start
     * @param limit Search limit
     * @param search Word
     * @return List of {@link DoctorsOfficeTO}
     */
    @Override
    public List<DoctorsOfficeTO> findDoctorsOfficesByCompany(long companyId, int ofset, int limit, String search) {
        String sql = "select doctorsoffice.id,doctorsoffice.doctorid,doctorsoffice.description,doctor.profileimage,doctor.professionalcard," +
                " personaldata.firstname,personaldata.lastname,personaldata.sex,personaldata.birthdate," +
                " locationdata.address,locationdata.zipcode,locationdata.cellphone,locationdata.phone,locationdata.email" +
                " from doctorsoffice" +
                " inner join doctor on doctorsoffice.doctorid = doctor.id" +
                " inner join personaldata on doctor.personaldataid = personaldata.id" +
                " inner join locationdata on doctor.locationdataid = locationdata.id" +
                " where doctorsoffice.companyid = ?" +
                " and doctorsoffice.statusid = " + StatusKeys.ACTIVE_STATUS;
        if(search != null && !search.isEmpty()) {
            sql += " and ((upper(personaldata.firstname) like upper('%"+search+"%')) or (upper(personaldata.lastname) like upper('%"+search+"%')) or (upper(doctorsoffice.description) like upper('%"+search+"%')))";
        }
        sql += " order by doctorsoffice.description" +
                " offset " + ofset + " limit " + limit;

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(DoctorsOfficeTO.class), companyId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }

    /**
     * Query to get total of doctorsoffices in the systems
     *
     * @param companyId Company that doctorsoffices is
     * @param search Word to search
     * @return Number of elements
     */
    @Override
    public int findDoctorsOfficesCountByCompany(long companyId, String search) {
        String sql = "select count(*)" +
                " from doctorsoffice" +
                " inner join doctor on doctorsoffice.doctorid = doctor.id" +
                " inner join personaldata on doctor.personaldataid = personaldata.id" +
                " inner join locationdata on doctor.locationdataid = locationdata.id" +
                " where doctorsoffice.companyid = ?" +
                " and doctorsoffice.statusid = " + StatusKeys.ACTIVE_STATUS;
        if(search != null && !search.isEmpty()) {
            sql += " and ((upper(personaldata.firstname) like upper('%"+search+"%')) or (upper(personaldata.lastname) like upper('%"+search+"%')) or (upper(doctorsoffice.description) like upper('%"+search+"%')))";
        }

        try {
            return jdbcTemplate.queryForObject(sql, Integer.class, companyId);
        } catch (EmptyResultDataAccessException e) {
            return NumberUtil.ZERO_INT;
        }
    }
}
