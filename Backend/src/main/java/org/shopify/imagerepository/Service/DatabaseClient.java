package org.shopify.imagerepository.Service;

import org.shopify.imagerepository.Model.Image;
import org.shopify.imagerepository.Repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DatabaseClient {

    private static Logger logger = LoggerFactory.getLogger(DatabaseClient.class);

    @Autowired
    ImageRepository imageRepository;

    public void store(String name, String contentType, String url)
    {
        logger.info("Storing file "+name+" information into database ");
        Image image = new Image(name,contentType,url);
        imageRepository.save(image);
        logger.info("Stored file : "+name+" details in database");

    }
}
