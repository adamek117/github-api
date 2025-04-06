package com.atipera.githubAPI.models.DTOs;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RepositoryDto {
    private String name;
    private String ownerLogin;
    private List<BranchDto> branches;
}
