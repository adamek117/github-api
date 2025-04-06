package com.atipera.githubAPI.repositories.interfaces;

import java.util.List;

import com.atipera.githubAPI.models.Branch;
import com.atipera.githubAPI.models.RepositoryModel;


public interface IGitHubRepository {

    public List<RepositoryModel> getUserRepositories(String username);

    public List<Branch> getBranches(String owner, String repoName);

}
