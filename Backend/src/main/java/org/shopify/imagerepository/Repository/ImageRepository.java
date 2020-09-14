package org.shopify.imagerepository.Repository;

import org.shopify.imagerepository.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.Optional;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Optional<Image> findByName(String name);
    Optional<Image> findByDate(Date date);
}
