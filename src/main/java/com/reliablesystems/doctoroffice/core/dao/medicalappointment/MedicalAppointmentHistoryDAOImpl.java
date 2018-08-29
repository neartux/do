package com.reliablesystems.doctoroffice.core.dao.medicalappointment;

import com.reliablesystems.doctoroffice.core.to.medicalappointment.MedicalAppointmentHistoryTO;
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
public class MedicalAppointmentHistoryDAOImpl implements MedicalAppointmentHistoryDAO {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("doDataSource")
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Method to find history to a patient
     *
     * @param patientId Patient id
     * @return List of history
     */
    @Override
    public List<MedicalAppointmentHistoryTO> findHistoryByPatientId(Long patientId) {
        String sql = "select medicalappointmenthistory.*,doctorsoffice.description as doctorsoffice,pdd.firstname || ' ' || pdd.lastname as doctor,personaldata.firstname || ' ' || personaldata.lastname as patient" +
                " from medicalappointmenthistory" +
                " inner join doctorsoffice on medicalappointmenthistory.doctorsofficeid = doctorsoffice.id" +
                " inner join doctor on doctorsoffice.doctorid = doctor.id" +
                " inner join personaldata pdd on doctor.personaldataid = pdd.id" +
                " inner join locationdata ldd on doctor.locationdataid = ldd.id" +
                " inner join usuario on medicalappointmenthistory.userid = usuario.id" +
                " inner join vitalsigns on medicalappointmenthistory.vitalsignsid = vitalsigns.id" +
                " inner join personaldata on medicalappointmenthistory.personaldataid = personaldata.id" +
                " inner join locationdata on medicalappointmenthistory.locationdataid = locationdata.id" +
                " where medicalappointmenthistory.patientid = ?" +
                " order by medicalappointmenthistory.startdate desc";

        try {
            return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(MedicalAppointmentHistoryTO.class), patientId);
        } catch (EmptyResultDataAccessException e) {
            return new ArrayList<>();
        }
    }
}
