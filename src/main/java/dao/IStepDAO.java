package dao;

import models.StepModel;
import models.TaskModel;

import java.sql.Date;
import java.util.List;

public interface IStepDAO extends IBaseDAO {
    StepModel insert (StepModel step);

    StepModel updateDescription (long id, String description);
    StepModel updateDateComplete (long id, Date dateComplete);
    StepModel updateUnCompleted (long id);

    void delete(StepModel step);

    StepModel selectById (long id);
    StepModel selectByDescription (String description);
    List<StepModel> selectByTask (TaskModel task);
    List<StepModel> getAll ();

    void addTask (Long id, TaskModel task);

    Long getNumberStepOfTask (TaskModel task);
    Long getNumberStepCompleteOfTask (TaskModel task);
    Long getNumberStepUnCompletedOfTask (TaskModel task);
    List<StepModel> selectStepCompleteByTask (TaskModel task);
    List<StepModel> selectStepUnCompletedByTask (TaskModel task);
}
