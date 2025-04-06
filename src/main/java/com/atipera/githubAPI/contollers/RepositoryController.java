package com.atipera.githubAPI.contollers;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atipera.githubAPI.models.Repository;
import com.atipera.githubAPI.models.DTOs.RepositoryDTO;
import com.atipera.githubAPI.services.intefaces.IUserRepositoryService;

@RestController
@RequestMapping("/github")
public class RepositoryController {

    private final IUserRepositoryService userRepositoryService;

    @Autowired
    public RepositoryController(IUserRepositoryService userRepositoryService) {
        this.userRepositoryService = userRepositoryService;
    }

    @GetMapping("/user/{userId}/repos")
    public ResponseEntity<Object> getUserRepos(@PathVariable String userId) {
        try {
            List<Repository> repositories = userRepositoryService.getUserRepositories(userId);
            if (repositories.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of(
                                "status", HttpStatus.NOT_FOUND.value(),
                                "message", "No repositories found for user: " + userId));
            }
            List<RepositoryDTO> repositoriesDTO = repositories.stream()
                    .map(repo -> new RepositoryDTO(repo.getName(), repo.getOwnerLogin(), repo.getBranches()))
                    .toList();
            return new ResponseEntity<>(repositoriesDTO, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of(
                            "status", HttpStatus.NOT_FOUND.value(),
                            "message", "User not found"));
        }
    }
}