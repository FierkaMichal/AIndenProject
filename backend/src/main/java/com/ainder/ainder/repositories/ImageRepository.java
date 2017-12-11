package com.ainder.ainder.repositories;

import com.ainder.ainder.entities.Image;
import com.ainder.ainder.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Michał on 2017-12-07.
 */
public interface ImageRepository extends CrudRepository<Image, Long> {

    @Transactional
    @Modifying
    void deleteByIdImage(Long idImage);

    List<Image> getAllByUser(User user);
}
