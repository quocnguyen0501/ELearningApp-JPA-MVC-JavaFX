package controllers;

import com.jfoenix.controls.JFXButton;
import helper.CourseContent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.CourseModel;

import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import static helper.CourseContent.*;
import static helper.UrlViewsConst.*;

public class CourseController extends BaseController implements Initializable {
    @FXML
    private ResourceBundle resources;

    @FXML
    private ImageView img;

    @FXML
    private Label algorithmName;

    @FXML
    private Label content1;

    @FXML
    private Label content2;

    @FXML
    private Label content3;

    @FXML
    private Label content4;

    @FXML
    private JFXButton btnTraining;

    public void setData (CourseModel course) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(course.getSourceImageSrc())));
        this.img.setImage(image);

        this.algorithmName.setText(course.getName());
        this.content1.setText(course.getContent().get(0));
        this.content2.setText(course.getContent().get(1));
        this.content3.setText(course.getContent().get(2));
        this.content4.setText(course.getContent().get(3));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnTraining.setOnAction(actionEvent -> {
            if (this.algorithmName.getText().equals(SORTING.getName()))
            this.switchScreen(SORT_VIEW);
        });
    }
}
