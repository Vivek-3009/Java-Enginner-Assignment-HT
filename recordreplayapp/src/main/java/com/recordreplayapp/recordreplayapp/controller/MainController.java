package com.recordreplayapp.recordreplayapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.recordreplayapp.recordreplayapp.entity.Post;
import com.recordreplayapp.recordreplayapp.services.PostService;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private PostService PostService;

    @Value("${HT_MODE}")
    private String mode;

    @RequestMapping("/createNewPost")
    public ResponseEntity<Integer> comment(@RequestBody Post post) {
        Post p;
        if(isRecordMode()){
            try {
                p=PostService.saveComment(post);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        }
        else{
            p=new Post(1,"testing","epic");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(p.getId());
    }    

    private boolean isRecordMode() {
        return "RECORD".equalsIgnoreCase(mode);
    }
}