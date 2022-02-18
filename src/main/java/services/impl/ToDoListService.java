package services.impl;

import dao.impl.BaseDAO;
import dao.impl.ToDoListDAO;
import models.ToDoListModel;
import models.UserModel;
import services.IToDoListService;

import java.sql.Date;
import java.util.List;

public class ToDoListService extends BaseDAO implements IToDoListService {
    private final ToDoListDAO toDoListDAO;

    public ToDoListService() {
        this.toDoListDAO = new ToDoListDAO();
    }

    @Override
    public ToDoListModel insert(ToDoListModel toDoList) {
        return toDoListDAO.insert(toDoList);
    }

    @Override
    public ToDoListModel updateDescription(long id, String description) {
        return toDoListDAO.updateDescription(id, description);
    }

    @Override
    public ToDoListModel updateDateComplete(long id, Date dateComplete) {
        return toDoListDAO.updateDateComplete(id, dateComplete);
    }

    @Override
    public void delete(ToDoListModel toDoList) {
        toDoListDAO.delete(toDoList);
    }

    @Override
    public ToDoListModel selectById(long id) {
        return toDoListDAO.selectById(id);
    }

    @Override
    public ToDoListModel selectByDescription(String description) {
        return toDoListDAO.selectByDescription(description);
    }

    @Override
    public List<ToDoListModel> selectByUser(UserModel user) {
        return toDoListDAO.selectByUser(user);
    }

    @Override
    public List<ToDoListModel> getAll() {
        return toDoListDAO.getAll();
    }

    @Override
    public ToDoListModel selectByUserAndDescription(UserModel user, String description) {
        return toDoListDAO.selectByUserAndDescription(user, description);
    }

    @Override
    public ToDoListModel selectByUserAndDescriptionAndState(UserModel user, String description, String state) {
        return toDoListDAO.selectByUserAndDescriptionAndState(user, description, state);
    }

    @Override
    public List<Long> selectIdTodoList(UserModel user) {
        return toDoListDAO.selectIdTodoList(user);
    }

    @Override
    public boolean isToDoListDescriptionExist(String description) {
        return toDoListDAO.selectByDescription(description) != null;
    }

    @Override
    public void addUser(Long id, UserModel user) {
        toDoListDAO.addUser(id, user);
    }

    @Override
    public Long getNumberSelectById(Long id) {
        return toDoListDAO.getNumberSelectById(id);
    }

    @Override
    public Long getIdToDoListByUserAndDescription(UserModel user, String description) {
        return toDoListDAO.getIdToDoListByUserAndDescription(user, description);
    }

    @Override
    public Long getNumberSelectByUser(UserModel user) {
        return toDoListDAO.getNumberSelectByUser(user);
    }
}
