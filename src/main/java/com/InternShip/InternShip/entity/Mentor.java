package com.InternShip.InternShip.entity;


import jakarta.persistence.Entity;

@Entity
public class Mentor {

    Integer mentorId;
    String mentorName;
    Integer numberOfProjectMentored;
}
