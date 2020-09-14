package org.shopify.imagerepository.Controller;

import org.hibernate.dialect.Database;
import org.shopify.imagerepository.Service.AmazonClient;
import org.shopify.imagerepository.Service.DatabaseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/storage")
public class BucketController {

        private static final Logger logger = LoggerFactory.getLogger(BucketController.class);

        @Autowired
        private AmazonClient amazonClient;

        @Autowired
        private DatabaseClient databaseClient;

        BucketController(AmazonClient amazonClient)
        {
            this.amazonClient=amazonClient;
        }

        @PostMapping("/upload")
        public  String uploadFile(@RequestPart(value="file") MultipartFile file) {
            logger.info("Upload file "+file.getName());
            String url = this.amazonClient.uploadFile(file);
            logger.info("Uploaded to S3 Bucket "+url);
            /* Store image details in MYSQL */
            this.databaseClient.store(file.getName(),file.getContentType(),url);

            return "Upload Image successfully!";
        }
}
