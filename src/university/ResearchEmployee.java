package university;

import java.util.ArrayList;
import java.util.List;

public class ResearchEmployee extends Employee {

    private String researchArea;
    private List<Institute> institutes = new ArrayList<>();
    private List<ProjectAssignment> assignments = new ArrayList<>();

    public ResearchEmployee(String socialSecurityNumber, String name, String email, String researchArea) {
        super(socialSecurityNumber, name, email);
        this.researchArea = researchArea;
    }

    public String getResearchArea() {
        return researchArea;
    }

    @Override
    public String getRole() {
        return "Научный сотрудник";
    }

    public void attachToInstitute(Institute institute) {
        if (!institutes.contains(institute)) {
            institutes.add(institute);
        }
    }

    public ProjectAssignment joinProject(Project project, int hours) {
        ProjectAssignment assignment = project.addParticipant(this, hours);
        if (!assignments.contains(assignment)) {
            assignments.add(assignment);
        }
        return assignment;
    }

    public int getHoursOn(Project project) {
        for (ProjectAssignment assignment : assignments) {
            if (assignment.getProject() == project) {
                return assignment.getHours();
            }
        }
        return 0;
    }

    public int getTotalProjectHours() {
        int total = 0;
        for (ProjectAssignment assignment : assignments) {
            total += assignment.getHours();
        }
        return total;
    }

    public List<Institute> getInstitutes() {
        return institutes;
    }

    public List<ProjectAssignment> getAssignments() {
        return assignments;
    }
}
