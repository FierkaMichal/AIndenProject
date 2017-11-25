package com.ainder.ainder.restPOJO;

import java.util.List;

public class UserArray {

    private List<UserResponse> userList = null;

    public UserArray() {
    }

    public List<UserResponse> getUser() {
        return userList;
    }

    public void setUser(List<UserResponse> userList) {
        this.userList = userList;
    }

}
