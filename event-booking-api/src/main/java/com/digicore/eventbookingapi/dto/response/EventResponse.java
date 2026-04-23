package com.digicore.eventbookingapi.dto.response;

import com.digicore.eventbookingapi.enums.EventStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Response payload for event details")
public class EventResponse {

    @Schema(description = "Event ID", example = "evt_12345")
    private String id;

    @Schema(description = "Title of event", example = "Tech Conference 2026")
    private String title;

    @Schema(description = "Event description", example = "Annual tech gathering")
    private String description;

    @Schema(description = "Event date and time")
    private LocalDateTime date;

    @Schema(description = "Venue of event", example = "Lagos Convention Center")
    private String venue;

    @Schema(description = "Total number of seats available", example = "100")
    private int totalSeats;

    @Schema(description = "Number of seats already booked", example = "45")
    private int bookedSeats;

    @Schema(description = "Current event status", example = "OPEN")
    private EventStatus status;
}