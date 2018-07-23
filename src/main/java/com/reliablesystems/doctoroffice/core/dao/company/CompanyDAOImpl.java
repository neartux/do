package com.reliablesystems.doctoroffice.core.dao.company;

import com.reliablesystems.doctoroffice.core.to.company.CompanyTO;
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
public class CompanyDAOImpl implements CompanyDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Query to find all companies
     *
     * @return List of companies
     */
    @Override
    public List<CompanyTO> findAllCompanies() {
        String sql = "select company.id,company.personaldataid,company.locationdataid,company.statusid,company.description,personaldata.firstname,personaldata.lastname," +
                " locationdata.address,locationdata.cellphone,locationdata.phone,locationdata.email" +
                " from company" +
                " inner join personaldata on company.personaldataid = personaldata.id" +
                " inner join locationdata on company.locationdataid = locationdata.id";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(CompanyTO.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
