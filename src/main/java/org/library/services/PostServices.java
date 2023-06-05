package org.library.services;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import org.library.dto.PostDTO;
import org.library.models.User;
import org.library.models.Post;
import org.library.models.QPost;

import java.util.ArrayList;
import java.util.List;


public class PostServices {
    private final EntityManager entityManager;
    private final EntityManagerFactory emf;
    private final JPAQueryFactory queryFactory;
    private final QPost qpost;


    public PostServices(String pu){
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.qpost = QPost.post;
    }

    public Post createPost(String title, String content, User user){
        Post post = new Post(title, content, user);
        entityManager.getTransaction().begin();
        entityManager.persist(post);
        entityManager.getTransaction().commit();
        return post;
    }

    public List<PostDTO> getAllPosts(){
        List<Post> postList = queryFactory.selectFrom(qpost).fetch();
        return toPostDTO(postList);
    }

    public Post findPostByTitle(String title){
        return queryFactory
                .selectFrom(qpost).where(qpost.title.eq(title)).fetchOne();
    }
    public Post findPostByUsername(String username){
        return queryFactory
                .selectFrom(qpost).where(qpost.user.username.eq(username)).fetchOne();
    }

    public void deletePost(Post post){
        entityManager.getTransaction().begin();
        entityManager.remove(post);
        entityManager.getTransaction().commit();
    }

    public static PostDTO toPostDTO(Post post) {
        PostDTO newPost = new PostDTO();
        newPost.setTitle(post.getTitle());
        newPost.setContent(post.getContent());
        newPost.setUser(UserServices.toUserDTO(post.getUser()));
        return newPost;
    }

    public static List<PostDTO> toPostDTO(List<Post> posts) {
        List<PostDTO> postDTOS = new ArrayList<>();

        for (Post post : posts) {
            postDTOS.add(toPostDTO(post));
        }
        return postDTOS;
    }

    public void close(){
        entityManager.close();
        emf.close();
    }
}
