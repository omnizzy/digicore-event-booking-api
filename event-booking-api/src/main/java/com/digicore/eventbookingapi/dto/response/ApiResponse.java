package com.digicore.eventbookingapi.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Standard API response wrapper")
public class ApiResponse<T> {

    @Schema(description = "Indicates if request was successful", example = "true")
    private boolean success;

    @Schema(description = "Response message", example = "Request completed successfully")
    private String message;

    @Schema(description = "Response payload (generic)")
    private T data;

    @Schema(description = "Response timestamp")
    private LocalDateTime timestamp;
}