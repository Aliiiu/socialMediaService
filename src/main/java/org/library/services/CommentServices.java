package org.library.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.*;
import org.library.dto.CommentDTO;
import org.library.models.Comment;
import org.library.models.User;
import org.library.models.Post;
import org.library.models.QComment;

import java.util.ArrayList;
import java.util.List;

public class CommentServices {
    private EntityManager entityManager;
    private EntityManagerFactory emf;
    private JPAQueryFactory queryFactory;
    private QComment qcomment;

    public CommentServices(String pu){
        this.emf = Persistence.createEntityManagerFactory(pu);
        this.entityManager = this.emf.createEntityManager();
        this.queryFactory = new JPAQueryFactory(entityManager);
        this.qcomment = QComment.comment;
    }

    public Comment createComment(String content, Post post, User user){
        Comment comment = new Comment(content, post, user);
        entityManager.getTransaction().begin();
        entityManager.persist(comment);
        entityManager.getTransaction().commit();
        return comment;
    }

    public List<CommentDTO> getAllCommentsOnPost(Long id){
        return toCommentDTO(queryFactory.selectFrom(qcomment).where(qcomment.post.postId.eq(id)).fetch());
    }
    public List<CommentDTO> getAllCommentsByOnPostByUser(Long id, String username){
        return toCommentDTO(queryFactory.selectFrom(qcomment).where(qcomment.post.postId.eq(id)).where(qcomment.user.username.eq(username)).fetch());
    }

//    public List<>
    public void deleteComment(Comment comment){
        entityManager.getTransaction().begin();
        entityManager.remove(comment);
        entityManager.getTransaction().commit();
    }
    public static CommentDTO toCommentDTO(Comment comment) {
        CommentDTO newComment = new CommentDTO();
        newComment.setContent(comment.getContent());
        newComment.setPost(PostServices.toPostDTO(comment.getPost()));
        newComment.setUser(UserServices.toUserDTO(comment.getUser()));
        return  newComment;
    }

    public static List<CommentDTO> toCommentDTO(List<Comment> comments) {
        List<CommentDTO> commentDTOs = new ArrayList<>();

        for (Comment comment : comments) {
            commentDTOs.add(toCommentDTO(comment));
        }
        return commentDTOs;
    }

    public void close(){
        entityManager.close();
        emf.close();
    }
}
