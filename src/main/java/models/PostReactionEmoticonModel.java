package models;

import javax.persistence.*;

@Entity
@Table(name = "reaction_emoticon")
public class PostReactionEmoticonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reaction_emoticon", length = 6, nullable = false)
    private String reactionEmoticon = "None";

    @ManyToOne
    private UserModel user;

    @ManyToOne
    private UserPostModel post;

    public PostReactionEmoticonModel() {
    }

    public PostReactionEmoticonModel(UserModel user, UserPostModel post) {
        this.user = user;
        this.post = post;
    }

    public PostReactionEmoticonModel(String reactionEmoticon, UserModel user, UserPostModel post) {
        this.reactionEmoticon = reactionEmoticon;
        this.user = user;
        this.post = post;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReactionEmoticon() {
        return reactionEmoticon;
    }

    public void setReactionEmoticon(String reactionEmoticon) {
        this.reactionEmoticon = reactionEmoticon;
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
