package controllers;

import com.jfoenix.controls.JFXButton;
import helper.Month;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.UserModel;
import services.impl.UserService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import static helper.UrlViewsConst.*;

public class SignUpController {
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
    private JFXButton signup;

    @FXML
    private TextField surName;

    @FXML
    private TextField username;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    void initialize() {
        this.setDay();
        this.setMonth();
        this.setYear();

        signup.setOnAction(actionEvent -> {
            UserService userService = new UserService();
            String firstName = this.firstName.getText();
            String surName = this.surName.getText();
            String username = this.username.getText();
            String password = this.password.getText();
            password = userService.getHashPassword(password);
            Date dob = this.setDOB();
            String gender = this.setGender();
            String avatarUrl = this.setAvatar(Objects.requireNonNull(gender));

            System.out.println(firstName + surName + username + password + dob.toString()  + gender);

            UserModel userModel = new UserModel(firstName, surName, username, password, dob, gender, avatarUrl);
            String result = userService.register(userModel);
        });
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

    private void setDay () {
        List<Integer> listDay = new ArrayList<>();
        for (int i = 1; i < 32; i++) {
            listDay.add(i);
        }
        this.day.getItems().addAll(listDay);
        this.day.setValue(day.getItems().get(Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1));
    }

    private void setMonth () {
        Month[] months = Month.values();
        this.month.getItems().addAll(months);
        for (Month m : months) {
            if (m.getNumber() == Calendar.getInstance().get(Calendar.MONTH) + 1) {
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
        this.year.setValue(this.year.getItems().get(0));
    }

    private String setAvatar (String gender) {
        return gender.equals("male") ? MALE_AVATAR : FEMALE_AVATAR;
    }
}
