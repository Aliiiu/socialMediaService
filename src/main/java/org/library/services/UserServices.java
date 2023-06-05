package org.library.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.library.application.manager.PostManager;
import org.library.dto.UserDTO;
import org.library.models.QUser;
import org.library.models.User;
//import org.library.models.QPerson;


public class UserServices {
    private static final Logger logger = LogManager.getLogger(UserServices.class);
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    private JPAQueryFactory queryFactory;

    public UserServices(String pu){
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    public User registerUser(String username, String password){
        User userFound = findByUsername(username);
        if (userFound == null){
            User user = new User(username, password);
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
            logger.info("User with name {} has registered successfully", username);
            return user;
        }
        logger.info("User already exist");
        return null;
    }

    public User findByUsername (String username){
        QUser quser  = QUser.user;
        return queryFactory
                .selectFrom(quser).where(quser.username.eq(username)).fetchOne();
    }

    public User signIn(String username, String password){
        User user = findByUsername(username);
        if (user != null && user.getPassword().equals(password)){
            return user;
        }
        logger.info("Incorrect username");
        return null;
    }

    public void deleteUser(User user){
        entityManager.getTransaction().begin();
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    public static UserDTO toUserDTO(User user) {
        UserDTO newUser = new UserDTO();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        return newUser;
    }

    public void close(){
        entityManager.close();
        emf.close();
    }
}
