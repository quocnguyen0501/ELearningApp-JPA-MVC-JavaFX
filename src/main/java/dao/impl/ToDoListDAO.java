package dao.impl;

import dao.IToDoListDAO;
import models.TaskModel;
import models.ToDoListModel;
import models.UserModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class ToDoListDAO extends BaseDAO implements IToDoListDAO {
    @Override
    public ToDoListModel insert(ToDoListModel toDoList) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(toDoList);
        entityTransaction.commit();

        return toDoList;
    }

    @Override
    public ToDoListModel updateDescription(long id, String description) {
        ToDoListModel toDoListToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        toDoListToUpdate.setDescription(description);

        entityTransaction.commit();
        return toDoListToUpdate;
    }

    @Override
    public ToDoListModel updateDateComplete(long id, Date dateComplete) {
        ToDoListModel toDoListToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        toDoListToUpdate.setDateComplete(dateComplete);
        toDoListToUpdate.setState("complete");

        entityTransaction.commit();
        return toDoListToUpdate;
    }

    @Override
    public void delete(ToDoListModel toDoList) {
        TaskDAO taskDAO = new TaskDAO();
        List<TaskModel> list = taskDAO.selectByToDoList(toDoList);

        if (list != null) {
            for (TaskModel t : list) {
                taskDAO.delete(t);
            }
        }

        taskDAO.close();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(toDoList);
        entityTransaction.commit();
    }

    @Override
    public ToDoListModel selectById(long id) {
        return entityManager.find(ToDoListModel.class, id);
    }

    @Override
    public ToDoListModel selectByDescription(String description) {
        Query query = entityManager.createQuery("SELECT todolist FROM ToDoListModel todolist WHERE todolist.description = '" + description + "'");
        return !query.getResultList().isEmpty() ? (ToDoListModel) query.getSingleResult() : null;
    }

    @Override
    public List<ToDoListModel> selectByUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT todolist FROM ToDoListModel todolist WHERE todolist.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<ToDoListModel> getAll() {
        Query query = entityManager.createQuery("SELECT todolist FROM ToDoListModel todolist");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<Long> selectIdTodoList(UserModel user) {
        Query query = entityManager.createQuery("SELECT todolist.id FROM ToDoListModel todolist WHERE todolist.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public ToDoListModel selectByUserAndDescription(UserModel user, String description) {
        Query query = entityManager.createQuery("SELECT todolist FROM ToDoListModel todolist " +
                "WHERE todolist.user.id = " + user.getUser_id() + " " +
                "AND todolist.description = '" + description + "'");
        return !query.getResultList().isEmpty() ? (ToDoListModel) query.getSingleResult() : null;
    }

    @Override
    public ToDoListModel selectByUserAndDescriptionAndState(UserModel user, String description, String state) {
        Query query = entityManager.createQuery("SELECT todolist FROM ToDoListModel todolist " +
                "WHERE todolist.user.id = " + user.getUser_id() + " " +
                "AND todolist.description = '" + description +
                "' AND todolist.state = '" + state + "'");
        return !query.getResultList().isEmpty() ? (ToDoListModel) query.getSingleResult() : null;
    }

    @Override
    public void addUser(Long id, UserModel user) {
        ToDoListModel toDoList = selectById(id);
        if (toDoList != null) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            toDoList.setUser(user);
            entityTransaction.commit();
        }
    }

    @Override
    public Long getNumberSelectById(Long id) {
        Query query = entityManager.createQuery("SELECT COUNT(todolist) FROM ToDoListModel todolist WHERE todolist.id = '" + id + "'");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long getIdToDoListByUserAndDescription(UserModel user, String description) {
        Query query = entityManager.createQuery("SELECT todolist.id FROM ToDoListModel todolist " +
                "WHERE todolist.user.id = " + user.getUser_id() + " " +
                "AND todolist.description = '" + description + "'");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long getNumberSelectByUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT COUNT (todolist) FROM ToDoListModel todolist WHERE todolist.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }
}
