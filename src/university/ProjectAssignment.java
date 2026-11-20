package university;

public class ProjectAssignment {

    private Project project;
    private ResearchEmployee employee;
    private int hours;

    ProjectAssignment(Project project, ResearchEmployee employee, int hours) {
        this.project = project;
        this.employee = employee;
        this.hours = hours;
    }

    public Project getProject() {
        return project;
    }

    public ResearchEmployee getEmployee() {
        return employee;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("количество часов должно быть положительным");
        }
        this.hours = hours;
    }

    @Override
    public String toString() {
        return employee.getName() + " — " + project.getName() + ", " + hours + " ч";
    }
}
