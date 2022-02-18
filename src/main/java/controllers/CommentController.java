package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import models.PostCommentModel;

import java.util.Objects;

public class CommentController {
    @FXML
    private Label content;

    @FXML
    private ImageView imgProfile;

    @FXML
    private Label time;

    @FXML
    private Label name;

    public void setData (PostCommentModel comment) {
        //Avatar
        Image image;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(comment.getUser().getAvatar())));
        this.imgProfile.setImage(image);
        Circle clip = new Circle();
        clip.setCenterX(25.0f);
        clip.setCenterY(25.0f);
        clip.setRadius(25.0f);
        this.imgProfile.setClip(clip);

        this.content.setText(comment.getCommentText());

        this.time.setText(comment.getDateCreate().toString());

        String name = comment.getUser().getFirstName() + " " + comment.getUser().getSurname();
        this.name.setText(name);
    }
}
