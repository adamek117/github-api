package com.atipera.githubAPI.contollers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atipera.githubAPI.models.DTOs.RepositoryDto;
import com.atipera.githubAPI.services.intefaces.IGitHubService;

@RestController
@RequestMapping("/api/github")
public class RepositoryController {

    private final IGitHubService gitHubService;

    @Autowired
    public RepositoryController(IGitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/user/{username}/repos")
    public ResponseEntity<Object> getUserRepositories(@PathVariable String username) {
            List<RepositoryDto> repos = gitHubService.getUserRepositories(username);
            if (repos == null || repos.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                        "status", HttpStatus.NOT_FOUND.value(),
                        "message", "No repositories found for user: " + username));
            }
            return ResponseEntity.ok(repos);
    }
   
}
