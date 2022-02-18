package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import models.TaskModel;
import services.impl.StepService;
import services.impl.TaskService;

import java.sql.Date;

import static helper.ImgConst.*;
import static helper.StaticValue.*;

public class TaskElementController {
    @FXML
    private ImageView imgState;

    @FXML
    private Label numberStep;

    @FXML
    private Button btnComplete;

    @FXML
    private Button btnRemove;

    @FXML
    private JFXButton btnTaskDescription;

    @FXML
    private Label idTask;

    @FXML
    void handleBtnTaskDescriptionClick(ActionEvent event) {
        ID_TASK = Long.parseLong(idTask.getText());
    }

    @FXML
    void handleBtnCompleteClicked(ActionEvent event) {
        TaskService taskService = new TaskService();
        long idTask = Long.parseLong(this.idTask.getText());
        TaskModel task = taskService.selectById(idTask);

        int countStepUpdate = 0;

        if (task.getState().equals("not complete")) {
            taskService.updateDateComplete(idTask, new Date(System.currentTimeMillis()));
            Image image = new Image(COMPLETE);
            imgState.setImage(image);
            countStepUpdate += 1;
        }
        if (task.getState().equals("complete")) {
            if (countStepUpdate == 0) {
                taskService.updateUnCompleted(idTask);
                Image image = new Image(UNCOMPLETED);
                imgState.setImage(image);
            }
        }
    }

    @FXML
    void handleBtnRemoveClicked(ActionEvent event) {
        TaskService taskService = new TaskService();
        long idTask = Long.parseLong(this.idTask.getText());
        taskService.delete(taskService.selectById(idTask));
    }

    public void setData (TaskModel task) {
        TaskService taskService = new TaskService();

        TaskModel taskTemp = taskService.selectById(task.getId());
        idTask.setText(taskTemp.getId().toString());
        btnTaskDescription.setText(taskTemp.getDescription());

        String urlImageState = "";
        if (taskTemp.getState().equals("complete")) {
            urlImageState = COMPLETE;
        }
        if (taskTemp.getState().equals("not complete")) {
            urlImageState = UNCOMPLETED;
        }
        Image image = new Image(urlImageState);
        imgState.setImage(image);

        StepService stepService = new StepService();
        Long numberStepComplete = stepService.getNumberStepCompleteOfTask(taskTemp);
        Long numberOfStep = stepService.getNumberStepOfTask(taskTemp);

        String numberStepDisplay = numberStepComplete + "/" + numberOfStep;
        this.numberStep.setText(numberStepDisplay);
    }
}
