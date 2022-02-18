package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "todolist")
public class ToDoListModel {
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
    private UserModel user;

    public ToDoListModel(String description, Date dateCreate, UserModel user) {
        this.description = description;
        this.dateCreate = dateCreate;
        this.user = user;
    }

    public ToDoListModel(String description, Date dateCreate) {
        this.description = description;
        this.dateCreate = dateCreate;
    }

    public ToDoListModel() {
    }

    public void setId(Long id) {
        this.id = id;
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

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ToDoListModel{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", dateCreate=" + dateCreate +
                ", dateComplete=" + dateComplete +
                ", state='" + state + '\'' +
                ", user=" + user +
                '}';
    }
}
