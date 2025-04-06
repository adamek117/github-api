package com.atipera.githubAPI.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RepositoryModel {
    private String name;
    private Owner owner;
    private List<Branch> branches;
    private Boolean fork;

}