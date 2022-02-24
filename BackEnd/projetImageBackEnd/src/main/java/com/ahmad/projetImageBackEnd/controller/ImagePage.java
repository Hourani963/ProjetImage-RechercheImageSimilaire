package com.ahmad.projetImageBackEnd.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("image")
@CrossOrigin("*")
public class ImagePage {

    @GetMapping("upload")
    private void getImageR(){
        System.err.println("done");
    }
}
