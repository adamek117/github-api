package com.atipera.githubAPI.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atipera.githubAPI.models.Repository;
import com.atipera.githubAPI.repositories.interfaces.IRepositoryRepository;
import com.atipera.githubAPI.repositories.interfaces.IUserRepository;
import com.atipera.githubAPI.services.intefaces.IUserRepositoryService;

@Service
public class UserRepositoryService implements IUserRepositoryService {

    private IUserRepository userRepository;
    private IRepositoryRepository repositoryRepository;

    @Autowired
    public UserRepositoryService(IUserRepository userRepository, IRepositoryRepository repositoryRepository) {
        this.userRepository = userRepository;
        this.repositoryRepository = repositoryRepository;
    }

    public List<Repository> getUserRepositories(String userId) {
        String userLogin = userRepository.getUsers().stream()
                .filter(user -> user.getId().equals(userId))
                .findFirst().orElseThrow(() -> new NoSuchElementException(userId)).getLogin();

        List<Repository> repositories = repositoryRepository.getRepositories().stream()
                .filter(repo -> repo.getOwnerLogin().equals(userLogin) && !repo.getIsForked())
                .toList();

        return repositories;
    }
}