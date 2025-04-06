package com.atipera.githubAPI;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atipera.githubAPI.models.Repository;
import com.atipera.githubAPI.services.IUserRepositoryService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APITests {

    @Autowired
    private IUserRepositoryService userRepositoryService;

    @Test
    public void testGetExistedUserRepositories() {
        List<Repository> repos = userRepositoryService.getUserRepositories("2");    
        assertNotNull(repos, "The response body should not be null");
        assertTrue(repos.size() > 0, "The response body should contain at least one repository");
    }

    @Test
    public void testGetNotExistedUserRepositories(){
        List<Repository> repos = userRepositoryService.getUserRepositories("nonexistent-user");    
        assertTrue(repos.size() == 0, "The response body should be empty for a non-existent user");
        
    }

    @Test
    public void testGetUserRepositoryNotForkedNotFound() {
        List<Repository> repos = userRepositoryService.getUserRepositories("1"); 
        assertTrue(repos.size() == 0, "The response body should be empty for a non-existent user");
    }

    @Test
    public void testGetUserRepositoryNotForked() {
        List<Repository> repos = userRepositoryService.getUserRepositories("2"); 
        assertNotNull(repos, "The response body should not be null");
        assertTrue(repos.size() > 0, "The response body should contain at least one repository");
    }
 
}
