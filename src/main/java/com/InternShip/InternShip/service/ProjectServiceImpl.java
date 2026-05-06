package com.InternShip.InternShip.service;

import com.InternShip.InternShip.dto.MentorDTO;
import com.InternShip.InternShip.dto.ProjectDTO;
import com.InternShip.InternShip.entity.Mentor;
import com.InternShip.InternShip.entity.Project;

import com.InternShip.InternShip.exception.InfyInternException;
import com.InternShip.InternShip.repository.MentorRepository;
import com.InternShip.InternShip.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

    //Constructor Injection
    private MentorRepository mentorRepository;

    ProjectServiceImpl(MentorRepository mentorRepository) {
        this.mentorRepository = mentorRepository;
    }

    //
    @Autowired
    private ProjectRepository projectRepository;


    @Override
    public Integer allocateProject(ProjectDTO projectdto) throws InfyInternException {

        // retrieve mentor
        Optional<Mentor> m = mentorRepository.findById(projectdto.getMentordto().getMentorId());
        if (m.isEmpty()) {
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        }

        // fetch from Optional
        Mentor mentor = m.get();

        // Check projects
        if (mentor.getNumberOfprojectMentored() >= 3) {
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");
        }

        // Create and populate the Project object
        Project project = new Project();
        project.setProjectName(projectdto.getProjectName());
        project.setIdeaOwner(projectdto.getIdeaOwner());
        project.setReleaseDate(projectdto.getReleaseDate());
        project.setMentor(mentor);

        // Save the project to database
        Project savedProject = projectRepository.save(project);

        // Increment the mentor's project count and save mentor
        mentor.setNumberOfprojectMentored(mentor.getNumberOfprojectMentored() + 1);
        mentorRepository.save(mentor);

        // Return the newly created Project ID
        return savedProject.getProjectId();
    }

    @Override
    public List<MentorDTO> getMentors(Integer numberOfProjectsMentored) throws InfyInternException {
// Fetch mentor details
        List<Mentor> mentor = mentorRepository.findByNumberOfprojectMentored(numberOfProjectsMentored);
        System.out.println("************" + mentor.size());
        List<MentorDTO> mentordto;
        if (mentor.isEmpty()) {

            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        } else {

            mentordto = new ArrayList<>();
            for (Mentor m : mentor) {
                MentorDTO dto = new MentorDTO();
                dto.setMentorId(m.getMentorId());
                dto.setMentorName(m.getMentorName());
                dto.setNumberOfprojectMentored(m.getNumberOfprojectMentored());
                mentordto.add(dto);
            }
        }
        return mentordto;
    }

    @Override
    public void updateProjectMentor(Integer projectId, Integer mentorId) throws InfyInternException {

        // get Mentor Details
        Optional<Mentor> m = mentorRepository.findById(mentorId);
        if (m.isEmpty()) {
            throw new InfyInternException("Service.MENTOR_NOT_FOUND");
        }

        // fetch from Optional
        Mentor mentor = m.get();
        if(mentor.getNumberOfprojectMentored() >=3 ){
            throw new InfyInternException("Service.CANNOT_ALLOCATE_PROJECT");

        }
        // retrive project details
        Optional<Project>  p = projectRepository.findById(projectId);
        if(p.isEmpty()){
            throw new InfyInternException("Service.PROJECT_NOT_FOUND");
        }
        Project project = p.get();
        // Create and populate the Project object
        Project projects = new Project();
        projects.setProjectName(project.getProjectName());
        projects.setIdeaOwner(project.getIdeaOwner());
        projects.setReleaseDate(project.getReleaseDate());
        projects.setMentor(mentor);

        // Save the project to database
        Project savedProject = projectRepository.save(project);

        // Increment the mentor's project count and save mentor
        mentor.setNumberOfprojectMentored(mentor.getNumberOfprojectMentored() + 1);
        mentorRepository.save(mentor);

    }

    @Override
    public void deleteProject(Integer projectId) throws InfyInternException {

        // Retrieve project details
        Optional<Project> projectOpt = projectRepository.findById(projectId);
        if (projectOpt.isEmpty()) {
            throw new InfyInternException("Service.PROJECT_NOT_FOUND");
        }

        Project project = projectOpt.get();
        Mentor mentor = project.getMentor();

        // If project is allocated to a mentor, decrement mentor's count
        if (mentor != null) {
            mentor.setNumberOfprojectMentored(mentor.getNumberOfprojectMentored() - 1);
            mentorRepository.save(mentor);
        }

        // Delete the project
        projectRepository.delete(project);
    }
}
