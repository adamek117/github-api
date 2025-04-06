package com.atipera.githubAPI.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atipera.githubAPI.helpers.interfaces.IRepositoryHelper;
import com.atipera.githubAPI.repositories.interfaces.IRepositoryRepository;

@Repository
public class RepositoryRepository implements IRepositoryRepository {

    private IRepositoryHelper gitHubRepositoryHelper;

    @Autowired
    public RepositoryRepository(IRepositoryHelper gitHubRepositoryHelper) {
        this.gitHubRepositoryHelper = gitHubRepositoryHelper;
    }

    @Override
    public List<com.atipera.githubAPI.models.Repository> getRepositories() {
        return gitHubRepositoryHelper.getRepositories();
    }
}