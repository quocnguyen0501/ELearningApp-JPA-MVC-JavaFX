package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "step")
public class StepModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", length = 300, nullable = false)
    private String description;

    @Column (name = "date_create", nullable = false)
    private Date dateCreate;

    @Column (name = "date_complete")
    private Date dateComplete;

    @Column (name = "state", length = 14, nullable = false)
    private String state = "not complete";

    @ManyToOne
    private TaskModel task;

    public StepModel(String description, Date dateCreate, TaskModel task) {
        this.description = description;
        this.dateCreate = dateCreate;
        this.task = task;
    }

    public StepModel(String description, Date dateCreate) {
        this.description = description;
        this.dateCreate = dateCreate;
    }

    public StepModel() {
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Date getDateComplete() {
        return dateComplete;
    }

    public void setDateComplete(Date dateComplete) {
        this.dateComplete = dateComplete;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public TaskModel getTask() {
        return task;
    }

    public void setTask(TaskModel task) {
        this.task = task;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateComplete=" + dateComplete +
                ", state='" + state + '\'' +
                ", task=" + task +
                '}';
    }
}
