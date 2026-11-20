package university;

public class Course {

    private int id;
    private String name;
    private int weeklyDuration;
    private Lecturer lecturer;

    public Course(int id, String name, int weeklyDuration) {
        this.id = id;
        this.name = name;
        this.weeklyDuration = weeklyDuration;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getWeeklyDuration() {
        return weeklyDuration;
    }

    public Lecturer getLecturer() {
        return lecturer;
    }

    void setLecturer(Lecturer lecturer) {
        this.lecturer = lecturer;
    }

    @Override
    public String toString() {
        return id + " " + name + ", " + weeklyDuration + " ч/нед";
    }
}
