package com.ainder.ainder.restPOJO;

public class Login {

    private String login;
    private String password;
    private Double lat;
    private Double lon;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

}

//        {
//          "login" : "dadada",
//          "password" : "dadadas",
//          "lat" : 15.55,
//          "lon" : 31.515
//        }