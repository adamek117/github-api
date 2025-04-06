package com.atipera.githubAPI.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.atipera.githubAPI.models.Branch;
import com.atipera.githubAPI.models.RepositoryModel;
import com.atipera.githubAPI.repositories.interfaces.IGitHubRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@Repository
public class GitHubRepository implements IGitHubRepository {

    @Value("${github.api.base-url:https://api.github.com}")
    private String githubApiBaseUrl;

    private final RestTemplate restTemplate;

    public GitHubRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public List<RepositoryModel> getUserRepositories(String username) {
        String url = String.format("%s/users/%s/repos", githubApiBaseUrl, username);

        ResponseEntity<List<RepositoryModel>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<RepositoryModel>>() {});
                
        System.out.println("Response: " + response.getBody().toString());
        
        return response.getBody();
    }

    @Override
    public List<Branch> getBranches(String owner, String repoName) {
        String url = String.format("%s/repos/%s/%s/branches", githubApiBaseUrl, owner, repoName);

        ResponseEntity<List<Branch>> response = restTemplate.exchange(url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Branch>>() {});

        return response.getBody();
    }
}
