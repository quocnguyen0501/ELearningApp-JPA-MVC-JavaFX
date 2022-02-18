package services.impl;

import dao.impl.BaseDAO;
import dao.impl.TaskDAO;
import models.TaskModel;
import models.ToDoListModel;
import models.UserModel;
import services.ITaskService;

import java.sql.Date;
import java.util.List;

public class TaskService extends BaseDAO implements ITaskService {
    private final TaskDAO taskDAO;

    public TaskService() {
        taskDAO = new TaskDAO();
    }

    @Override
    public TaskModel insert(TaskModel task) {
        return taskDAO.insert(task);
    }

    @Override
    public TaskModel updateDescription(long id, String description) {
        return taskDAO.updateDescription(id, description);
    }

    @Override
    public TaskModel updateDateComplete(long id, Date dateComplete) {
        return taskDAO.updateDateComplete(id, dateComplete);
    }

    @Override
    public void updateUnCompleted(long id) {
        taskDAO.updateUnCompleted(id);
    }

    @Override
    public void delete(TaskModel task) {
        taskDAO.delete(task);
    }

    @Override
    public TaskModel selectById(long id) {
        return taskDAO.selectById(id);
    }

    @Override
    public TaskModel selectByDescription(String description) {
        return taskDAO.selectByDescription(description);
    }

    @Override
    public List<TaskModel> selectByToDoListAndStateComplete(ToDoListModel toDoList) {
        return taskDAO.selectByToDoListAndStateComplete(toDoList);
    }

    @Override
    public List<TaskModel> selectByToDoListAndStateUncompleted(ToDoListModel toDoList) {
        return taskDAO.selectByToDoListAndStateUncompleted(toDoList);
    }

    @Override
    public List<TaskModel> selectByUser(UserModel user) {
        return taskDAO.selectByUser(user);
    }

    @Override
    public List<TaskModel> selectTaskCompleteByUser(UserModel user) {
        return taskDAO.selectTaskCompleteByUser(user);
    }

    @Override
    public List<TaskModel> selectTaskUnCompletedByUser(UserModel user) {
        return taskDAO.selectTaskUnCompletedByUser(user);
    }

    @Override
    public List<TaskModel> selectByToDoList(ToDoListModel toDoList) {
        return taskDAO.selectByToDoList(toDoList);
    }

    @Override
    public List<TaskModel> getAll() {
        return taskDAO.getAll();
    }

    @Override
    public void addToDoList(Long id, ToDoListModel toDoList) {
        taskDAO.addToDoList(id, toDoList);
    }

    @Override
    public long getNumberTaskOfToDoListUnCompleted(Long id) {
        return taskDAO.getNumberTaskOfToDoListUnCompleted(id);
    }

    @Override
    public long getNumberTaskOfToDoListUnCompletedByAll(List<Long> listIdToDoList) {
        long sum = 0;
        for (Long e : listIdToDoList) {
            long num = getNumberTaskOfToDoListUnCompleted(e);
            sum += num;
        }

        return sum;
    }

    @Override
    public Long getNumberSelectById(Long id) {
        return taskDAO.getNumberSelectById(id);
    }
}
