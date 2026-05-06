package com.InternShip.InternShip.controller;


import com.InternShip.InternShip.dto.MentorDTO;
import com.InternShip.InternShip.dto.ProjectDTO;
import com.InternShip.InternShip.exception.InfyInternException;
import com.InternShip.InternShip.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="project/")
public class ProjectController {

    @Autowired
    ProjectService projectServiceimpl;

    @PostMapping(value = "/allocate")
    public ResponseEntity<String> allocateProjects(@RequestBody ProjectDTO project) throws InfyInternException {

        Integer projectId = projectServiceimpl.allocateProject(project);
        String message = "Project has been allocated with projectId: " + projectId;

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

@GetMapping(value ="/getmentors/{numberOfProjectsMentored}")
    public ResponseEntity<List<MentorDTO>> getMentors(@PathVariable Integer numberOfProjectsMentored) throws InfyInternException {
        List<MentorDTO> mentorList = projectServiceimpl.getMentors(numberOfProjectsMentored);

return new ResponseEntity<>(mentorList, HttpStatus.OK);
    }

    @PutMapping(value="/updateMentor")
    public ResponseEntity<String> updateProjectMentor(@RequestParam Integer projectId, @RequestParam Integer mentorId) throws InfyInternException {
        projectServiceimpl.updateProjectMentor(projectId, mentorId);
return new ResponseEntity<>("Project mentor updated successfully", HttpStatus.OK);
    }

    @DeleteMapping(value = "/delete/{projectId}")
    public ResponseEntity<String> deleteProject(@PathVariable Integer projectId) throws InfyInternException {
        projectServiceimpl.deleteProject(projectId);
        String message = "Project deleted successfully for projectId: " + projectId;
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}