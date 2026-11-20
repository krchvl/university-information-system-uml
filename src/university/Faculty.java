package university;

import java.util.ArrayList;
import java.util.List;

public class Faculty {

    private String name;
    private Dean dean;
    private University university;
    private List<Institute> institutes = new ArrayList<>();

    public Faculty(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Dean getDean() {
        return dean;
    }

    public void setDean(Dean dean) {
        if (this.dean != null) {
            this.dean.setFaculty(null);
        }
        this.dean = dean;
        if (dean != null) {
            dean.setFaculty(this);
        }
    }

    public University getUniversity() {
        return university;
    }

    void setUniversity(University university) {
        this.university = university;
    }

    public void addInstitute(Institute institute) {
        if (!institutes.contains(institute)) {
            institutes.add(institute);
            institute.setFaculty(this);
        }
    }

    public List<Institute> getInstitutes() {
        return institutes;
    }

    @Override
    public String toString() {
        return name;
    }
}
