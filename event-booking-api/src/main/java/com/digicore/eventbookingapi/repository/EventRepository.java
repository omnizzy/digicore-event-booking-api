package com.digicore.eventbookingapi.repository;

import com.digicore.eventbookingapi.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, String> {
}
