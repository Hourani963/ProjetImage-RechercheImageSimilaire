package com.ahmad.projetImageBackEnd.service;

import com.ahmad.projetImageBackEnd.algosJava.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUploadUtil {



    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
            // d'ici il faut commencer de faire les algos.
            ImageOperations requeteImage = new ImageOperations(uploadDir+"\\"+fileName);
            RechercheImage rechercheImage = new RechercheImage("C:\\Users\\ugarit\\Desktop\\GIT\\ProjetImage\\BackEnd\\images\\motos",requeteImage);


        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}