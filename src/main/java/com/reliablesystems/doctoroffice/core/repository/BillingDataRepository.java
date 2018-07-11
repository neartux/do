package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.BillingData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillingDataRepository extends JpaRepository<BillingData, Long> {
}
