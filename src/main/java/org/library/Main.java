package org.library;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.library.application.App;
import org.library.application.manager.UserManager;
import org.library.dto.UserDTO;
import org.library.services.CommentServices;
import org.library.services.PostServices;
import org.library.services.UserServices;
import java.util.Scanner;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);
    private static Scanner sc;

    public static int setup(){
        logger.info("Please Enter 1 to register as a user");
        logger.info("or 2 to sign in");
        logger.info("or Enter 0 to end the program");

        sc = new Scanner(System.in);
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
                        return 1;
                    case 2:
                        return 2;
                    default:
                        logger.warn("\nPlease enter either 1 or 2 ");
                        break;
                }

            } catch (final NumberFormatException e) {
                logger.warn("\nPlease enter a valid number: ");
            }
        }
        while (choice != 0);

        return -1;
    }

    public static void main(String[] args) {

        logger.info(">>>>>>>>> TechiesBlog <<<<<<<<<<");
        logger.info("Welcome, share your discovery!!\n");

        int mode = setup();
        switch (mode) {
            case 1:
                UserManager.createUser();
                break;
            case 2:
                try{
                    UserDTO user = UserManager.login();
                    App.run(user.getUsername());
                } catch (NullPointerException e){
                    logger.warn("Incorrect login details");
                }
                break;
            default:
                break;
        }
    }
}