package com.ainder.ainder.restPOJO;

public class Error {
    String error;

    public Error() {
        error = "none";
    }

    public Error(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
