package com.solarchain.project.clientt;

import com.solarchain.project.entity.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name="client-service" , url ="${application.config.clients-url}")
public interface ProjectClient {

    @GetMapping("/project/{projectId}")
    List<Client> findAllClientsByProject(@PathVariable("projectId") String projectId);

}
