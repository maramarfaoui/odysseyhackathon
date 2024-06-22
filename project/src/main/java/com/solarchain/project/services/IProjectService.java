package com.solarchain.project.services;


import com.solarchain.project.entity.FullProjectResponse;
import com.solarchain.project.entity.Project;

import java.util.List;

public interface IProjectService {

    List<Project> getProjectByName(String name);
    Project addProject(Project c);
    void delete(String id);
    Project update(String id,Project project);

    FullProjectResponse getProjectsWithClients(String projetId);
}
