package com.InternShip.InternShip.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="mentor")
@Data
public class Mentor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mentor_id")
    private Integer mentorId;
    @Column(name="mentor_name")
    private String mentorName;
    @Column(name="number_ofproject_mentored")
    private Integer numberOfprojectMentored;
}
