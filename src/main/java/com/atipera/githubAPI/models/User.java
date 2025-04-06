package com.atipera.githubAPI.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private String id;
    private String login;

    public User(String id, String login) {
        this.id = id;
        this.login = login;
    }


}
