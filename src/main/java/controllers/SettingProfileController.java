package controllers;

import com.jfoenix.controls.JFXButton;
import helper.Month;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import models.UserLogin;
import models.UserModel;
import services.impl.UserService;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class SettingProfileController implements Initializable {
    @FXML
    private ImageView avatar;

    @FXML
    private JFXButton btnChange;

    @FXML
    private ChoiceBox<Integer> day;

    @FXML
    private RadioButton female;

    @FXML
    private TextField firstName;

    @FXML
    private ToggleGroup gender;

    @FXML
    private RadioButton male;

    @FXML
    private ChoiceBox<Month> month;

    @FXML
    private PasswordField password;

    @FXML
    private TextField surName;

    @FXML
    private JFXButton update;

    @FXML
    private TextField username;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private Label notification;

    private static String URI_AVATAR;

    @FXML
    void handleChangeAvatar(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose an image");
        FileChooser.ExtensionFilter filter = new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            URI_AVATAR = file.toURI().toString();
            Image image = new Image(URI_AVATAR);
            this.avatar.setImage(image);
        }
    }

    @FXML
    void handleUpdate(ActionEvent event) {
        UserService userService = new UserService();
        String firstName = this.firstName.getText();
        String surName = this.surName.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        password = userService.getHashPassword(password);
        Date dob = this.setDOB();
        String gender = this.setGender();

        System.out.println(firstName + surName + username + password + dob.toString()  + gender);

        Image imageToBeSaved = this.avatar.getImage();
        String pathSave = this.getURI();
        this.saveImage(imageToBeSaved, pathSave);
        String img = this.getURLImgDisplay();

        UserModel userModel = new UserModel(firstName, surName, username, password, dob, gender, img);
        userModel.setUser_id(user.getUser_id());
        userService.update(userModel);

        this.notification.setText("Update success! Please restart the application to see result");
        this.notification.setVisible(true);
    }

    private final UserModel user = UserLogin.getINSTANCE().getUserModel();
    private final LocalDate localDate = user.getDateOfBirth().toLocalDate();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.firstName.setText(user.getFirstName());
        this.surName.setText(user.getSurname());
        this.username.setText(user.getUsername());

        this.setDay();
        this.setMonth();
        this.setYear();

        Image image;
        image = new Image(Objects.requireNonNull(getClass().getResourceAsStream(user.getAvatar())));
        this.avatar.setImage(image);
        Circle clip = new Circle();
        clip.setCenterX(100.0f);
        clip.setCenterY(100.0f);
        clip.setRadius(100.0f);
        this.avatar.setClip(clip);
    }

    private void setDay () {
        List<Integer> listDay = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            listDay.add(i);
        }
        this.day.getItems().addAll(listDay);
        this.day.setValue(day.getItems().get(localDate.getDayOfMonth()) - 1);
    }

    private void setMonth () {
        Month[] months = Month.values();
        this.month.getItems().addAll(months);
        for (Month m : months) {
            if (m.getNumber() == localDate.getMonthValue()) {
                this.month.setValue(m);
            }
        }
    }

    private void setYear () {
        List<Integer> years = new ArrayList<>();
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        for (int i = 0; i <= 100; i++) {
            years.add(currentYear - i);
        }
        this.year.getItems().addAll(years);
        this.year.setValue(localDate.getYear());
    }

    private Date setDOB () {
        String day = this.day.getValue().toString();
        int monthNum = Month.valueOf(this.month.getValue().toString()).getNumber();
        String year = this.year.getValue().toString();

        String DOB = year + "-" + monthNum + "-" + day;
        return Date.valueOf(DOB);
    }

    private String setGender () {
        if (this.male.isSelected()) {
            return  "male";
        } else if (this.female.isSelected()) {
            return "female";
        } else {
            return null;
        }
    }

    //===================== SAVE IMAGE ===========================
    private void saveImage (Image image, String pathSave) {
        File file = new File(pathSave);
        try {
            String typeImage = this.getTypeImage();
            ImageIO.write(SwingFXUtils.fromFXImage(image, null), typeImage, file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTypeImage () {
        int index = URI_AVATAR.lastIndexOf('.');
        return URI_AVATAR.substring(index + 1);
    }

    private String getURI () {
        int index = URI_AVATAR.lastIndexOf(File.separator);

        File fileCurrent = new File("");
        String currentDirectory = fileCurrent.getAbsolutePath();

        String folder = "/src/main/resources/assets/images/avatar/store/";
        folder = folder.replace('/', File.separator.charAt(0));

        return currentDirectory + folder + URI_AVATAR.substring(index + 1);
    }

    private String getURLImgDisplay () {
        int index = URI_AVATAR.lastIndexOf(File.separator);

        String folder = "/assets/images/avatar/store/";
        folder = folder.replace('/', File.separator.charAt(0));

        return folder + URI_AVATAR.substring(index + 1);
    }
}
