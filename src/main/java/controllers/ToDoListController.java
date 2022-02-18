package controllers;

import animations.Shaker;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import models.*;
import services.impl.StepService;
import services.impl.TaskService;
import services.impl.ToDoListService;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static helper.StaticValue.*;
import static helper.UrlViewsConst.*;

public class ToDoListController implements Initializable {
    @FXML
    private TextField addListField;

    @FXML
    private Button btnAddList;

    @FXML
    private JFXButton btnImportant;

    @FXML
    private JFXButton btnMyDay;

    @FXML
    private JFXButton btnTask;

    @FXML
    private Button btnAddStep;

    @FXML
    private Label numberOfTask;

    @FXML
    private Label numberTaskOfImportant;

    @FXML
    private Label numberTaskOfMyDay;

    @FXML
    private VBox viewListContainer;

    @FXML
    private VBox viewTaskContainer;

    @FXML
    private VBox viewStepContainer;

    @FXML
    private Button btnRefresh;

    @FXML
    private JFXButton btnOpenStep;

    @FXML
    private TextField stepField;

    @FXML
    private TextField taskField;

    private final ToDoListService toDoListService = new ToDoListService();
    private final TaskService taskService = new TaskService();
    private final StepService stepService = new StepService();
    private final UserModel user = UserLogin.getINSTANCE().getUserModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String description = new Date(System.currentTimeMillis()).toString();
        String state = "default";
        if (toDoListService.selectByUserAndDescriptionAndState(user, description, state) == null) {
            ToDoListModel toDoList = new ToDoListModel(description, new Date(System.currentTimeMillis()), user);
            toDoList.setState(state);
            toDoListService.insert(toDoList);
        }

        String myDay = new Date(System.currentTimeMillis()).toString();
        Long idToDoListOfMyDay = toDoListService.getIdToDoListByUserAndDescription(user, myDay);
        if (idToDoListOfMyDay != null) {
            long num = taskService.getNumberTaskOfToDoListUnCompleted(idToDoListOfMyDay);
            this.numberTaskOfMyDay.setText(Long.toString(num));
        }

