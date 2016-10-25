package com.example.pl.slc.service;

import com.example.pl.slc.model.ImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageUtil {

    public ImageFile toImageFile(MultipartFile file) throws IOException {
        if(file == null) return null;

        ImageFile imageFile = ImageFile
                .builder()
                .data(file.getBytes())
                .name(file.getName())
                .extension(file.getContentType())
                .build();
        return imageFile;
    }
}
