package com.atipera.githubAPI.services.intefaces;

import java.util.List;

import com.atipera.githubAPI.models.DTOs.RepositoryDto;

public interface IGitHubService {
    public List<RepositoryDto> getUserRepositories(String username);
}
