package com.example.demo;

import com.cloudinary.Cloudinary;

import com.cloudinary.Singleton;
import com.cloudinary.Transformation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import javax.swing.*;
import java.io.IOException;
import java.util.Map;

@Component
public class CloudinaryConfig {
    private Cloudinary cloudinary;
    @Autowired
    public CloudinaryConfig(@Value("674569594276411") String key,
                            @Value ("YfMqd4Ay-YOx9qRbKmjlLtoUJo4") String secret,
                            @Value ("dca0pvel9") String cloud){


        cloudinary = Singleton.getCloudinary();
        cloudinary.config.cloudName=cloud;
        cloudinary.config.apiSecret=secret;
        cloudinary.config.apiKey=key;

    }
    public Map upload(Object file, Map options){
        try{
            return cloudinary.uploader().upload(file, options);
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
    public String createUrl(String content, String posteddate,
                            String sentby){

        return cloudinary.url().transformation(new Transformation().width(content).height(posteddate).border("2px_solid_black").crop(sentby)).imageTag(content);
    }
}
