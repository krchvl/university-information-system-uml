package university;

import java.util.ArrayList;
import java.util.List;

public class Lecturer extends ResearchEmployee {

    private List<Course> courses = new ArrayList<>();

    public Lecturer(String socialSecurityNumber, String name, String email, String researchArea) {
        super(socialSecurityNumber, name, email, researchArea);
    }

    @Override
    public String getRole() {
        return "Преподаватель";
    }

    public void teachCourse(Course course) {
        if (!courses.contains(course)) {
            courses.add(course);
            course.setLecturer(this);
        }
    }

    public int getWeeklyTeachingLoad() {
        int load = 0;
        for (Course course : courses) {
            load += course.getWeeklyDuration();
        }
        return load;
    }

    public List<Course> getCourses() {
        return courses;
    }
}
