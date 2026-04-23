package com.digicore.eventbookingapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Request payload for creating a new event")
public class CreateEventRequest {

    @Schema(
            description = "Title of the event",
            example = "Tech Conference 2026",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Title is required")
    private String title;

    @Schema(
            description = "Detailed description of the event",
            example = "A gathering of software engineers and tech enthusiasts",
            requiredMode = Schema.RequiredMode.NOT_REQUIRED
    )
    private String description;

    @Schema(
            description = "Date and time of the event (must be in the future)",
            example = "2026-12-31T10:00:00",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull(message = "Event date is required")
    @Future(message = "Event date must be in the future")
    private LocalDateTime date;

    @Schema(
            description = "Venue where the event will be held",
            example = "Eko Convention Centre, Lagos",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Venue is required")
    private String venue;

    @Schema(
            description = "Total number of seats available for the event",
            example = "100",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotNull
    @Min(value = 1, message = "Total seats must be greater than 0")
    private int totalSeats;
}