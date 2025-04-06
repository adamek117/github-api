package com.atipera.githubAPI.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atipera.githubAPI.helpers.interfaces.IRepositoryHelper;
import com.atipera.githubAPI.models.Branch;
import com.atipera.githubAPI.models.Repository;

@Component
public class RepositoryHelper implements IRepositoryHelper {

    private List<Repository> repositories = new ArrayList<>(List.of(
            new Repository("repo1", "owner1", List.of(new Branch("branch1", "commit1")),true),
            new Repository("repo2", "owner2", List.of(new Branch("branch2", "commit2")),false),
            new Repository("repo3", "owner3", List.of(new Branch("branch3", "commit3")),false),
            new Repository("repo4", "owner4", List.of(new Branch("branch4", "commit4")),true),
            new Repository("repo5", "owner5", List.of(new Branch("branch5", "commit5")),false)
            ));

    @Override
    public List<Repository> getRepositories() {
        return repositories;
    }
}