        refreshAndUpdateLeftScreen();
        refreshAndUpdateMiddleScreen(description, state);
    }

    //==================== HANDLE ADD LIST ======================
    @FXML
    void handleBtnAddList(ActionEvent event) {
        String description = this.addListField.getText();
        if (!description.equals("")) {
            if (toDoListService.selectByUserAndDescriptionAndState(user, description, "user set") == null) {
                ToDoListModel toDoList = new ToDoListModel(description, new Date(System.currentTimeMillis()), user);
                toDoList.setState("user set");
                toDoListService.insert(toDoList);
                this.addListField.setText("");
                refreshAndUpdateLeftScreen();
            } else {
                Shaker shaker = new Shaker(this.addListField);
                shaker.shake();
            }
        } else {
            Shaker shaker = new Shaker(this.addListField);
            shaker.shake();
        }
    }

    //==================== HANDLE ADD TASK ======================
    @FXML
    void handleBtnAddTask(ActionEvent event) {
        String descriptionTask = taskField.getText();

        if (!descriptionTask.equals("")) {
            ToDoListModel toDoList = toDoListService.selectById(ID_TODOLIST);
            TaskModel taskModel = new TaskModel(descriptionTask, new Date(System.currentTimeMillis()), toDoList);
            taskService.insert(taskModel);
            this.taskField.setText("");
            refreshScreen();
        } else {
            Shaker shaker = new Shaker(this.addListField);
            shaker.shake();
        }
    }

    //==================== HANDLE ADD STEP ======================
    @FXML
    void handleBtnAddStep(ActionEvent event) {
        String descriptionStep = stepField.getText();

        if (!descriptionStep.equals("")) {
            TaskModel task = taskService.selectById(ID_TASK);
            StepModel step = new StepModel(descriptionStep, new Date(System.currentTimeMillis()), task);
            stepService.insert(step);
            this.stepField.setText("");
            refreshScreen();
        } else {
            Shaker shaker = new Shaker(this.addListField);
            shaker.shake();
        }
    }

    @FXML
    void handelBtnMyDayClicked(ActionEvent event) {
        String description = new Date(System.currentTimeMillis()).toString();
        String state = "default";

        refreshAndUpdateMiddleScreen(description, state);
    }

    @FXML
    void handleBtnImportantClick(ActionEvent event) {
        String description = "Important";
        String state = "important";

        refreshAndUpdateMiddleScreen(description, state);
    }

    //=================== ALL TASK ======================
    @FXML
    void handleBtnTaskClicked(ActionEvent event) {
        ID_TODOLIST = 0;
        refreshMiddleScreenForAllTask();
    }

    //============================= REFRESH ALL CLICKED ==============================
    @FXML
    void handleBtnRefreshClicked(ActionEvent event) {
        refreshAndUpdateLeftScreen();
        if (ID_TODOLIST == 0) {
            refreshMiddleScreenForAllTask();
        } else {
            if (toDoListService.getNumberSelectById(ID_TODOLIST) > 0) {
                ToDoListModel toDoList = toDoListService.selectById(ID_TODOLIST);
                refreshAndUpdateMiddleScreen(toDoList.getDescription(), toDoList.getState());
            }
        }
        if (taskService.getNumberSelectById(ID_TASK) > 0) {
            refreshAndUpdateRightScreen();
        }
    }

    @FXML
    void handleBtnOpenClicked(ActionEvent event) {
        if (toDoListService.getNumberSelectById(ID_TODOLIST) > 0) {
            ToDoListModel toDoList = toDoListService.selectById(ID_TODOLIST);
            refreshAndUpdateMiddleScreen(toDoList.getDescription(), toDoList.getState());
        }
    }

    @FXML
    void handleBtnOpenStepClicked(ActionEvent event) {
        if (taskService.getNumberSelectById(ID_TASK) > 0) {
            refreshAndUpdateRightScreen();
        }
    }

    //========================= LEFT SCREEN =========================
    public void refreshAndUpdateLeftScreen () {
        this.viewListContainer.getChildren().removeAll(viewListContainer.getChildren());

        this.setNumberOfMyDay();
        this.setNumberOfImportant();
        this.setNumberOfAllTask();

        List<ToDoListModel> list = new ArrayList<>(getToDoList());
        try {
            for (ToDoListModel toDoList : list) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(TODOlIST_SINGLE_VIEW));

                VBox vBox = fxmlLoader.load();
                ToDoListElementController toDoListElementController = fxmlLoader.getController();
                toDoListElementController.setData(toDoList);
                this.viewListContainer.getChildren().add(vBox);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //========================= MIDDLE SCREEN =========================
    private void refreshAndUpdateMiddleScreen (String description, String state) {
        this.viewTaskContainer.getChildren().removeAll(viewTaskContainer.getChildren());

        ToDoListModel toDoList = toDoListService.selectByUserAndDescriptionAndState(user, description, state);

        ID_TODOLIST = toDoList.getId();

        List<TaskModel> list = getTaskUnComplete(toDoList.getId());

        if (list != null) {
            try {
                for (TaskModel task : list) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(TASK_SINGLE_VIEW));

                    VBox vBox = fxmlLoader.load();
                    TaskElementController taskElementController = fxmlLoader.getController();
                    taskElementController.setData(task);
                    this.viewTaskContainer.getChildren().add(vBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(NOTI_NONE_TASK));

                VBox vBox = fxmlLoader.load();
                this.viewTaskContainer.getChildren().add(vBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //========================= RIGHT SCREEN =========================
    private void refreshAndUpdateRightScreen () {
        this.viewStepContainer.getChildren().removeAll(viewStepContainer.getChildren());

        List<StepModel> allStep = stepService.selectByTask(taskService.selectById(ID_TASK));
        if (allStep != null) {
            try {
                for (StepModel step : allStep) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(STEP_SINGLE_VIEW));

                    VBox vBox = fxmlLoader.load();
                    StepElementController stepElementController = fxmlLoader.getController();
                    stepElementController.setData(step);
                    this.viewStepContainer.getChildren().add(vBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(NOTI_NONE_STEP));

                VBox vBox = fxmlLoader.load();
                this.viewStepContainer.getChildren().add(vBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //===================================================================

    private void setNumberOfMyDay () {
        String myDay = new Date(System.currentTimeMillis()).toString();
        Long idToDoListOfMyDay = toDoListService.getIdToDoListByUserAndDescription(user, myDay);
        if (idToDoListOfMyDay != null) {
            long num = taskService.getNumberTaskOfToDoListUnCompleted(idToDoListOfMyDay);
            this.numberTaskOfMyDay.setText(Long.toString(num));
        }
    }

    private void setNumberOfImportant () {
        String description = "Important";
        Long idToDoListOfMyDay = toDoListService.getIdToDoListByUserAndDescription(user, description);
        if (idToDoListOfMyDay != null) {
            long num = taskService.getNumberTaskOfToDoListUnCompleted(idToDoListOfMyDay);
            this.numberTaskOfImportant.setText(Long.toString(num));
        }
    }

    private void setNumberOfAllTask () {
        List<Long> idToDoLists = toDoListService.selectIdTodoList(user);
        long number = taskService.getNumberTaskOfToDoListUnCompletedByAll(idToDoLists);
        this.numberOfTask.setText(Long.toString(number));
    }

    private List<ToDoListModel> getToDoList () {
        List<ToDoListModel> toDoLists = toDoListService.selectByUser(UserLogin.getINSTANCE().getUserModel());
        toDoLists.removeIf(e -> (e.getState().equals("default")) || (e.getState().equals("important")));
        return toDoLists;
    }

    private List<TaskModel> getTaskUnComplete (Long idToDoList) {
        return taskService.selectByToDoListAndStateUncompleted(toDoListService.selectById(idToDoList));
    }

    private void refreshMiddleScreenForAllTask () {
        this.viewTaskContainer.getChildren().removeAll(viewTaskContainer.getChildren());

        List<TaskModel> listTaskComplete = taskService.selectTaskCompleteByUser(user);
        List<TaskModel> listTaskUnCompleted = taskService.selectTaskUnCompletedByUser(user);

        if (listTaskUnCompleted != null) {
            try {
                for (TaskModel task : listTaskUnCompleted) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(TASK_SINGLE_VIEW));

                    VBox vBox = fxmlLoader.load();
                    TaskElementController taskElementController = fxmlLoader.getController();
                    taskElementController.setData(task);
                    this.viewTaskContainer.getChildren().add(vBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (listTaskComplete != null) {
            try {
                for (TaskModel task : listTaskComplete) {
                    FXMLLoader fxmlLoader = new FXMLLoader();
                    fxmlLoader.setLocation(getClass().getResource(TASK_SINGLE_VIEW));

                    VBox vBox = fxmlLoader.load();
                    TaskElementController taskElementController = fxmlLoader.getController();
                    taskElementController.setData(task);
                    this.viewTaskContainer.getChildren().add(vBox);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (listTaskUnCompleted == null && listTaskComplete == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource(NOTI_NONE_TASK));

                VBox vBox = fxmlLoader.load();
                this.viewTaskContainer.getChildren().add(vBox);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void refreshScreen () {
        refreshAndUpdateLeftScreen();
        if (toDoListService.getNumberSelectById(ID_TODOLIST) > 0) {
            ToDoListModel toDoList = toDoListService.selectById(ID_TODOLIST);
            refreshAndUpdateMiddleScreen(toDoList.getDescription(), toDoList.getState());
        }
        if (taskService.getNumberSelectById(ID_TASK) > 0) {
            refreshAndUpdateRightScreen();
        }
    }
}
