package university;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        University university = createUniversity();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println("1 - структура университета");
            System.out.println("2 - сотрудники");
            System.out.println("3 - проекты");
            System.out.println("4 - учебная нагрузка");
            System.out.println("5 - назначить часы на проекте");
            System.out.println("0 - выход");
            System.out.print("> ");

            String command = scanner.nextLine().trim();

            if (command.equals("0")) {
                break;
            } else if (command.equals("1")) {
                printStructure(university);
            } else if (command.equals("2")) {
                printEmployees(university);
            } else if (command.equals("3")) {
                printProjects(university);
            } else if (command.equals("4")) {
                printTeachingLoad(university);
            } else if (command.equals("5")) {
                assignHours(university, scanner);
            } else {
                System.out.println("неизвестная команда");
            }
        }
    }

    private static University createUniversity() {
        University university = new University("ВГУ");

        Faculty fkn = new Faculty("Факультет компьютерных наук");
        Faculty pm = new Faculty("Факультет прикладной математики");
        university.addFaculty(fkn);
        university.addFaculty(pm);

        Institute iit = new Institute("Кафедра информационных технологий", "Воронеж, Университетская пл., 1");
        Institute sp = new Institute("Кафедра системного программирования", "Воронеж, пр. Революции, 24");
        Institute mm = new Institute("Кафедра математического моделирования", "Воронеж, Университетская пл., 1");
        fkn.addInstitute(iit);
        fkn.addInstitute(sp);
        pm.addInstitute(mm);

        Dean ivanov = new Dean("100-000-001", "Иванов Иван Иванович", "dean@cs.vsu.ru");
        university.appointDean(fkn, ivanov);

        Lecturer petrov = new Lecturer("100-000-002", "Петров Пётр Петрович", "petrov@cs.vsu.ru",
                "Компиляторы и анализ программ");
        Lecturer sidorova = new Lecturer("100-000-003", "Сидорова Анна Сергеевна", "sidorova@cs.vsu.ru",
                "Машинное обучение");
        ResearchEmployee kuznetsov = new ResearchEmployee("100-000-004", "Кузнецов Олег Игоревич",
                "kuznetsov@cs.vsu.ru", "Распределённые вычисления");
        AdministrativeEmployee smirnova = new AdministrativeEmployee("100-000-005", "Смирнова Ольга Павловна",
                "smirnova@cs.vsu.ru", "специалист деканата");

        university.hire(petrov);
        university.hire(sidorova);
        university.hire(kuznetsov);
        university.hire(smirnova);

        iit.addMember(petrov);
        sp.addMember(sidorova);
        sp.addMember(kuznetsov);

        Project uml = new Project("UML-моделирование", LocalDate.of(2026, 2, 1), LocalDate.of(2026, 6, 30));
        Project ai = new Project("Нейросетевые методы", LocalDate.of(2026, 3, 1), LocalDate.of(2026, 12, 15));
        petrov.joinProject(uml, 120);
        kuznetsov.joinProject(uml, 80);
        sidorova.joinProject(ai, 200);
        uml.start();

        Course oop = new Course(101, "Объектно-ориентированное программирование", 6);
        Course db = new Course(102, "Базы данных", 4);
        Course ml = new Course(201, "Машинное обучение", 6);
        petrov.teachCourse(oop);
        petrov.teachCourse(db);
        sidorova.teachCourse(ml);

        return university;
    }

    private static void printStructure(University university) {
        for (Faculty faculty : university.getFaculties()) {
            Dean dean = faculty.getDean();
            System.out.println(faculty + ", декан: " + (dean == null ? "не назначен" : dean.getName()));
            for (Institute institute : faculty.getInstitutes()) {
                System.out.println("  " + institute + ", сотрудников: " + institute.getMembers().size());
            }
        }
    }

    private static void printEmployees(University university) {
        System.out.println("всего сотрудников: " + university.getEmployeeCount());
        for (Employee employee : university.getEmployees()) {
            System.out.println("  " + employee);
        }
    }

    private static void printProjects(University university) {
        for (Employee employee : university.getEmployees()) {
            if (employee instanceof ResearchEmployee) {
                ResearchEmployee researcher = (ResearchEmployee) employee;
                for (ProjectAssignment assignment : researcher.getAssignments()) {
                    System.out.println("  " + assignment);
                }
            }
        }
        System.out.println("  итого часов: " + university.getTotalProjectHours());
    }

    private static void printTeachingLoad(University university) {
        for (Lecturer lecturer : university.getLecturers()) {
            System.out.println("  " + lecturer.getName() + ", " + lecturer.getWeeklyTeachingLoad() + " ч/нед");
            for (Course course : lecturer.getCourses()) {
                System.out.println("    " + course);
            }
        }
    }

    private static void assignHours(University university, Scanner scanner) {
        System.out.print("социальный код: ");
        String ssn = scanner.nextLine().trim();
        System.out.print("проект: ");
        String projectName = scanner.nextLine().trim();
        System.out.print("часы: ");
        String hoursText = scanner.nextLine().trim();

        Employee employee = university.findEmployee(ssn);
        Project project = university.findProject(projectName);

        if (!(employee instanceof ResearchEmployee)) {
            System.out.println("сотрудник не найден");
            return;
        }
        if (project == null) {
            System.out.println("проект не найден");
            return;
        }

        int hours;
        try {
            hours = Integer.parseInt(hoursText);
        } catch (NumberFormatException e) {
            System.out.println("часы должны быть целым числом");
            return;
        }

        try {
            ((ResearchEmployee) employee).joinProject(project, hours);
            System.out.println("готово: " + employee.getName() + ", проект " + project.getName() + ", " + hours + " ч");
        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println(e.getMessage());
        }
    }
}
