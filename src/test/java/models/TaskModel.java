package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "task")
public class TaskModel {
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
    private ToDoListModel toDoList;

    public TaskModel(String description, Date dateCreate, ToDoListModel toDoList) {
        this.description = description;
        this.dateCreate = dateCreate;
        this.toDoList = toDoList;
    }

    public TaskModel(String description, Date dateCreate) {
        this.description = description;
        this.dateCreate = dateCreate;
    }

    public TaskModel() {
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

    public ToDoListModel getToDoList() {
        return toDoList;
    }

    public void setToDoList(ToDoListModel toDoList) {
        this.toDoList = toDoList;
    }

    @Override
    public String toString() {
        return "TaskModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateComplete=" + dateComplete +
                ", state='" + state + '\'' +
                ", toDoList=" + toDoList +
                '}';
    }
}
