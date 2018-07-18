package com.reliablesystems.doctoroffice.core.service.bloodtype;

import com.reliablesystems.doctoroffice.core.dao.bloodtype.BloodTypeDAO;
import com.reliablesystems.doctoroffice.core.domain.BloodType;
import com.reliablesystems.doctoroffice.core.exception.BackEndException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(value = "doTransactionManager", propagation = Propagation.REQUIRED, rollbackFor = BackEndException.class)
public class BloodTypeServiceImpl implements BloodTypeService {
    @Autowired
    private BloodTypeDAO bloodTypeDAO;

    /**
     * Query to find all bloodtype of the system
     *
     * @return List of {@link BloodType}
     */
    @Override
    public List<BloodType> findAllBloodTypes() {
        return bloodTypeDAO.findAllBloodTypes();
    }
}
