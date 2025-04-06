package com.atipera.githubAPI.repositories;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atipera.githubAPI.helpers.IUserHelper;
import com.atipera.githubAPI.models.User;

@Repository
public class UserRepository implements IUserRepository {

    private IUserHelper gitHubUserHelper;

    @Autowired
    public UserRepository(IUserHelper gitHubUserHelper) {
        this.gitHubUserHelper = gitHubUserHelper;
    }

    @Override
    public List<User> getUsers() {
        return gitHubUserHelper.getUsers();
    }
}