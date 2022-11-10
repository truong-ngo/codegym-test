import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private Printer printer;
    private Input input;
    private StudentManager manager;
    private Validate validate;

    public Menu() {
        printer = new Printer();
        input = new Input();
        manager = new StudentManager();
        validate = new Validate();
    }

    public void display(Scanner scanner) {
        while (true) {
            int choice;
            printer.printMenu();
            String choiceStr = scanner.nextLine();
            if (validate.validateChoice(choiceStr, 0, 7)) {
                choice = Integer.parseInt(choiceStr);
                switch (choice) {
                    case 1:
                        manager.display(scanner);
                        break;
                    case 2:
                        Student student = input.inputStudent(scanner, printer, validate);
                        manager.add(student);
                        break;
                    case 3:
                        String id = input.inputStringData(scanner, printer, "ID");
                        if (manager.checkId(id)) {
                            Student newStudent = input.updateStudent(id, scanner, printer, validate);
                            manager.update(newStudent, id);
                        } else {
                            if (id.equals("")) {
                                break;
                            } else {
                                printer.invalidData();
                            }
                        }
                        break;
                    case 4:
                        while (true) {
                            String delId = input.inputStringData(scanner, printer, "ID");
                            if (manager.checkId(delId)) {
                                manager.delete(delId);
                                break;
                            } else {
                                if (delId.equals("")) {
                                    break;
                                } else {
                                    printer.invalidData();
                                }
                            }
                        }
                        break;
                    case 5:
                        subMenu(scanner);
                        break;
                    case 6:
                        manager.readFromFile();
                        break;
                    case 7:
                        manager.writeToFile();
                        break;
                    case 0:
                        System.exit(0);
                }
            } else {
                printer.invalidData();
            }
        }
    }

    public void subMenu(Scanner scanner) {
        boolean check = true;
        while (check) {
            int choice;
            printer.printSubMenu();
            String choiceStr = scanner.nextLine();
            if (validate.validateChoice(choiceStr, 0, 2)) {
                choice = Integer.parseInt(choiceStr);
                List<Student> newList = new ArrayList<>(manager.getStudents());
                switch (choice) {
                    case 1:
                        Collections.sort(newList);
                        manager.display(newList);
                        break;
                    case 2:
                        Collections.sort(newList);
                        Collections.reverse(newList);
                        manager.display(newList);
                        break;
                    case 0:
                        check = false;
                }
            }
        }
    }
}
