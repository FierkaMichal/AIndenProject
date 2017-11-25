package com.ainder.ainder.restPOJO;

public class GetMessages {
    private Integer userId;

    public GetMessages(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
