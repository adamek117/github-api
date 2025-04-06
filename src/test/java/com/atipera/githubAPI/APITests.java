package com.atipera.githubAPI;

import static com.atipera.githubAPI.intefaces.TestData.UserData.USER_EXISTED;
import static com.atipera.githubAPI.intefaces.TestData.UserData.USER_WITH_FORKED_REPO;
import static com.atipera.githubAPI.intefaces.TestData.UserData.USER_NOT_EXISTED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.HttpClientErrorException;

import com.atipera.githubAPI.models.DTOs.BranchDto;
import com.atipera.githubAPI.models.DTOs.RepositoryDto;
import com.atipera.githubAPI.services.intefaces.IGitHubService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APITests {

    @Autowired
    private IGitHubService GitHubService;

    @Test
    public void testGetExistedUserRepositories() {
        List<RepositoryDto> repos = GitHubService.getUserRepositories(USER_EXISTED);
        assertNotNull(repos, "Expected non-null repository list for user" + USER_EXISTED);
        assertFalse(repos.isEmpty(), "Expected at least one repository for user" + USER_EXISTED);
    }

    @Test
    public void testGetNotExistedUserRepositories() {
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            GitHubService.getUserRepositories(USER_NOT_EXISTED);
        });
        assertNotNull(exception.getMessage(), "Expected exception to have a message");
        assertFalse(exception.getMessage().isEmpty(), "Expected exception message not to be empty");
    }

    @Test
    public void testRepositoryFieldsMapping() {
        List<RepositoryDto> repos = GitHubService.getUserRepositories(USER_EXISTED);
        for (RepositoryDto repo : repos) {
            assertNotNull(repo.getName(), "Repository name should not be null");
            assertNotNull(repo.getOwnerLogin(), "Owner login should not be null");
            assertFalse(repo.getBranches().isEmpty(), "Branches list should not be empty");
            for (BranchDto branch : repo.getBranches()) {
                assertNotNull(branch.getName(), "Branch name should not be null");
                assertNotNull(branch.getCommitSha(), "Commit SHA should not be null");
            }
        }
    }

    @Test
    public void testForkedRepositoriesAreNotExcluded() {
        List<RepositoryDto> repos = GitHubService.getUserRepositories(USER_WITH_FORKED_REPO);
        assertNotNull(repos, "Expected non-null repository list for user" + USER_WITH_FORKED_REPO);
        assertFalse(repos.isEmpty(), "Expected at least one repository for user" + USER_WITH_FORKED_REPO);
        assertEquals(7, repos.size(), "Expected only non-fork repositories to be returned");
    }
}
