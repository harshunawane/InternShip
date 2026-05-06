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
    @Column(name="project_id")
    private Integer projectId;
@Column(name="project_name")
    private String projectName;
@Column(name="idea_owner")
    private Integer ideaOwner;
@Column(name="release_date")
    private LocalDate releaseDate;

    @ManyToOne
    @JoinColumn(name ="mentor_id")
    private Mentor mentor;

}
