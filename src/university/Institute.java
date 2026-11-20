package university;

import java.util.ArrayList;
import java.util.List;

public class Institute {

    private String name;
    private String address;
    private Faculty faculty;
    private List<ResearchEmployee> members = new ArrayList<>();

    public Institute(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public void addMember(ResearchEmployee employee) {
        if (!members.contains(employee)) {
            members.add(employee);
            employee.attachToInstitute(this);
        }
    }

    public List<ResearchEmployee> getMembers() {
        return members;
    }

    @Override
    public String toString() {
        return name + ", " + address;
    }
}
