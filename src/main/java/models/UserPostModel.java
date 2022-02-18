package models;

import dao.IPostReactionEmoticonDAO;
import dao.impl.PostReactionEmoticonDAO;
import services.IPostReactionEmoticonService;
import services.impl.PostCommentService;
import services.impl.PostReactionEmoticonService;
import services.impl.PostShareService;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "user_post")
public class UserPostModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "written_text", length = 1000)
    private String writtenText;

    @Column(name = "image", length = 1000)
    private String image;

    @Column(name = "date_create", nullable = false)
    private Timestamp dateCreate;

    @ManyToOne
    private UserModel user;

    public UserPostModel() {
    }

    public UserPostModel(String writtenText, String image, Timestamp dateCreate, UserModel user) {
        this.writtenText = writtenText;
        this.image = image;
        this.dateCreate = dateCreate;
        this.user = user;
    }

    public UserPostModel(String writtenText, Timestamp dateCreate, UserModel user) {
        this.writtenText = writtenText;
        this.dateCreate = dateCreate;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWrittenText() {
        return writtenText;
    }

    public void setWrittenText(String writtenText) {
        this.writtenText = writtenText;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Long getNumberReactionEmoticon () {
        PostReactionEmoticonService postReactionEmoticonService = new PostReactionEmoticonService();

        return postReactionEmoticonService.selectNumberReactionEmoticonOfPost(this);
    }

    public Long getNumberComment () {
        PostCommentService postCommentService = new PostCommentService();

        return postCommentService.selectNumberCommentOfPost(this);
    }

    public Long getNumberShare () {
        PostShareService postShareService = new PostShareService();

        return postShareService.selectNumberShareOfPost(this);
    }

    @Override
    public String toString() {
        return "UserPostModel{" +
                "id=" + id +
                ", writtenText='" + writtenText + '\'' +
                ", image='" + image + '\'' +
                ", dateCreate=" + dateCreate +
                ", user=" + user +
                '}';
    }
}
