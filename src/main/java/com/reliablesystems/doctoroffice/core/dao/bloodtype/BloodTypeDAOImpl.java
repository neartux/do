package com.reliablesystems.doctoroffice.core.dao.bloodtype;

import com.reliablesystems.doctoroffice.core.domain.BloodType;
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
public class BloodTypeDAOImpl implements BloodTypeDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Query to find all bloodtype of the system
     *
     * @return List of {@link BloodType}
     */
    @Override
    public List<BloodType> findAllBloodTypes() {
        String sql = "select *" +
                " from bloodtype" +
                " order by description";
        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(BloodType.class));
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
