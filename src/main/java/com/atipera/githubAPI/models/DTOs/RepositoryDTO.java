package com.atipera.githubAPI.models.DTOs;

import java.util.List;

import com.atipera.githubAPI.models.Branch;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RepositoryDTO {
    private String name;
    private String ownerLogin;
    private List<Branch> branches;

    public RepositoryDTO(String name, String ownerLogin, List<Branch> branches) {
        this.name = name;
        this.ownerLogin = ownerLogin;
        this.branches = branches;
    }

}
