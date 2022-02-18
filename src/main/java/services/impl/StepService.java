package services.impl;

import dao.impl.BaseDAO;
import dao.impl.StepDAO;
import models.StepModel;
import models.TaskModel;
import services.IStepService;

import java.sql.Date;
import java.util.List;

public class StepService extends BaseDAO implements IStepService {
    private final StepDAO stepDAO;

    public StepService() {
        stepDAO = new StepDAO();
    }

    @Override
    public StepModel insert(StepModel step) {
        return stepDAO.insert(step);
    }

    @Override
    public StepModel updateDescription(long id, String description) {
        return stepDAO.updateDescription(id, description);
    }

    @Override
    public StepModel updateDateComplete(long id, Date dateComplete) {
        return stepDAO.updateDateComplete(id, dateComplete);
    }

    @Override
    public StepModel updateUnCompleted(long id) {
        return stepDAO.updateUnCompleted(id);
    }

    @Override
    public void delete(StepModel step) {
        stepDAO.delete(step);
    }

    @Override
    public StepModel selectById(long id) {
        return stepDAO.selectById(id);
    }

    @Override
    public StepModel selectByDescription(String description) {
        return stepDAO.selectByDescription(description);
    }

    @Override
    public List<StepModel> selectByTask(TaskModel task) {
        return stepDAO.selectByTask(task);
    }

    @Override
    public List<StepModel> getAll() {
        return stepDAO.getAll();
    }

    @Override
    public void addTask(Long id, TaskModel task) {
        stepDAO.addTask(id, task);
    }

    @Override
    public Long getNumberStepOfTask(TaskModel task) {
        return stepDAO.getNumberStepOfTask(task);
    }

    @Override
    public Long getNumberStepCompleteOfTask(TaskModel task) {
        return stepDAO.getNumberStepCompleteOfTask(task);
    }

    @Override
    public Long getNumberStepUnCompletedOfTask(TaskModel task) {
        return stepDAO.getNumberStepUnCompletedOfTask(task);
    }

    @Override
    public List<StepModel> selectStepCompleteByTask(TaskModel task) {
        return stepDAO.selectStepCompleteByTask(task);
    }

    @Override
    public List<StepModel> selectStepUnCompletedByTask(TaskModel task) {
        return stepDAO.selectStepCompleteByTask(task);
    }
}
