package com.atipera.githubAPI.helpers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.atipera.githubAPI.helpers.interfaces.IUserHelper;
import com.atipera.githubAPI.models.User;

@Component
public class UserHelper implements IUserHelper {
    private List<User> users = new ArrayList<>(List.of(
            new User("1", "owner1"),
            new User("2", "owner2"),
            new User("3", "owner3"),
            new User("4", "owner4"),
            new User("5", "owner5")
            ));

    public List<User> getUsers() {
        return users;
    }
}