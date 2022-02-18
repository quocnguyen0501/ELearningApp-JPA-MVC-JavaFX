package dao;

import models.TaskModel;
import models.ToDoListModel;
import models.UserModel;

import java.sql.Date;
import java.util.List;

public interface ITaskDAO extends IBaseDAO{
    TaskModel insert (TaskModel task);

    TaskModel updateDescription (long id, String description);
    TaskModel updateDateComplete (long id, Date dateComplete);
    TaskModel updateUnCompleted (long id);

    void delete(TaskModel task);

    TaskModel selectById (long id);
    TaskModel selectByDescription (String description);
    List<TaskModel> selectByToDoList (ToDoListModel toDoList);

    List<TaskModel> selectByToDoListAndStateComplete (ToDoListModel toDoList);
    List<TaskModel> selectByToDoListAndStateUncompleted(ToDoListModel toDoList);
    List<TaskModel> selectByUser (UserModel user);
    List<TaskModel> selectTaskCompleteByUser (UserModel user);
    List<TaskModel> selectTaskUnCompletedByUser (UserModel user);

    List<TaskModel> getAll ();

    void addToDoList (Long id, ToDoListModel toDoList);

    long getNumberTaskOfToDoListUnCompleted (Long id);
    Long getNumberSelectById(Long id);
}
