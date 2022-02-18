package controllers;

import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.ToDoListModel;
import models.UserLogin;
import models.UserModel;
import services.impl.ToDoListService;
import services.impl.UserService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import static helper.UrlViewsConst.*;

public class LoginController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXButton btnLogin;

    @FXML
    private JFXButton btnSignUp;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label notiLable;

    @FXML
    void initialize() {
        btnSignUp.setOnAction(actionEvent -> {
            String title = "Register";
            this.switchScreen(SIGNUP_VIEW, title, false);
        });

        btnLogin.setOnAction(actionEvent -> {
            String username = this.username.getText().toString();
            String password = this.password.getText().toString();

            if (!username.equals("") && !password.equals("")){
                UserService userService = new UserService();
                password = userService.getHashPassword(password);

                UserModel userModel = userService.selectByUsernameAndPassword(username, password);

                UserLogin userLogin = UserLogin.getINSTANCE();
                userLogin.setUserModel(userModel);

                if (userModel != null) {
                    ToDoListService toDoListService = new ToDoListService();
                    if (toDoListService.selectByUserAndDescriptionAndState(UserLogin.getINSTANCE().getUserModel(), "Important", "important") == null) {
                        ToDoListModel toDoList = new ToDoListModel();
                        toDoList.setDescription("Important");
                        toDoList.setDateCreate(new Date(System.currentTimeMillis()));
                        toDoList.setUser(UserLogin.getINSTANCE().getUserModel());
                        toDoList.setState("important");

                        toDoListService.insert(toDoList);
                    }

                    String title = "Dashboard";
                    this.switchScreen(DASHBOARD_VIEW, title, true);
                } else {
                    this.notiLable.setText("The username or password that you've entered is incorrect. Try again!");
                    this.notiLable.setVisible(true);
                    new Shaker(this.username).shake();
                    new Shaker(this.password).shake();
                }
            } else {
                this.notiLable.setText("Please fill username and password before press Login!");
                this.notiLable.setVisible(true);
                new Shaker(this.username).shake();
                new Shaker(this.password).shake();
            }
        });
    }

    public void switchScreen (String url, String title, boolean isHide) {
        if (isHide) {
            btnSignUp.getScene().getWindow().hide();
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(url));

        try {
            loader.load();

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            //stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}