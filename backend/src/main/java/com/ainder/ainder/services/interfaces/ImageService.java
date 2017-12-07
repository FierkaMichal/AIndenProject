package com.ainder.ainder.services.interfaces;

import com.ainder.ainder.entities.Image;
import com.ainder.ainder.entities.User;

/**
 * Created by Michał on 2017-12-07.
 */
public interface ImageService {

    Image getImageById(Long id);
}
