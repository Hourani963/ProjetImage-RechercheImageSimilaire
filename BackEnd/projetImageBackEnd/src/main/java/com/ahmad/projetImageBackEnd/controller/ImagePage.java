package com.ahmad.projetImageBackEnd.controller;


import com.ahmad.projetImageBackEnd.algosJava.RechercheImage;
import com.ahmad.projetImageBackEnd.service.FileUploadUtil;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;


import java.io.IOException;


@RestController()
@CrossOrigin("*")
@RequestMapping("image")
public class ImagePage {


    @PostMapping("/upload")
    public RedirectView saveUser(
            @RequestParam("imageR") MultipartFile multipartFile)
            throws IOException {

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String uploadDir = "uploads/" + "imageR";

        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        return new RedirectView("/users", true);
    }

    @GetMapping("/download")
    public String[] getSimilarPhotos() {
        return RechercheImage.bestImagesFullPath;
    }
}

