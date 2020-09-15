package org.shopify.imagerepository.Controller;

import org.hibernate.dialect.Database;
import org.shopify.imagerepository.Service.AmazonClient;
import org.shopify.imagerepository.Service.DatabaseClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/storage")
public class BucketController {

        private static final Logger logger = LoggerFactory.getLogger(BucketController.class);
        private String message;

        @Autowired
        private AmazonClient amazonClient;

        @Autowired
        private DatabaseClient databaseClient;

        BucketController(AmazonClient amazonClient)
        {
            this.amazonClient=amazonClient;
            this.message="";
        }

        @PostMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_VALUE)
        public String uploadFile(@RequestPart(value="file") MultipartFile file,
                                                 @RequestPart(value="name",required = false) String name) {
            String currName=(name==null)?file.getOriginalFilename():name;
            logger.info("Upload file "+name);
            String url = this.amazonClient.uploadFile(file);
            logger.info("Uploaded to S3 Bucket "+url);
            /* Store image details in MYSQL */
            this.databaseClient.store(currName,file.getContentType(),url);
            this.message="{\n" +
                    "\t\"text\": \"Image uploaded successfully!\"\n" +
                    "}";

            return this.message;
        }
}
