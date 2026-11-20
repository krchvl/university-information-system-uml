package university;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {

    public enum State {
        PLANNED, ACTIVE, COMPLETED
    }

    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<ProjectAssignment> assignments = new ArrayList<>();
    private State state = State.PLANNED;

    public Project(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public State getState() {
        return state;
    }

    public void start() {
        if (state != State.PLANNED) {
            throw new IllegalStateException("проект уже запущен или завершён");
        }
        state = State.ACTIVE;
    }

    public void finish() {
        if (state != State.ACTIVE) {
            throw new IllegalStateException("завершить можно только активный проект");
        }
        state = State.COMPLETED;
    }

    public ProjectAssignment addParticipant(ResearchEmployee employee, int hours) {
        if (hours <= 0) {
            throw new IllegalArgumentException("количество часов должно быть положительным");
        }
        if (state == State.COMPLETED) {
            throw new IllegalStateException("проект завершён");
        }
        for (ProjectAssignment assignment : assignments) {
            if (assignment.getEmployee() == employee) {
                assignment.setHours(hours);
                return assignment;
            }
        }
        ProjectAssignment assignment = new ProjectAssignment(this, employee, hours);
        assignments.add(assignment);
        return assignment;
    }

    public int getTotalHours() {
        int total = 0;
        for (ProjectAssignment assignment : assignments) {
            total += assignment.getHours();
        }
        return total;
    }

    public List<ProjectAssignment> getAssignments() {
        return assignments;
    }

    @Override
    public String toString() {
        return name + " (" + startDate + " - " + endDate + "), " + state;
    }
}
