package com.solarchain.keycloak.web;


import com.solarchain.keycloak.service.KeycloakAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class RestControllerUser {
    @Autowired
    private KeycloakAdminService keycloakAdminService;

    @PostMapping("/assign-role")
    public void assignRoleToUser(@RequestParam String userId, @RequestParam String roleName) {
        keycloakAdminService.assignRoleToUser(userId, roleName);
    }
    @GetMapping("/auth")
    public Authentication authentication(Authentication authentication){
        return authentication;
    }
}
