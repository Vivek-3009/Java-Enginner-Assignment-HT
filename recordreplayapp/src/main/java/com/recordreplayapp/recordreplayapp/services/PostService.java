package com.recordreplayapp.recordreplayapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordreplayapp.recordreplayapp.entity.Post;
import com.recordreplayapp.recordreplayapp.repository.PostRepository;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public Post saveComment(Post post){
        return postRepository.save(post);
    }

    
}