package com.atipera.githubAPI.models;

import java.util.List;

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
