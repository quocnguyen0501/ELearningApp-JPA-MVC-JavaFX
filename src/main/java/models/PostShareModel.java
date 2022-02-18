package models;

import javax.persistence.*;

@Entity
@Table (name = "share")
public class PostShareModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private UserPostModel post;

    public PostShareModel(UserModel user, UserPostModel post) {
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public UserPostModel getPost() {
        return post;
    }

    public void setPost(UserPostModel post) {
        this.post = post;
    }
}
