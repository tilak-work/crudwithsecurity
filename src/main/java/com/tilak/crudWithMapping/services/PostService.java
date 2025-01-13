package com.tilak.crudWithMapping.services;


import com.tilak.crudWithMapping.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class PostService {

    private static final String POST_API_URL = "https://jsonplaceholder.typicode.com/posts";

    @Autowired
    private RestTemplate restTemplate;

    // Fetch all posts
    public List<Post> getAllPosts() {
        Post[] posts = restTemplate.getForObject(POST_API_URL, Post[].class);
        return Arrays.asList(posts);
    }

    // Fetch a post by its ID
    public Post getPostById(int id) {
        String url = POST_API_URL + "/" + id;
        return restTemplate.getForObject(url, Post.class);
    }
}