package com.InternShip.InternShip.service;

import com.InternShip.InternShip.dto.MentorDTO;
import com.InternShip.InternShip.dto.ProjectDTO;
import com.InternShip.InternShip.exception.InfyInternException;

import java.util.List;

public interface ProjectService {

    public Integer  allocateProject(ProjectDTO projectdto) throws  InfyInternException;
    public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws  InfyInternException;
    public void updateProjectMentor (Integer projectId, Integer mentorId)throws  InfyInternException;
    public void deleteProject(Integer projectId) throws InfyInternException;
}
