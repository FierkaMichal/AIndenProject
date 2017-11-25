package com.ainder.ainder.restPOJO;

public class UserResponse {
    private Long userId;
    private String name;
    private String surname;
    private String description;
    private String photo;
    private Double lastLongitude;
    private Double lastLatitude;

    public UserResponse() {
    }

    public UserResponse(Long id, String name, String surname, String description, String photo, Double lastLongitude, Double lastLatitude) {
        this.userId = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.photo = photo;
        this.lastLongitude = lastLongitude;
        this.lastLatitude = lastLatitude;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Double getLastLongitude() {
        return lastLongitude;
    }

    public void setLastLongitude(Double lastLongitude) {
        this.lastLongitude = lastLongitude;
    }

    public Double getLastLatitude() {
        return lastLatitude;
    }

    public void setLastLatitude(Double lastLatitude) {
        this.lastLatitude = lastLatitude;
    }
}
