package com.digicore.eventbookingapi.service.impl;

import com.digicore.eventbookingapi.dto.request.BookingRequest;
import com.digicore.eventbookingapi.dto.request.CreateEventRequest;
import com.digicore.eventbookingapi.dto.response.BookingResponse;
import com.digicore.eventbookingapi.dto.response.EventResponse;
import com.digicore.eventbookingapi.entity.Booking;
import com.digicore.eventbookingapi.entity.Event;
import com.digicore.eventbookingapi.enums.EventStatus;
import com.digicore.eventbookingapi.exception.*;
import com.digicore.eventbookingapi.repository.BookingRepository;
import com.digicore.eventbookingapi.repository.EventRepository;
import com.digicore.eventbookingapi.service.EventService;
import com.digicore.eventbookingapi.util.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final BookingRepository bookingRepository;

    @Override
    public EventResponse createEvent(CreateEventRequest request) {

        if (request.getTotalSeats() <= 0) {
            throw new BadRequestException("Total seats must be greater than 0");
        }

        if (request.getDate().isBefore(LocalDate.now().atStartOfDay())) {
            throw new BadRequestException("Event date must be in the future");
        }

        Event event = Mapper.toEvent(request);
        event.setBookedSeats(0);
        event.setStatus(EventStatus.OPEN);

        return Mapper.toEventResponse(eventRepository.save(event));
    }

    @Override
    public Page<EventResponse> getAllEvents(Pageable pageable) {
        return eventRepository.findAll(pageable)
                .map(Mapper::toEventResponse);
    }

    @Override
    public EventResponse getEventById(String eventId) {
        return Mapper.toEventResponse(getEventOrThrow(eventId));
    }

    @Transactional
    @Override
    public BookingResponse bookEvent(String eventId, BookingRequest request) {

        Event event = getEventOrThrow(eventId);

        if (event.getStatus() == EventStatus.CLOSED) {
            throw new EventClosedException("Event is closed");
        }

        if (event.getBookedSeats() >= event.getTotalSeats()) {
            throw new NoAvailableSeatsException("No available seats");
        }

        boolean alreadyBooked =
                bookingRepository.findByEventIdAndAttendeeEmail(eventId, request.getAttendeeEmail())
                        .isPresent();

        if (alreadyBooked) {
            throw new DuplicateBookingException("Email already booked this event");
        }

        Booking booking = Mapper.toBooking(
                eventId,
                request.getAttendeeName(),
                request.getAttendeeEmail()
        );

        Booking saved = bookingRepository.save(booking);

        event.setBookedSeats(event.getBookedSeats() + 1);

        if (event.getBookedSeats() >= event.getTotalSeats()) {
            event.setStatus(EventStatus.CLOSED);
        }

        eventRepository.save(event);

        return Mapper.toBookingResponse(saved);
    }

    @Transactional
    @Override
    public void cancelBooking(String bookingId) {

        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() ->
                        new NotFoundException("Booking not found: " + bookingId)
                );

        Event event = getEventOrThrow(booking.getEventId());

        bookingRepository.delete(booking);

        event.setBookedSeats(event.getBookedSeats() - 1);

        if (event.getStatus() == EventStatus.CLOSED &&
                event.getBookedSeats() < event.getTotalSeats()) {
            event.setStatus(EventStatus.OPEN);
        }

        eventRepository.save(event);
    }

    @Override
    public Page<BookingResponse> getBookingsByEvent(String eventId, Pageable pageable) {

        getEventOrThrow(eventId);

        return bookingRepository.findByEventId(eventId, pageable)
                .map(Mapper::toBookingResponse);
    }

    private Event getEventOrThrow(String eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() ->
                        new NotFoundException("Event not found: " + eventId)
                );
    }
}