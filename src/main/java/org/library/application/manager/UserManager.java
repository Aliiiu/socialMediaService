package org.library.application.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.library.dto.UserDTO;
import org.library.models.User;
import org.library.services.UserServices;

import java.util.Scanner;

public class UserManager {
    private static final Logger logger = LogManager.getLogger(PostManager.class);
    private static final UserServices userServices = new UserServices("socials-pu");
    private static Scanner sc;
    public static void createUser (){
        sc = new Scanner(System.in);
        logger.info("\nPlease enter 1 to continue or '0' to exit: ");
        int choice = -1;
        do {
            try {
                String input = sc.nextLine();
                choice = Integer.parseInt(input);
                switch (choice) {
                    case 0:
                        logger.info("Exiting Program...");
                        System.exit(0);
                    case 1:
                        logger.info("\nPlease enter your preffered username: ");
                        String username = sc.nextLine();
                        logger.info("\nPlease enter your preffered password: ");
                        String password = sc.nextLine();
                        userServices.registerUser(username, password);
                        break;
                    default:
                        logger.warn("\nPlease enter either 1 or 2 ");
                        break;
                }

            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            }

        }
        while (choice != 0);
    }
    public static UserDTO login(){
        UserDTO user = new UserDTO();
        sc = new Scanner(System.in);
        logger.info("\nPlease enter your username: ");
        String username = sc.nextLine();
        logger.info("\nEnter your password: ");
        String password = sc.nextLine();
        user = UserServices.toUserDTO(userServices.signIn(username, password));


//        do {
//            try {
//                input = sc.nextLine();
//                if (input.equals("0")) {
//                    logger.info("\nExiting program");
//                    System.exit(0);
//                }
//                String username = input;
//                user = USER_SERVICES.findByUsername(username);
//                //Get password to confirm login
//                if (user != null){
//                    logger.info("Please enter your password: ");
//                    input = sc.nextLine();
//                    if (user.getPassword().equals(input)){
//                        //Login successful
//                        break;
//                    }
//                    else{
//                        logger.error("Invalid password");
//                    }
//                }
//                //If account does not exist
//                else{
//                    logger.error("Wrong user");
//                    logger.info("Please enter a valid username or create an account");
//                }
//            } catch (final NumberFormatException e) {
//                logger.warn("\nPlease enter a valid number: ");
//            }
//        }
//        while (!input.equals("0"));
        return user;
    }
}
