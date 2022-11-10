import java.util.Vector;

public class Printer {
    public void printMenu() {
        System.out.println("---- STUDENT MANAGEMENT PROGRAM ----");
        System.out.println("Select function by number (to continue)");
        System.out.println("1. View student list");
        System.out.println("2. Add student");
        System.out.println("3. Update student");
        System.out.println("4. Delete student");
        System.out.println("5. Sort");
        System.out.println("6. Read from file");
        System.out.println("7. Write to file");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }

    public void printSubMenu() {
        System.out.println("---- Sort students by GPA ----");
        System.out.println("1. Sort grade point average in ascending");
        System.out.println("2. Sort grade point average in descending");
        System.out.println("0. Exit");
        System.out.println("Enter your choice: ");
    }

    public void inputField(String content) {
        System.out.println("Enter " + content + ": ");
    }

    public void invalidData() {
        System.out.println("Invalid input data");
    }
}
