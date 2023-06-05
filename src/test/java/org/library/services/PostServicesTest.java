package org.library.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.dto.PostDTO;
import org.library.models.User;
import org.library.models.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostServicesTest {
    private static PostServices postServices;
    private static UserServices userServices;
    private static User user;
    private static Post post;
    @BeforeEach
    void setUp() {
        postServices = new PostServices("socials-pu-test");
        userServices = new UserServices("socials-pu-test");

        user = userServices.registerUser("Aliu", "12345");
    }

    @AfterEach
    void tearDown() {
        postServices.close();
    }

    @Test
    void testCreatePost() {
        post = postServices.createPost("Welcome", "Hi Everyone", user);

        assertNotNull(post);
    }

    @Test
    void testGetAllPosts() {
        postServices.createPost("Welcome", "Anyone here", user);
        List<PostDTO> postList = postServices.getAllPosts();

        assertEquals(1, postList.size());
    }

    @Test
    void testFindPostByTitle() {
        postServices.createPost("Welcome", "Anyone here", user);
        post = postServices.findPostByTitle("Welcome");
        assertNotNull(post);
    }

    @Test
    void testFindPostByUsername(){
        postServices.createPost("Welcome", "Anyone here", user);
        Post post = postServices.findPostByUsername("Aliu");
        assertNotNull(post);
    }

}