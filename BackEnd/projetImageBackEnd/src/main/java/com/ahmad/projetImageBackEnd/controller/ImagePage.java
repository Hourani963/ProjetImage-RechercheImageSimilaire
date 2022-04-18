package com.ahmad.projetImageBackEnd.controller;


import com.ahmad.projetImageBackEnd.algosJava.RechercheImage;
import com.ahmad.projetImageBackEnd.service.*;


import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import org.springframework.web.servlet.view.RedirectView;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;



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

    @GetMapping("getimage/{id}")
    @ResponseBody
    public byte[] download(@PathVariable("id") String imageName) {
        try {
            //ystem.err.println(RechercheImage.pathDossier + "\\"+ imageName);
            File file = new File(RechercheImage.pathDossier + "\\"+ imageName);
            InputStream targetStream = new FileInputStream(file);
            return IOUtils.toByteArray(targetStream);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to download file From Local", e);
        }
    }

}









