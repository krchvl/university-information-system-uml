package university;

public class Dean extends Employee {

    private Faculty faculty;

    public Dean(String socialSecurityNumber, String name, String email) {
        super(socialSecurityNumber, name, email);
    }

    @Override
    public String getRole() {
        return "Декан";
    }

    public Faculty getFaculty() {
        return faculty;
    }

    void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
}
