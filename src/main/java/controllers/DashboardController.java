package controllers;

import com.jfoenix.controls.JFXButton;
import helper.CourseContent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import models.*;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

import static helper.UrlViewsConst.*;

public class DashboardController extends BaseController implements Initializable{
    @FXML
    private GridPane sourceGird;

    @FXML
    private Label firstAndSurName;

    @FXML
    private ImageView imgUser;

    @FXML
    private Label username;

    @FXML
    private JFXButton btnCourse;

    @FXML
    private JFXButton btnForum;

    @FXML
    private JFXButton btnLogout;

    @FXML
    private JFXButton btnProfile;

    @FXML
    private JFXButton btnQuiz;

    @FXML
    private JFXButton btnSetting;

    @FXML
    private JFXButton btnToDo;

    @FXML
    private JFXButton btnUsers;

    @FXML
    private Label titleContent;

    @FXML
    private Label titlePage;

    @FXML
    void handleCourseClicked(ActionEvent event) {
        this.getSource();
    }

    @FXML
    void handleForumClicked(ActionEvent event) {
        this.deleteAllItemsInGrid(this.sourceGird);

        this.titlePage.setText("Forum");

        int columns = 0;
        int rows = 1;

        try {FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(POST_STATUS_VIEW));

            VBox postBox = postBox = fxmlLoader.load();

            sourceGird.add(postBox, columns++, rows);
            GridPane.setMargin(postBox, new Insets(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleBtnSettingClicked(ActionEvent event) {
        this.deleteAllItemsInGrid(this.sourceGird);

        this.titlePage.setText("Setting Profile");

        int columns = 0;
        int rows = 1;

        try {FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(SETTING_VIEW));

            VBox postBox = postBox = fxmlLoader.load();

            sourceGird.add(postBox, columns, rows);
            GridPane.setMargin(postBox, new Insets(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleToDoClicked(ActionEvent event) {
        this.deleteAllItemsInGrid(this.sourceGird);

        this.titlePage.setText("To Do List");

        int columns = 1;
        int rows = 1;

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource(TODOlIST_VIEW));

        BorderPane borderPane;
        try {
            borderPane = fxmlLoader.load();
            //StatusController statusController = fxmlLoader.getController();

            sourceGird.add(borderPane, columns, rows);
            GridPane.setMargin(borderPane, new Insets(10));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void handleLogOutClicked(ActionEvent event) {
        //Hide Current Windows if you want
        btnLogout.getScene().getWindow().hide();

        this.switchScreen(LOGIN_VIEW);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserLogin userLogin = UserLogin.getINSTANCE();
        UserModel userModel = userLogin.getUserModel();
        this.setUser(userModel);

        this.getSource();
    }

    private List<CourseModel> data () {
        List<CourseModel> list = new ArrayList<>();

        CourseContent[] temp = CourseContent.values();
        for (CourseContent courseContent : temp) {
            CourseModel courseModel = new CourseModel();
            courseModel.setName(courseContent.getName());
            courseModel.setSourceImageSrc(courseContent.getImgSrc());
            courseModel.setContent(courseContent.getListContent());

            list.add(courseModel);
        }

        return list;
    }

    private void setUser (UserModel userModel) {
        firstAndSurName.setText(userModel.getFirstName() + " " + userModel.getSurname());
        username.setText("@" + userModel.getUsername());

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(userModel.getAvatar())));
        imgUser.setImage(image);
        Circle clip = new Circle();
        clip.setCenterX(80.0f);
        clip.setCenterY(80.0f);
        clip.setRadius(80.0f);
        imgUser.setClip(clip);
    }

    private void deleteAllItemsInGrid (GridPane gridPane) {
        gridPane.getColumnConstraints().clear();
        gridPane.getRowConstraints().clear();
        gridPane.getChildren().clear();
    }

    private void getSource () {
        this.deleteAllItemsInGrid(this.sourceGird);

        this.titlePage.setText("Choose Algorithm");
        this.titleContent.setText("Algorithms");
        this.titleContent.setVisible(true);
        List<CourseModel> courseModelList = new ArrayList<>(data());

        int columns = 0;
        int rows = 1;

        try {
            for (CourseModel courseModel : courseModelList) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(SOURCE_ELEMENT_VIEW));

                VBox postBox = fxmlLoader.load();

                CourseController courseController = fxmlLoader.getController();
                courseController.setData(courseModel);

                if (columns == 3) {
                    columns = 0;
                    ++rows;
                }

                sourceGird.add(postBox, columns++, rows);
                GridPane.setMargin(postBox, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
