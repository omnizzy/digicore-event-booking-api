package com.digicore.eventbookingapi.repository;

import com.digicore.eventbookingapi.entity.Booking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, String> {

    List<Booking> findByEventId(String eventId);

    Optional<Booking> findByEventIdAndAttendeeEmail(String eventId, String attendeeEmail);

    Page<Booking> findByEventId(String eventId, Pageable pageable);
}