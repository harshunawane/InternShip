package com.InternShip.InternShip.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="project")
@Data
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ProjectId;

    private String projectName;
    private Integer ideaOwner;
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name ="mentorId")
    private Mentor mentor;

}
