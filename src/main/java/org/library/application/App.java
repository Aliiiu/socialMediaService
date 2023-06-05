package org.library.application;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.library.application.manager.PostManager;
import org.library.dto.PostDTO;
import org.library.services.PostServices;

import java.util.List;
import java.util.Scanner;

public class App {
    private static final Logger logger = LogManager.getLogger(App.class);
    private static final PostServices postServices = new PostServices("socials-pu");
    private static Scanner sc;



    public static void run (String username){
        sc = new Scanner(System.in);
        int choice = 0;
        logger.info("Welcome {}", username);
        do {
            logger.info("Enter '1' to see the list of posts.");
            logger.info("Enter '2' to create your post.");
            logger.info("Enter '3' to search for post.");
            logger.info("Or enter 0 to end the program");
            try {
                choice = Integer.parseInt(sc.nextLine());
                switch (choice) {
                    case 0:
                        logger.info("Exiting Program...");
                        System.exit(0);
                    case 1:
                        List<PostDTO> postList = postServices.getAllPosts();
                        for(PostDTO post : postList){
                            System.out.println(post);
                        }
                        break;
                    case 2:
                        PostManager.createPost(username);
                        break;
                    case 3:
                        PostManager.searchPost(username);
                        break;
                    default:
                        logger.warn("\nPlease enter a number between 0 and 3 ");
                        break;
                }
            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            }
        }
        while (choice !=0);
    }
}
