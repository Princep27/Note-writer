package com.notes.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.notes.entity.User;
import com.notes.store.UserStore;

import java.io.IOException;
import java.util.List;

public class UserService {

    private List<User> users = UserStore.loadUser();
    private User loggedInUser = null;

    public UserService() throws IOException {
    }

    public boolean login(String userName,String password){
        for (User user : users) {
            if (userName.equals(user.getUserName()) && password.equals(user.getPassword())) {
                loggedInUser = user;
                return true;
            }
        }
        return false;
    }

    public boolean addUser(String userName, String password){
        for(User user : users){
            if(userName.equals(user.getUserName())) return false;
        }

        User user = new User(userName,password);
        users.add(user);
        UserStore.saveUsers(users);
        return true;
    }

    public void logout(){
        loggedInUser = null;
    }

    public User getLoggedInUser(){
        return loggedInUser;
    }
}
