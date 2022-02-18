package dao.impl;

import dao.ITaskDAO;
import models.StepModel;
import models.TaskModel;
import models.ToDoListModel;
import models.UserModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class TaskDAO extends BaseDAO implements ITaskDAO {

    @Override
    public TaskModel insert(TaskModel task) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(task);
        entityTransaction.commit();

        return task;
    }

    @Override
    public TaskModel updateDescription(long id, String description) {
        TaskModel taskToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        taskToUpdate.setDescription(description);

        entityTransaction.commit();
        return taskToUpdate;
    }

    @Override
    public TaskModel updateDateComplete(long id, Date dateComplete) {
        TaskModel taskToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        taskToUpdate.setDateComplete(dateComplete);
        taskToUpdate.setState("complete");

        entityTransaction.commit();
        return taskToUpdate;
    }

    @Override
    public TaskModel updateUnCompleted(long id) {
        TaskModel taskToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        taskToUpdate.setDateComplete(null);
        taskToUpdate.setState("not complete");

        entityTransaction.commit();
        return taskToUpdate;
    }

    @Override
    public void delete(TaskModel task) {
        StepDAO stepDAO = new StepDAO();
        List<StepModel> list = stepDAO.selectByTask(task);
        if (list != null) {
            for (StepModel s : list) {
                stepDAO.delete(s);
            }
        }
        stepDAO.close();

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(task);
        entityTransaction.commit();
    }

    @Override
    public TaskModel selectById(long id) {
        return entityManager.find(TaskModel.class, id);
    }

    @Override
    public TaskModel selectByDescription(String description) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t WHERE t.description = '" + description + "'");
        return !query.getResultList().isEmpty() ? (TaskModel) query.getSingleResult() : null;
    }

    @Override
    public List<TaskModel> selectByToDoList(ToDoListModel toDoList) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t WHERE t.toDoList.id = " + toDoList.getId());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<TaskModel> selectByToDoListAndStateComplete(ToDoListModel toDoList) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t " +
                                                    "WHERE t.toDoList.id = " + toDoList.getId() +
                                                    " AND t.state = 'complete'");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<TaskModel> selectByToDoListAndStateUncompleted(ToDoListModel toDoList) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t " +
                "WHERE t.toDoList.id = " + toDoList.getId() +
                " AND t.state = 'not complete'");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<TaskModel> selectByUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t WHERE t.toDoList.user.id = " + user.getUser_id());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<TaskModel> selectTaskCompleteByUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t WHERE t.toDoList.user.id = " + user.getUser_id()
                                                + " AND t.state = 'complete'");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<TaskModel> selectTaskUnCompletedByUser(UserModel user) {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t WHERE t.toDoList.user.id = " + user.getUser_id()
                + " AND t.state = 'not complete'");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<TaskModel> getAll() {
        Query query = entityManager.createQuery("SELECT t FROM TaskModel t");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public void addToDoList(Long id, ToDoListModel toDoList) {
        TaskModel task = selectById(id);
        if (task != null) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            task.setToDoList(toDoList);
            entityTransaction.commit();
        }
    }

    @Override
    public long getNumberTaskOfToDoListUnCompleted(Long id) {
        Query query = entityManager.createQuery("SELECT COUNT(t) FROM TaskModel t " +
                                                    "WHERE t.toDoList.id = " + id +
                                                    " AND t.state = 'not complete'");
        return (long) query.getSingleResult();
    }

    @Override
    public Long getNumberSelectById(Long id) {
        Query query = entityManager.createQuery("SELECT COUNT(t) FROM TaskModel t " +
                "WHERE t.id = " + id);
        return (long) query.getSingleResult();
    }
}
