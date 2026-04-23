package com.digicore.eventbookingapi.controller;

import com.digicore.eventbookingapi.dto.request.CreateEventRequest;
import com.digicore.eventbookingapi.dto.response.ApiResponse;
import com.digicore.eventbookingapi.dto.response.BookingResponse;
import com.digicore.eventbookingapi.dto.response.EventResponse;
import com.digicore.eventbookingapi.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/events")
@RequiredArgsConstructor
@Tag(name = "Event API", description = "Endpoints for managing events and bookings")
public class EventController {

    private final EventService eventService;

    @Operation(summary = "Create a new event")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<EventResponse> createEvent(
            @Valid @RequestBody CreateEventRequest request
    ) {
        return ApiResponse.<EventResponse>builder()
                .success(true)
                .message("Event created successfully")
                .data(eventService.createEvent(request))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Operation(summary = "Get all events (paginated)")
    @GetMapping
    public ApiResponse<Page<EventResponse>> getAllEvents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ApiResponse.<Page<EventResponse>>builder()
                .success(true)
                .message("Events fetched successfully")
                .data(eventService.getAllEvents(pageable))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Operation(summary = "Get event by ID")
    @GetMapping("/{id}")
    public ApiResponse<EventResponse> getEventById(
            @Parameter(description = "Event ID")
            @PathVariable String id
    ) {
        return ApiResponse.<EventResponse>builder()
                .success(true)
                .message("Event fetched successfully")
                .data(eventService.getEventById(id))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Operation(summary = "Get bookings for an event (paginated)")
    @GetMapping("/{eventId}/bookings")
    public ApiResponse<Page<BookingResponse>> getBookings(
            @PathVariable String eventId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return ApiResponse.<Page<BookingResponse>>builder()
                .success(true)
                .message("Bookings fetched successfully")
                .data(eventService.getBookingsByEvent(eventId, pageable))
                .timestamp(LocalDateTime.now())
                .build();
    }
}