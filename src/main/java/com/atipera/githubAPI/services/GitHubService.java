package com.atipera.githubAPI.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import org.springframework.stereotype.Service;

@Service
public class GitHubService {

    @Value("${github.api.base-url}")
    private String githubApiBaseUrl;

    private final RestTemplate restTemplate;

    public GitHubService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Map<String, Object>> getRepositories(String user) {
        List<Map<String, Object>> result = new ArrayList<>();

        List<Map<String, Object>> repos = getRepos(user);

        if (repos != null) {
            for (Map<String, Object> repo : repos) {
                if (!Boolean.TRUE.equals(repo.get("fork"))) {
                    Map<String, Object> repoData = new HashMap<>();
                    String repoName = (String) repo.get("name");
                    repoData.put("name", repoName);
                    Object ownerObject = repo.get("owner");
                    String ownerLogin = "unknown";
                    if (ownerObject instanceof Map) {
                        Map<?, ?> ownerMap = (Map<?, ?>) ownerObject;
                        Object login = ownerMap.get("login");
                        if (login instanceof String) {
                            ownerLogin = (String) ownerMap.get("login");
                        } else {
                            System.err.println("Owner login is not a String for repository: " + repo.get("name"));
                        }
                    } else {
                        System.err.println("Owner is not a Map for repository: " + repo.get("name"));
                    }

                    repoData.put("ownerLogin", ownerLogin);

                    List<Map<String, Object>> branches = getBranches(ownerLogin, repoName);

                    repoData.put("branches", branches);

                    result.add(repoData);
                }
            }
        }

        return result;
    }

    private List<Map<String, Object>> getRepos(String user) {
        String url = githubApiBaseUrl + "/users/" + user + "/repos";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                }).getBody();
    }

    private List<Map<String, Object>> getBranches(String owner, String repo) {
        String url = githubApiBaseUrl + "/repos/" + owner + "/" + repo + "/branches";
        return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {
                }).getBody();
    }

}
