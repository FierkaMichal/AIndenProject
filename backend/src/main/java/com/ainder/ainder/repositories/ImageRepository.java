package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Image;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Michał on 2017-12-07.
 */
public interface ImageRepository extends CrudRepository<Image, Long> {
}
