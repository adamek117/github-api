package com.atipera.githubAPI.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Branch {
    private String name;
    private String commitSha;

    public Branch(String name, String commitSha) {
        this.name = name;
        this.commitSha = commitSha;
    }

}
