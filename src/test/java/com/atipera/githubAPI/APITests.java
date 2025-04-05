package com.atipera.githubAPI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.atipera.githubAPI.services.GitHubService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APITests {

    @Autowired
    private GitHubService gitHubService;

    @Test
    public void getRepositories() {
        String user = "adamek117";
        List<Map<String, Object>> repos = gitHubService.getRepositories(user);
        assertNotNull(repos, "The response body should not be null");
        assertTrue(repos.size() > 0, "The response body should contain at least one repository");

        Map<String, Object> firstRepo = repos.get(0);
        assertTrue(firstRepo.containsKey("name"), "The repository should contain a 'name' key");
        assertTrue(firstRepo.containsKey("ownerLogin"), "The repository should contain an 'ownerLogin' key");
        assertTrue(firstRepo.containsKey("branches"), "The repository should contain a 'branches' key");

    }

    @Test
    public void testGetUserRepositoryNotFound() {
        String user = "nonexistent-user";
        try {
            gitHubService.getRepositories(user);
            fail("Expected exception for non-existent user");
        } catch (Exception e) {
            assertTrue(e instanceof GitHubUserNotFoundException, "Expected GitHubUserNotFoundException");
        }
    }

}
