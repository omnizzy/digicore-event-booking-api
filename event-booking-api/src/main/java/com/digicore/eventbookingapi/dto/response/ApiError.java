package com.digicore.eventbookingapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

@Schema(description = "Standard error response structure")
public record ApiError(

        @Schema(description = "Error message", example = "Event not found")
        String message,

        @Schema(description = "HTTP status code", example = "404")
        int status,

        @Schema(description = "Timestamp of error occurrence")
        LocalDateTime timestamp

) {}