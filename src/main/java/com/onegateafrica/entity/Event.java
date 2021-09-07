package com.onegateafrica.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    @Id
    private String id;
    private String name;
    private String description;
    private String banner;
    private String eventDate;
    private String eventTime;
    private String place;
    private String status;
    private List<Guest> guests = new ArrayList<>();
    //private List<User> participants = new ArrayList<User>();
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
