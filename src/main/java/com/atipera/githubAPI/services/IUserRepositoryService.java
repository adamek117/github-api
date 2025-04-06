package com.atipera.githubAPI.services;

import java.util.List;import com.atipera.githubAPI.models.Repository;


public interface IUserRepositoryService {

public List<Repository> getUserRepositories(String userId);
}
