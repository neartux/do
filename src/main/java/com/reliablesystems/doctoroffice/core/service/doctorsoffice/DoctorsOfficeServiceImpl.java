package com.reliablesystems.doctoroffice.core.service.doctorsoffice;

import com.reliablesystems.doctoroffice.core.dao.doctorsoffice.DoctorsOfficeDAO;
import com.reliablesystems.doctoroffice.core.domain.Doctor;
import com.reliablesystems.doctoroffice.core.domain.DoctorsOffice;
import com.reliablesystems.doctoroffice.core.domain.Status;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import com.reliablesystems.doctoroffice.core.repository.DoctorsOfficeRepository;
import com.reliablesystems.doctoroffice.core.service.company.CompanyService;
import com.reliablesystems.doctoroffice.core.service.doctor.DoctorService;
import com.reliablesystems.doctoroffice.core.to.doctorsoffice.DoctorsOfficeTO;
import com.reliablesystems.doctoroffice.core.utils.common.DateUtil;
import com.reliablesystems.doctoroffice.core.utils.common.StatusKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class DoctorsOfficeServiceImpl implements DoctorsOfficeService {
    @Autowired
    private DoctorsOfficeRepository doctorsOfficeRepository;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private DoctorsOfficeDAO doctorsOfficeDAO;

    /**
     * Metodo para encontrar un {@link DoctorsOffice} por su id
     *
     * @param id Id del registro
     * @return DoctorsOffice
     */
    @Override
    public DoctorsOffice findById(long id) {
        return doctorsOfficeRepository.findOne(id);
    }

    /**
     * Method to find all DoctorsOffice of the system
     *
     * @param companyId Company that DoctorsOffice is
     * @param ofset Search start
     * @param limit Search limit
     * @param search Word
     * @return List of {@link DoctorsOffice}
     */
    @Override
    public List<DoctorsOfficeTO> findDoctorsByCompany(long companyId, int ofset, int limit, String search) {
        return doctorsOfficeDAO.findDoctorsOfficesByCompany(companyId, ofset, limit, search);
    }

    /**
     * Method to find all officces of a company
     *
     * @param companyId Company id
     * @return List of {@link DoctorsOfficeTO}
     */
    @Override
    public List<DoctorsOfficeTO> findDoctorsOfficesByCompany(long companyId) {
        return doctorsOfficeDAO.findDoctorsOfficesByCompany(companyId);
    }

    /**
     * Method to get total of DoctorsOffice in the systems
     *
     * @param companyId Company that DoctorsOffice is
     * @param search Word to search
     * @return Number of elements
     */
    @Override
    public int findDoctorsOfficesCountByCompany(long companyId, String search) {
        return doctorsOfficeDAO.findDoctorsOfficesCountByCompany(companyId, search);
    }

    /**
     * Method to create a new doctorsOffice
     *
     * @param doctorsOffice {@link DoctorsOffice} Data
     * @return Id generated
     */
    @Override
    public Long createDoctorsOffcie(DoctorsOffice doctorsOffice) {
        // Validate if doctor exist
        if(doctorService.findDoctorById(doctorsOffice.getDoctor().getId()) == null) {
            throw new BackEndException("Doctor Not Found");
        }
        // Validate if company exist
        if (companyService.findCompanyById(doctorsOffice.getCompany().getId()) == null) {
            throw new BackEndException("Company Not Found");
        }
        doctorsOffice.setStatus(new Status(StatusKeys.ACTIVE_STATUS));
        doctorsOffice.setCreatedAt(DateUtil.nowTimestamp());
        doctorsOfficeRepository.save(doctorsOffice);

        return doctorsOffice.getId();
    }

    /**
     * Method to update a {@link DoctorsOffice}
     *
     * @param doctorsOfficeTO Data
     */
    @Override
    public void updateDoctorsOffice(DoctorsOfficeTO doctorsOfficeTO) {
        // Find office
        DoctorsOffice doctorsOffice = findById(doctorsOfficeTO.getId());
        // Validate
        if (doctorsOffice == null) {
            throw new BackEndException("DoctorsOffice Not Found");
        }
        // Validate if doctor exist
        if(doctorService.findDoctorById(doctorsOfficeTO.getDoctorId()) == null) {
            throw new BackEndException("Doctor Not Found");
        }
        doctorsOffice.setDescription(doctorsOfficeTO.getDescription());
        doctorsOffice.setDoctor(new Doctor(doctorsOfficeTO.getDoctorId()));

        doctorsOfficeRepository.save(doctorsOffice);
    }

    /**
     * Method to delete one {@link DoctorsOffice} by id
     *
     * @param id Id of registre
     */
    @Override
    public void inactiveDoctorsOffice(long id) {
        // Find office
        DoctorsOffice doctorsOffice = findById(id);
        // Validate
        if (doctorsOffice == null) {
            throw new BackEndException("DoctorsOffice Not Found");
        }
        doctorsOffice.setStatus(new Status(StatusKeys.INACTIVE_STATUS));
        doctorsOfficeRepository.save(doctorsOffice);
    }
}
