package com.solarchain.project.repository;

import com.solarchain.project.entity.Project;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProjectRepository extends MongoRepository<Project, String> {

    List<Project> findByProjectName(String name);
}
