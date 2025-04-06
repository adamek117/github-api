package com.atipera.githubAPI.services;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.atipera.githubAPI.models.Branch;
import com.atipera.githubAPI.models.RepositoryModel;
import com.atipera.githubAPI.models.DTOs.BranchDto;
import com.atipera.githubAPI.models.DTOs.RepositoryDto;
import com.atipera.githubAPI.repositories.interfaces.IGitHubRepository;
import com.atipera.githubAPI.services.intefaces.IGitHubService;

@Service
public class GitHubService implements IGitHubService {
    private final IGitHubRepository gitHubRepository;

    public GitHubService(IGitHubRepository gitHubRepository) {
        this.gitHubRepository = gitHubRepository;
    }

    @Override
    public List<RepositoryDto> getUserRepositories(String username) {
        List<RepositoryModel> repositories = gitHubRepository.getUserRepositories(username);
        if (repositories == null || repositories.isEmpty()) {
            return List.of();
        }
        List<RepositoryModel> filteredRepositories = repositories.stream()
                .filter(repo -> Boolean.FALSE.equals(repo.getFork()))
                .map(repo -> {
                    List<Branch> branches = getBranches(repo.getOwner().getLogin(), repo.getName());
                    repo.setBranches(branches);
                    return repo;
                }).collect(Collectors.toList());

        List<RepositoryDto> repositoriesDTO = filteredRepositories.stream()
                .map(repo -> new RepositoryDto(repo.getName(), repo.getOwner().getLogin(),
                        getBranchesDto(repo.getBranches())))
                .toList();
        return repositoriesDTO;
    }

    private List<Branch> getBranches(String owner, String repoName) {
        List<Branch> branches = gitHubRepository.getBranches(owner, repoName);
        if (branches == null || branches.isEmpty()) {
            return List.of();
        }
        return branches;

    }

    private List<BranchDto> getBranchesDto(List<Branch> branches) {
        List<BranchDto> branchesDto = branches.stream()
                .map(branch -> new BranchDto(branch.getName(), branch.getCommit().getSha()))
                .collect(Collectors.toList());
        return branchesDto;
    }

}
