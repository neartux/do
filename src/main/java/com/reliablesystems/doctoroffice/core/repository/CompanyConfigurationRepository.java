package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.CompanyConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyConfigurationRepository extends JpaRepository<CompanyConfiguration, Long> {
}
