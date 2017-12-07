package com.ainder.ainder.entities;

import javax.persistence.*;

/**
 * Created by Michał on 2017-12-07.
 */

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idImage;

    @Lob
    private byte[] picture;

    public Long getIdImage() {
        return idImage;
    }

    @ManyToOne()
    @JoinColumn(referencedColumnName = "ID_USER", nullable = false) //REFERENCED
    private User user;

    public void setIdImage(Long idImage) {
        this.idImage = idImage;
    }

    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }
}
