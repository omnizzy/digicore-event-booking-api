package com.digicore.eventbookingapi.controller;

import com.digicore.eventbookingapi.dto.request.BookingRequest;
import com.digicore.eventbookingapi.dto.response.ApiResponse;
import com.digicore.eventbookingapi.dto.response.BookingResponse;
import com.digicore.eventbookingapi.service.EventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@Tag(name = "Booking API", description = "Endpoints for managing bookings")
public class BookingController {

    private final EventService eventService;

    @Operation(summary = "Book an event seat")
    @PostMapping("/events/{eventId}/bookings")
    public ApiResponse<BookingResponse> bookEvent(
            @Parameter(description = "Event ID")
            @PathVariable String eventId,
            @Valid @RequestBody BookingRequest request
    ) {

        return ApiResponse.<BookingResponse>builder()
                .success(true)
                .message("Booking successful")
                .data(eventService.bookEvent(eventId, request))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Operation(summary = "Cancel a booking")
    @DeleteMapping("/bookings/{bookingId}")
    public ApiResponse<Void> cancelBooking(
            @Parameter(description = "Booking ID")
            @PathVariable String bookingId
    ) {

        eventService.cancelBooking(bookingId);

        return ApiResponse.<Void>builder()
                .success(true)
                .message("Booking cancelled successfully")
                .data(null)
                .timestamp(LocalDateTime.now())
                .build();
    }
}