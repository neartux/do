package com.reliablesystems.doctoroffice.core.repository;

import com.reliablesystems.doctoroffice.core.domain.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {
}
