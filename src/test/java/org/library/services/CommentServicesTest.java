package org.library.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.library.dto.CommentDTO;
import org.library.models.Comment;
import org.library.models.User;
import org.library.models.Post;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServicesTest {
    private static CommentServices commentServices;
    private static UserServices userServices;
    private static PostServices postServices;
    private static User user;
    private static Post post;
    private static Comment comment;

    @BeforeEach
    void setUp() {
        commentServices = new CommentServices("socials-pu-test");
        postServices = new PostServices("socials-pu-test");
        userServices = new UserServices("socials-pu-test");

        user = userServices.registerUser("Aliu", "12345");
        post = postServices.createPost("Welcome", "Hi everyone", user);
    }

    @AfterEach
    void tearDown() {
        commentServices.close();
        userServices.close();
        postServices.close();
    }

    @Test
    void testCreateComment() {
        comment  = commentServices.createComment("To everyone", post, user);
        assertNotNull(comment);
    }

    @Test
    void testGetAllCommentsOnPost() {
        commentServices.createComment("To everyone", post, user);
        List<CommentDTO> commentList = commentServices.getAllCommentsOnPost(post.getId());

        assertEquals(1, commentList.size());
    }

    @Test
    void  testGetAllCommentsOnPostByUser(){
        commentServices.createComment("To everyone", post, user);
        List<CommentDTO> commentList  = commentServices.getAllCommentsByOnPostByUser(post.getId(), user.getUsername());
        assertTrue(commentList.size() > 0);
    }
}