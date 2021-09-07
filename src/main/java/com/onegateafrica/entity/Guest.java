package com.onegateafrica.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Guest {
    private String guestName;
    private String guestEmail;
    private String invitationStatus;
}
