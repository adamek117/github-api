package com.atipera.githubAPI.models.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BranchDto {
    private String name;
    private String commitSha;

}
