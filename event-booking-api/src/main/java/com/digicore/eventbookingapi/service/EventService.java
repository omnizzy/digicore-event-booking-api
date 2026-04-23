package com.digicore.eventbookingapi.service;

import com.digicore.eventbookingapi.dto.request.BookingRequest;
import com.digicore.eventbookingapi.dto.request.CreateEventRequest;
import com.digicore.eventbookingapi.dto.response.BookingResponse;
import com.digicore.eventbookingapi.dto.response.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EventService {

    EventResponse createEvent(CreateEventRequest request);

    Page<EventResponse> getAllEvents(Pageable pageable);

    EventResponse getEventById(String eventId);

    BookingResponse bookEvent(String eventId, BookingRequest request);

    void cancelBooking(String bookingId);

    Page<BookingResponse> getBookingsByEvent(String eventId, Pageable pageable);
}
