package viewmodels;

import models.Project;
import services.ProjectService;

public class ProjectWidget {
    private int state;
    private long id;
    private String title;
    private String description;
    private String imageLink;

    public ProjectWidget(models.Project project) {
        this.title = project.getTitle();
        this.id = project.getId();
        this.description = project.getDescription();
        this.imageLink = project.getImageURL();
        this.state = ProjectService.getStateOfProjectInPercent(project);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
