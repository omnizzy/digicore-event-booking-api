package com.digicore.eventbookingapi.entity;

import com.digicore.eventbookingapi.enums.EventStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String title;

    private String description;

    private LocalDateTime date;

    private String venue;

    private int totalSeats;

    private int bookedSeats;

    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Version
    private Long version;
}
