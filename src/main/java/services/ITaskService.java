package services;

import models.TaskModel;
import models.ToDoListModel;
import models.UserModel;

import java.sql.Date;
import java.util.List;

public interface ITaskService {
    TaskModel insert (TaskModel task);

    TaskModel updateDescription (long id, String description);
    TaskModel updateDateComplete (long id, Date dateComplete);
    void updateUnCompleted (long id);

    void delete(TaskModel task);

    TaskModel selectById (long id);
    TaskModel selectByDescription (String description);

    List<TaskModel> selectByToDoListAndStateComplete (ToDoListModel toDoList);
    List<TaskModel> selectByToDoListAndStateUncompleted(ToDoListModel toDoList);

    List<TaskModel> selectByUser (UserModel user);
    List<TaskModel> selectTaskCompleteByUser (UserModel user);
    List<TaskModel> selectTaskUnCompletedByUser (UserModel user);
    List<TaskModel> selectByToDoList (ToDoListModel toDoList);
    List<TaskModel> getAll ();

    void addToDoList (Long id, ToDoListModel toDoList);

    long getNumberTaskOfToDoListUnCompleted (Long id);
    long getNumberTaskOfToDoListUnCompletedByAll (List<Long> listIdToDoList);
    Long getNumberSelectById(Long id);
}
