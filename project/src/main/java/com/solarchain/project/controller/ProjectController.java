package com.solarchain.project.controller;


import com.solarchain.project.entity.FullProjectResponse;
import com.solarchain.project.entity.Project;
import com.solarchain.project.services.IProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@AllArgsConstructor
@Slf4j
@CrossOrigin("*")
@RequestMapping("/api/v1/projects")
public class ProjectController {
    IProjectService iProjectService ;

    @GetMapping("/projects/{name}")
    public List<Project> getProjectssByName(@PathVariable("name") String name){
      return  iProjectService.getProjectByName(name) ;
    }

    @PostMapping("addProject")
    public Project add (@RequestBody Project c){
        log.info("addig");
        return  iProjectService.addProject(c);
    }
    @DeleteMapping("/delete-project/{project-id}")
    public String delete(@PathVariable("project-id") String projectId) {
        iProjectService.delete(projectId);
        return "project with ID " + projectId + " has been deleted successfully";
    }
     //fiha mochkl :(
    //Done Sal7tha :)
    @PutMapping("/updateProject/{projectId}")
    public Project update(@RequestBody Project project ,@PathVariable("projectId") String projectId) {
        log.info("update");
        return iProjectService.update(projectId,project);
    }

    @GetMapping("/with-clients/{projectId}")
    public FullProjectResponse getProjectsWithClients(@PathVariable("projectId") String projectId ){
        return  iProjectService.getProjectsWithClients(projectId) ;
    }
}
