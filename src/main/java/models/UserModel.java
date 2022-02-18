package models;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "user")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 250, nullable = false)
    private String firstName;

    @Column(name = "sur_name", length = 250, nullable = false)
    private String surname;

    @Column(name = "username", length = 250, nullable = false)
    private String username;

    @Column(name = "password", length = 250, nullable = false)
    private String password;

    @Column(name = "date_of_birth", length = 250, nullable = false)
    private Date dateOfBirth;

    @Column(name = "gender", length = 7, nullable = false)
    private String gender;

    @Column(name = "avatar", length = 500)
    private String avatar;

    public UserModel() {
    }

    public UserModel(String firstName, String surname, String username, String password, Date dateOfBirth, String gender, String avatar) {
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.avatar = avatar;
    }

    public UserModel(String firstName, String surname, String username, String password, Date dateOfBirth, String gender) {
        this.firstName = firstName;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    public Long getUser_id() {
        return id;
    }

    public void setUser_id(Long user_id) {
        this.id = user_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", gender='" + gender + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }

    public boolean isEmpty () {
        return this.username.equals("") || this.password.equals("") ||
                this.firstName.equals("") || this.surname.equals("") ||
                this.dateOfBirth == null || this.gender.equals("");
    }
}
