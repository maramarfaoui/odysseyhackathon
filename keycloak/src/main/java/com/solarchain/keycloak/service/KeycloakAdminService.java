package com.solarchain.keycloak.service;


import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeycloakAdminService {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Value("${keycloak-admin.username}")
    private String adminUsername;

    @Value("${keycloak-admin.password}")
    private String adminPassword;

    private Keycloak getKeycloakInstance() {
        return KeycloakBuilder.builder()
            .serverUrl(authServerUrl)
            .realm("master")
            .clientId(clientId)
            .clientSecret(clientSecret)
            .username(adminUsername)
            .password(adminPassword)
            .grantType(OAuth2Constants.PASSWORD)
            .build();
    }

    public void assignRoleToUser(String userId, String roleName) {
        Keycloak keycloak = getKeycloakInstance();
        RoleRepresentation role = keycloak.realm(realm).roles().get(roleName).toRepresentation();
        keycloak.realm(realm).users().get(userId).roles().realmLevel().add(List.of(role));
        keycloak.close();
    }
}
