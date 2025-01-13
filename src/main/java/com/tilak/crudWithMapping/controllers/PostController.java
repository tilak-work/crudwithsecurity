package com.tilak.crudWithMapping.controllers;
import com.tilak.crudWithMapping.entities.Post;
import com.tilak.crudWithMapping.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Endpoint to fetch all posts
    @GetMapping
    public List<Post> getAllPosts() {
        System.err.println(postService.getAllPosts());
        return postService.getAllPosts();
    }

    // Endpoint to fetch a post by ID
    @GetMapping("/{id}")
    public Post getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }
}
