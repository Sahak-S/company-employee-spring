package com.example.companyemployeespring.controller;

import org.apache.commons.io.IOUtils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileInputStream;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class MainController {

    @Value("${companyemployeesspring.upload.path}")
    private String imagePhat;


    @GetMapping("/")
    public String main(){
        return "main";
    }

    @GetMapping(value = "/getImage",produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@RequestParam("picName") String picName) throws IOException {
        InputStream inputStream = new FileInputStream(imagePhat + picName );
        return IOUtils.toByteArray(inputStream);
    }
}
