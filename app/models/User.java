package models;

import play.db.ebean.Model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="AfrikaUser")
public class User extends Model{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String passwordHashedSalted;

    @OneToMany(cascade=CascadeType.ALL)
    private List<Project> projects;

    //@OneToMany(cascade=CascadeType.ALL)
    //private List<Donation> donations;



    public void addProject(Project project){
        projects.add(project);
    }



    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
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

    public static Finder<Long, User> find = new Finder<Long, User>(
            Long.class, User.class
    );
}
