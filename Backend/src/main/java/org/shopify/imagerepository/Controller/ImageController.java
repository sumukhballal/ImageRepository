package org.shopify.imagerepository.Controller;

import org.shopify.imagerepository.Model.Image;
import org.shopify.imagerepository.Repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path="api")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @PostMapping("/image/delete")
    private ResponseEntity.BodyBuilder deleteSingleImage(@RequestParam("id") long id) throws IOException
    {
        Image image = imageRepository.getOne(id);
        if(image!=null)
            imageRepository.delete(image);

        return ResponseEntity.status(HttpStatus.OK);
    }

    @GetMapping("/image/getall")
    private void getAllImages()
    {
        for(Image image : imageRepository.findAll()) {
            System.out.println(image.toString());
        }
    }
}
