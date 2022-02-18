package models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "comment")
public class PostCommentModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "comment_text", length = 1000, nullable = false)
    private String commentText;

    @Column (name = "date_create", nullable = false)
    private Timestamp dateCreate;

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private UserPostModel post;

    public PostCommentModel() {
    }

    public PostCommentModel(String commentText, Timestamp dateCreate, UserModel user, UserPostModel post) {
        this.commentText = commentText;
        this.dateCreate = dateCreate;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Timestamp getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Timestamp dateCreate) {
        this.dateCreate = dateCreate;
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
