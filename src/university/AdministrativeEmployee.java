package university;

public class AdministrativeEmployee extends Employee {

    private String position;

    public AdministrativeEmployee(String socialSecurityNumber, String name, String email, String position) {
        super(socialSecurityNumber, name, email);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    @Override
    public String getRole() {
        return "Административный сотрудник";
    }
}
