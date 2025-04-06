package com.atipera.githubAPI.repositories;

import java.util.List;

import com.atipera.githubAPI.models.Repository;

public interface IRepositoryRepository {
    public List<Repository> getRepositories();
}
