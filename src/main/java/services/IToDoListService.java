package services;

import models.ToDoListModel;
import models.UserModel;

import java.sql.Date;
import java.util.List;

public interface IToDoListService {
    ToDoListModel insert (ToDoListModel toDoList);

    ToDoListModel updateDescription (long id, String description);
    ToDoListModel updateDateComplete (long id, Date dateComplete);

    void delete(ToDoListModel toDoList);

    ToDoListModel selectById (long id);
    ToDoListModel selectByDescription (String description);
    List<ToDoListModel> selectByUser (UserModel user);
    List<ToDoListModel> getAll ();
    ToDoListModel selectByUserAndDescription (UserModel user, String description);
    ToDoListModel selectByUserAndDescriptionAndState (UserModel user, String description, String state);

    List<Long> selectIdTodoList (UserModel user);

    boolean isToDoListDescriptionExist (String description);

    void addUser (Long id, UserModel user);

    Long getNumberSelectById (Long id);

    Long getIdToDoListByUserAndDescription (UserModel user, String description);
    Long getNumberSelectByUser (UserModel user);
}
