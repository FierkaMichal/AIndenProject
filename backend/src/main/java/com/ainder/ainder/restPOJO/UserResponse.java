package com.ainder.ainder.restPOJO;

import java.util.List;

public class UserResponse {
    private Long userId;
    private String login;
    private String name;
    private String surname;
    private String description;
    private String avatar;
    private List<Long> photoList = null;
    private Double lastLongitude;
    private Double lastLatitude;
    private boolean isAdmin;

    public UserResponse() {
    }

    public UserResponse(Long userId, String login, String name, String surname, String description, String avatar, List<Long>  photoArray, Double lastLongitude, Double lastLatitude, boolean role) {
        this.userId = userId;
        this.login = login;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.avatar = avatar;
        this.photoList = photoArray;
        this.lastLongitude = lastLongitude;
        this.lastLatitude = lastLatitude;
        this.isAdmin = role;
    }

    public UserResponse(Long id, String name, String surname, String description, String avatar, Double lastLongitude, Double lastLatitude) {
        this.userId = id;
        this.name = name;
        this.surname = surname;
        this.description = description;
        this.avatar = avatar;
        this.lastLongitude = lastLongitude;
        this.lastLatitude = lastLatitude;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<Long>  getPhotoArray() {
        return photoList;
    }

    public void setPhotoArray(List<Long> photoArray) {
        this.photoList = photoArray;
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
