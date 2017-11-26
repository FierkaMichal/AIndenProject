package com.ainder.ainder.restPOJO;

import java.util.List;

public class PhotoArray {

    private List<String> photoList = null;

    public PhotoArray() {
    }

    public PhotoArray(List<String> photoList) {
        this.photoList = photoList;
    }

    public List<String> getPhotoList() {
        return photoList;
    }

    public void setPhotoList(List<String> photoList) {
        this.photoList = photoList;
    }
}
