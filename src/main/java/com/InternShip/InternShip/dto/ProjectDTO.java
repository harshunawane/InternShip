package com.InternShip.InternShip.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ProjectDTO {

    private Integer ProjectId;

    private String projectName;
    private Integer ideaOwner;
    private LocalDate releaseDate;

    private MentorDTO mentordto;
}
