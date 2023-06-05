package org.library.application.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.library.dto.PostDTO;
import org.library.dto.UserDTO;
import org.library.models.User;
import org.library.models.Post;
import org.library.services.PostServices;
import org.library.services.UserServices;

import java.util.Scanner;

public class PostManager {
    private static final Logger logger = LogManager.getLogger(PostManager.class);
    private static final PostServices postServices = new PostServices("socials-pu");
    private static final UserServices userServices = new UserServices("socials-pu");
    private static Scanner sc;

    public static void searchPost(String username){
        sc = new Scanner(System.in);
        logger.info("Enter post title");
        String title = sc.nextLine();

        try {
            Post post = postServices.findPostByTitle(title);
            run(post, username);
        } catch (Exception e){
            logger.info("Enter a valid post title");
        }
    }

    public static void createPost(String username){
        User user = userServices.findByUsername(username);
        sc = new Scanner(System.in);
        logger.info("Enter your title ");
        String title = sc.nextLine();
        logger.info("Enter your content");
        String content = sc.nextLine();

        if (content.length() > 0 && title.length() > 0){
            postServices.createPost(title, content, user);
            logger.info("Post successfully created by {}", user.getUsername());
        } else {
            logger.warn("Create a valid post");
        }
    }

    public static void run(Post post, String username){
        User user = userServices.findByUsername(username);

        int choice = 0;
        sc = new Scanner(System.in);
        logger.info("Found post with title {}", post.getTitle());
        do {
            logger.info("Enter '1' to see post content.");
            logger.info("Enter '2' to comment.");
            logger.info("Or enter 0 to end the program");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 0:
                        logger.info("Exiting Program...");
                        System.exit(0);
                    case 1:
                        logger.info(post.getContent());
                        break;
                    case 2:
                        CommentManager.run(post, user);
                        break;
                    default:
                        logger.warn("\nPlease enter a number between 0 and 3 ");
                }
            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            }
        }
        while (choice !=0);
    }
}
