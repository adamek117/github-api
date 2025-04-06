package com.atipera.githubAPI;

import static com.atipera.githubAPI.intefaces.TestData.UserData.USER_ID_FORKED;
import static com.atipera.githubAPI.intefaces.TestData.UserData.USER_ID_NOT_FORKED;
import static com.atipera.githubAPI.intefaces.TestData.UserData.USER_ID_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.atipera.githubAPI.models.Repository;
import com.atipera.githubAPI.services.intefaces.IUserRepositoryService;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class APITests {

    @Autowired
    private IUserRepositoryService userRepositoryService;

    @Test
    public void testGetExistedUserRepositories() {
        List<Repository> repos = userRepositoryService.getUserRepositories(USER_ID_NOT_FORKED);
        assertNotNull(repos, "Expected non-null repository list for user" + USER_ID_NOT_FORKED);
        assertFalse(repos.isEmpty(), "Expected at least one repository for user" + USER_ID_NOT_FORKED);

    }

    @Test
    public void testGetNotExistedUserRepositories() {
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            userRepositoryService.getUserRepositories(USER_ID_NOT_FOUND);
        });
        assertNotNull(exception.getMessage(), "Expected exception to have a message");
        assertFalse(exception.getMessage().isEmpty(), "Expected exception message not to be empty");

        /*
         * try {
         * userRepositoryService.getUserRepositories(USER_ID_NOT_FOUND);
         * } catch (Exception e) {
         * assertTrue(e.getMessage() != null && !e.getMessage().isEmpty(),
         * "An exception with should be caught");
         * }
         */
    }

    @Test
    public void testGetUserRepositoryNotForkedNotFound() {
        List<Repository> repos = userRepositoryService.getUserRepositories(USER_ID_FORKED);
        assertTrue(repos.isEmpty(), "Expected empty repository list for user '1'");

    }

    @Test
    public void testGetUserRepositoryNotForked() {
        List<Repository> repos = userRepositoryService.getUserRepositories(USER_ID_NOT_FORKED);
        assertNotNull(repos, "Expected non-null repository list");
        assertTrue(!repos.isEmpty(), "Expected at least one repository");
    }

}
