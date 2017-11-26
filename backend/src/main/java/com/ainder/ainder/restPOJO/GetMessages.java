package com.ainder.ainder.restPOJO;

public class GetMessages {
    private Long userId;

    public GetMessages() {
    }

    public GetMessages(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
