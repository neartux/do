package com.reliablesystems.doctoroffice.core.dao.bloodtype;

import com.reliablesystems.doctoroffice.core.domain.BloodType;

import java.util.List;

public interface BloodTypeDAO {
    List<BloodType> findAllBloodTypes();
}
