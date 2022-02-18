package dao.impl;

import dao.IStepDAO;
import models.StepModel;
import models.TaskModel;

import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class StepDAO extends BaseDAO implements IStepDAO {
    @Override
    public StepModel insert(StepModel step) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.persist(step);
        entityTransaction.commit();

        return step;
    }

    @Override
    public StepModel updateDescription(long id, String description) {
        StepModel stepToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        stepToUpdate.setDescription(description);

        entityTransaction.commit();
        return stepToUpdate;
    }

    @Override
    public StepModel updateDateComplete(long id, Date dateComplete) {
        StepModel stepToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        stepToUpdate.setDateComplete(dateComplete);
        stepToUpdate.setState("complete");

        entityTransaction.commit();
        return stepToUpdate;
    }

    @Override
    public StepModel updateUnCompleted(long id) {
        StepModel stepToUpdate = selectById(id);

        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        stepToUpdate.setDateComplete(null);
        stepToUpdate.setState("not complete");

        entityTransaction.commit();
        return stepToUpdate;
    }

    @Override
    public void delete(StepModel step) {
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();
        entityManager.remove(step);
        entityTransaction.commit();
    }

    @Override
    public StepModel selectById(long id) {
        return entityManager.find(StepModel.class, id);
    }

    @Override
    public StepModel selectByDescription(String description) {
        Query query = entityManager.createQuery("SELECT s FROM StepModel s WHERE s.description = '" + description + "'");
        return !query.getResultList().isEmpty() ? (StepModel) query.getSingleResult() : null;
    }

    @Override
    public List<StepModel> selectByTask(TaskModel task) {
        Query query = entityManager.createQuery("SELECT s FROM StepModel s WHERE s.task.id = " + task.getId());
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public List<StepModel> getAll() {
        Query query = entityManager.createQuery("SELECT s FROM StepModel s");
        return !query.getResultList().isEmpty() ? query.getResultList() : null;
    }

    @Override
    public void addTask(Long id, TaskModel task) {
        StepModel step = selectById(id);
        if (task != null) {
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            step.setTask(task);
            entityTransaction.commit();
        }
    }

    @Override
    public Long getNumberStepOfTask(TaskModel task) {
        Query query = entityManager.createQuery("SELECT COUNT (s) FROM StepModel s WHERE s.task.id = " + task.getId());
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long getNumberStepCompleteOfTask(TaskModel task) {
        Query query = entityManager.createQuery("SELECT COUNT (s) FROM StepModel s WHERE s.task.id = " + task.getId() + " AND s.state = 'complete'");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public Long getNumberStepUnCompletedOfTask(TaskModel task) {
        Query query = entityManager.createQuery("SELECT COUNT (s) FROM StepModel s WHERE s.task.id = " + task.getId() + " AND s.state = 'not complete'");
        return !query.getResultList().isEmpty() ? (Long) query.getSingleResult() : null;
    }

    @Override
    public List<StepModel> selectStepCompleteByTask(TaskModel task) {
        Query query = entityManager.createQuery("SELECT s FROM StepModel s WHERE s.task.id = " + task.getId() + " AND s.state = 'complete'");
        return !query.getResultList().isEmpty() ?  query.getResultList(): null;
    }

    @Override
    public List<StepModel> selectStepUnCompletedByTask(TaskModel task) {
        Query query = entityManager.createQuery("SELECT s FROM StepModel s WHERE s.task.id = " + task.getId() + " AND s.state = 'not complete'");
        return !query.getResultList().isEmpty() ?  query.getResultList(): null;
    }
}
