package org.library.dto;

public class CommentDTO {
    private String content;

    private PostDTO postDTO;

    private UserDTO userDTO;

    public CommentDTO() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostDTO getPost() {
        return postDTO;
    }

    public void setPost(PostDTO post) {
        this.postDTO = post;
    }

    public UserDTO getUser() {
        return userDTO;
    }

    public void setUser(UserDTO user) {
        this.userDTO = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                " content='" + content + '\'' +
                ", post=" + postDTO.getTitle() +
                ", user=" + userDTO.getUsername() +
                '}';
    }
}
