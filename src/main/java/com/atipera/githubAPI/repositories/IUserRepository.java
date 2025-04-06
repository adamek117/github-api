package com.atipera.githubAPI.repositories;

import java.util.List;

import com.atipera.githubAPI.models.User;

public interface IUserRepository {
    public List<User> getUsers();
}
