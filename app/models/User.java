package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AfrikaUser")
public class User extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHashedSalted;
    @OneToMany(mappedBy = "owner")
    private List<Project> myProjects;
    @OneToMany(mappedBy = "user")
    private List<Donation> donations;

    public List<Donation> getDonations() {
        return donations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }
    public void addProject(Project project){
        myProjects.add(project);
    }

    public List<Project> getMyProjects() {
        return myProjects;
    }

    public void setMyProjects(List<Project> myProjects) {
        this.myProjects = myProjects;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getPasswordHashedSalted() {
        return passwordHashedSalted;
    }

    public void setPasswordHashedSalted(String passwordHashedSalted) {
        this.passwordHashedSalted = passwordHashedSalted;
    }

    public static Finder<Long, User> find = new Finder<>(
            Long.class, User.class
    );
}
