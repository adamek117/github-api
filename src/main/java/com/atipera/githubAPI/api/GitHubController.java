package com.atipera.githubAPI.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.atipera.githubAPI.services.GitHubService;

@RestController
@RequestMapping("/github")
public class GitHubController {

    private final GitHubService gitHubService;

    public GitHubController(GitHubService gitHubService) {
        this.gitHubService = gitHubService;
    }

    @GetMapping("/user/{user}/repos")
    public ResponseEntity<Object> getUserRepos(@PathVariable String user) {
        try {
            List<Map<String, Object>> repositories = gitHubService.getRepositories(user);
            return new ResponseEntity<>(repositories, HttpStatus.OK);
        } catch (HttpClientErrorException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", HttpStatus.NOT_FOUND.value(),
                            "message", e.getMessage()));
        }
    }
}