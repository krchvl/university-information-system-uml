package university;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String name;
    private List<Faculty> faculties = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();

    public University(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addFaculty(Faculty faculty) {
        if (!faculties.contains(faculty)) {
            faculties.add(faculty);
            faculty.setUniversity(this);
        }
    }

    public void hire(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        }
    }

    public void appointDean(Faculty faculty, Dean dean) {
        hire(dean);
        faculty.setDean(dean);
    }

    public Employee findEmployee(String socialSecurityNumber) {
        for (Employee employee : employees) {
            if (employee.getSocialSecurityNumber().equals(socialSecurityNumber)) {
                return employee;
            }
        }
        return null;
    }

    public Project findProject(String name) {
        for (Faculty faculty : faculties) {
            for (Institute institute : faculty.getInstitutes()) {
                for (ResearchEmployee employee : institute.getMembers()) {
                    for (ProjectAssignment assignment : employee.getAssignments()) {
                        if (assignment.getProject().getName().equalsIgnoreCase(name)) {
                            return assignment.getProject();
                        }
                    }
                }
            }
        }
        return null;
    }

    public int getEmployeeCount() {
        return employees.size();
    }

    public int getTotalProjectHours() {
        int total = 0;
        for (Employee employee : employees) {
            if (employee instanceof ResearchEmployee) {
                total += ((ResearchEmployee) employee).getTotalProjectHours();
            }
        }
        return total;
    }

    public List<Lecturer> getLecturers() {
        List<Lecturer> lecturers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee instanceof Lecturer) {
                lecturers.add((Lecturer) employee);
            }
        }
        return lecturers;
    }

    public List<Faculty> getFaculties() {
        return faculties;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return name;
    }
}
