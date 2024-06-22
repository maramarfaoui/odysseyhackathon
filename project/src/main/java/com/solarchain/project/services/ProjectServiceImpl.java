package com.solarchain.project.services;



import com.solarchain.project.clientt.ProjectClient;
import com.solarchain.project.entity.FullProjectResponse;
import com.solarchain.project.entity.Project;
import com.solarchain.project.repository.ProjectRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class ProjectServiceImpl implements IProjectService {
    @Autowired
    ProjectRepository projectRepository ;
    ProjectClient projectClient ;


    @Override
    public List<Project> getProjectByName(String name) {
        log.info("Getting Project from the repo", name);
        List<Project> projects = projectRepository.findByProjectName(name);

        if(projects.isEmpty()) {
            log.info("No Project");
            return new ArrayList<Project>();
        }

        log.info("this is the list of Project",projects.size(),name);
        return projects ;



    }

    @Override
    public Project addProject(Project p) {
        log.info("inserting start");
        Project project =  projectRepository.insert(p);
       log.info("success" + project);
        return project;

    }

    @Override
    public void delete(String id) {
        projectRepository.deleteById(id);
    }

    @Override
    public Project update(String id, Project updatedProject) {
        Optional<Project> existingProjectOptional = projectRepository.findById(id);

        if (existingProjectOptional.isPresent()) {
            Project existingProject = existingProjectOptional.get();

            // Mettre à jour les champs spécifiés dans la requête, s'ils ne sont pas nuls
            if (updatedProject.getProjectName() != null) {
                existingProject.setProjectName(updatedProject.getProjectName());
            }

            if (updatedProject.getDescription() != null) {
                existingProject.setDescription(updatedProject.getDescription());
            }

            if (updatedProject.getAmount() != null) {
                existingProject.setAmount(updatedProject.getAmount());
            }

            return projectRepository.save(existingProject);
        } else {
            return null;
        }
    }


    @Override
    public FullProjectResponse getProjectsWithClients(String projectId) {
        var project = projectRepository.findById(projectId)
                .orElse(Project.builder()
                        .projectName("Not Found")
                        .description("Not Found")

                        .build());
        var clients = projectClient.findAllClientsByProject(projectId) ; //lezm njib el clients kol mn el client micro service
        return FullProjectResponse.builder()
                .projectName(project.getProjectName())
                .description(project.getDescription())
                .clients(clients).build();
    }
}
