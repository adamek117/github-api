package com.atipera.githubAPI.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Branch {
    private String name;
    private Commit commit;

}
