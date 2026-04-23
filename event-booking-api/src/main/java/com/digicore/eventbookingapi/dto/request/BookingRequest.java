package com.digicore.eventbookingapi.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Request payload for booking a seat at an event")
public class BookingRequest {

    @Schema(
            description = "Name of the attendee",
            example = "Nathan Adebesin",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Attendee name is required")
    private String attendeeName;

    @Schema(
            description = "Email address of the attendee (must be unique per event)",
            example = "nathan@example.com",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @NotBlank(message = "Attendee email is required")
    @Email(message = "Invalid email format")
    private String attendeeEmail;
}