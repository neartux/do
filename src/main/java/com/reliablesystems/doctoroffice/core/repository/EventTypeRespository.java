package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.EventType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventTypeRespository extends JpaRepository<EventType, Long> {
}
