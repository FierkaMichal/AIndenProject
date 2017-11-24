package com.ainder.ainder.restPOJO;

public class Message {

    private Integer myId;
    private Integer otherPersonId;
    private String time;
    private String message;

    public Integer getMyId() {
        return myId;
    }

    public void setMyId(Integer myId) {
        this.myId = myId;
    }

    public Integer getOtherPersonId() {
        return otherPersonId;
    }

    public void setOtherPersonId(Integer otherPersonId) {
        this.otherPersonId = otherPersonId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}


//        {
//          "myId" : 5,
//          "otherPersonId" : 7.
//          "time" : "20171230185113",
//          "message" : "tesc wiadomosci"
//        }