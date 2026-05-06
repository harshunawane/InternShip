package com.InternShip.InternShip.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {

    private Integer projectId;

    private String projectName;
    private Integer ideaOwner;
    private LocalDate releaseDate;

    private MentorDTO mentordto;
}
