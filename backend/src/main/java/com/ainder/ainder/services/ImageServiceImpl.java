package com.ainder.ainder.services;

import com.ainder.ainder.entities.Image;
import com.ainder.ainder.repositories.ImageRepository;
import com.ainder.ainder.services.interfaces.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Michał on 2017-12-07.
 */

@Service
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void save(Image image) {
        imageRepository.save(image);
    }
}
