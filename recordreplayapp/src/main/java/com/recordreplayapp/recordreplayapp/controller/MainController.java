package com.recordreplayapp.recordreplayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.recordreplayapp.recordreplayapp.entity.Post;
import com.recordreplayapp.recordreplayapp.model.Response;
import com.recordreplayapp.recordreplayapp.services.PostService;

@RestController
@RequestMapping("/api")
public class MainController {
    
    @Autowired
    private RestTemplate restTemplate; 

    @Autowired
    private PostService PostService;

    @Value("${HT_MODE}")
    private String mode;

    @RequestMapping("/createNewPost")
    public ResponseEntity<Response> comment(@RequestBody Post post) {
        Post p;
        String worldTimeApiResponse="";
        if(isRecordMode()){
            try {
                p=PostService.saveComment(post);
                String worldTimeApiUrl = "http://worldtimeapi.org/api/timezone/Asia/Kolkata";
                worldTimeApiResponse = restTemplate.getForObject(worldTimeApiUrl, String.class);
                
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else{
            p=new Post(1,"testing","epic");
        }
        Response response =new Response(p.getId(),worldTimeApiResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }    

    private boolean isRecordMode() {
        return "RECORD".equalsIgnoreCase(mode);
    }
}