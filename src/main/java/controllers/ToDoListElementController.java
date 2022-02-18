package controllers;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import models.ToDoListModel;
import services.impl.TaskService;
import services.impl.ToDoListService;

import static helper.StaticValue.*;

public class ToDoListElementController{

    @FXML
    private Button btnDelete;

    @FXML
    private JFXButton btnDescription;

    @FXML
    private Button btnRename;

    @FXML
    private Label idToDoList;

    @FXML
    private Label numberOfTuDoList;

    @FXML
    void handleBtnDeleteClicked(ActionEvent event) {
        ToDoListService toDoListService = new ToDoListService();
        long idList = Long.parseLong(idToDoList.getText());
        toDoListService.delete(toDoListService.selectById(idList));
    }

    @FXML
    void handleBtnDescriptionClick(ActionEvent event) {
        ID_TODOLIST = Long.parseLong(idToDoList.getText());
    }

    public void setData (ToDoListModel toDoList) {
        TaskService taskService = new TaskService();
        long num = taskService.getNumberTaskOfToDoListUnCompleted(toDoList.getId());
        numberOfTuDoList.setText(Long.toString(num));
        btnDescription.setText(toDoList.getDescription());
        idToDoList.setText(toDoList.getId().toString());
    }
}
