package org.library.application.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.library.dto.CommentDTO;
import org.library.models.Comment;
import org.library.models.User;
import org.library.models.Post;
import org.library.services.CommentServices;
import org.library.services.PostServices;
import org.library.services.UserServices;

import java.util.List;
import java.util.Scanner;

public class CommentManager {
    private static final Logger logger = LogManager.getLogger(CommentManager.class);
    private static CommentServices commentServices = new CommentServices("socials-pu");
    private static UserServices userServices = new UserServices("socials-pu");
    private static PostServices postServices = new PostServices("socials-pu");
    private static Scanner sc;

    public static void createComment(Post post, User user){
        sc = new Scanner(System.in);
        logger.info("Enter comment");
        String comment = sc.nextLine();
        commentServices.createComment(comment, post, user);
        logger.info("Comment created successfully");
    }
    public static void run (Post post, User user){

        int choice = 0;
        sc = new Scanner(System.in);
        do {
            logger.info("Enter '1' to see all post comment.");
            logger.info("Enter '2' to comment.");
            logger.info("Or enter 0 to end the program");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 0:
                        logger.info("Exiting Program...");
                        System.exit(0);
                    case 1:
                        List<CommentDTO> commentList = commentServices.getAllCommentsOnPost(post.getId());
                        for(CommentDTO comment : commentList){
                            System.out.println(comment);
                        }
                        break;
                    case 2:
                        createComment(post, user);
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
