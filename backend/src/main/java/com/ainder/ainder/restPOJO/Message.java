package com.ainder.ainder.restPOJO;

public class Message {

    private Long myId;
    private Long otherPersonId;
    private String time;
    private String message;

    public Long getMyId() {
        return myId;
    }

    public void setMyId(Long myId) {
        this.myId = myId;
    }

    public Long getOtherPersonId() {
        return otherPersonId;
    }

    public void setOtherPersonId(Long otherPersonId) {
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

    public Message(Long myId, Long otherPersonId, String time, String message) {
        this.myId = myId;
        this.otherPersonId = otherPersonId;
        this.time = time;
        this.message = message;
    }
}


//        {
//          "myId" : 5,
//          "otherPersonId" : 7.
//          "time" : "20171230185113",
//          "message" : "tesc wiadomosci"
//        }