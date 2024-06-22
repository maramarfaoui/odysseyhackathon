package com.solarchain.client.services.keycloack;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RoleChangeService {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public void changeUserRole(String userId, String roleName) {
        String url = keycloakServiceUrl + "/keycloak/assign-role?userId=" + userId + "&roleName=" + roleName;
        restTemplate.postForEntity(url, null, Void.class);
    }
}
