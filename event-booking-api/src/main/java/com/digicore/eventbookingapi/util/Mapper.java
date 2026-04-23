package com.digicore.eventbookingapi.util;

import com.digicore.eventbookingapi.dto.request.CreateEventRequest;
import com.digicore.eventbookingapi.dto.response.BookingResponse;
import com.digicore.eventbookingapi.dto.response.EventResponse;
import com.digicore.eventbookingapi.entity.Booking;
import com.digicore.eventbookingapi.entity.Event;

import java.time.LocalDateTime;

public class Mapper {

    public static Event toEvent(CreateEventRequest request) {
        return Event.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .date(request.getDate())
                .venue(request.getVenue())
                .totalSeats(request.getTotalSeats())
                .bookedSeats(0)
                .status(null)
                .build();
    }

    public static EventResponse toEventResponse(Event event) {
        return EventResponse.builder()
                .id(event.getId())
                .title(event.getTitle())
                .description(event.getDescription())
                .date(event.getDate())
                .venue(event.getVenue())
                .totalSeats(event.getTotalSeats())
                .bookedSeats(event.getBookedSeats())
                .status(event.getStatus())
                .build();
    }

    public static Booking toBooking(String eventId, String name, String email) {
        return Booking.builder()
                .eventId(eventId)
                .attendeeName(name)
                .attendeeEmail(email)
                .bookedAt(LocalDateTime.now())
                .build();
    }

    public static BookingResponse toBookingResponse(Booking booking) {
        return BookingResponse.builder()
                .id(booking.getId())
                .eventId(booking.getEventId())
                .attendeeName(booking.getAttendeeName())
                .attendeeEmail(booking.getAttendeeEmail())
                .bookedAt(booking.getBookedAt())
                .build();
    }
}