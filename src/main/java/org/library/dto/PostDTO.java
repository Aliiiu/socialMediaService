package org.library.dto;

import org.library.models.User;

public class PostDTO {
    private String title;

    private String content;

    private UserDTO user;

    public PostDTO () {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                " title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", user=" + user.getUsername() +
                '}';
    }
}
