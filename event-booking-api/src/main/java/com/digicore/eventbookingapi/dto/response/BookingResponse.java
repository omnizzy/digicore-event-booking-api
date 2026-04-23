package com.digicore.eventbookingapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@Schema(description = "Response payload for a booking")
public class BookingResponse {

    @Schema(description = "Booking ID", example = "bkg_12345")
    private String id;

    @Schema(description = "Event ID associated with booking", example = "evt_67890")
    private String eventId;

    @Schema(description = "Name of attendee", example = "John Doe")
    private String attendeeName;

    @Schema(description = "Email of attendee", example = "john.doe@example.com")
    private String attendeeEmail;

    @Schema(description = "Timestamp when booking was made")
    private LocalDateTime bookedAt;
}