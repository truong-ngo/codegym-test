
import java.util.Scanner;

public class Input {
    public String inputStringData(Scanner scanner, Printer printer, String data) {
        printer.inputField(data);
        return scanner.nextLine();
    }

    public String inputNumberData(Scanner scanner, Printer printer, Validate validate, String data) {
        String string = null;
        boolean check = true;
        while (check) {
            printer.inputField(data);
            string = scanner.nextLine();
            if (validate.validateNumber(string) || string.equals("")) {
                check = false;
            } else {
                printer.invalidData();
            }
        }
        return string;
    }

    public String inputGenderData(Scanner scanner, Printer printer, String data) {
        String string = null;
        boolean check = true;
        while (check) {
            printer.inputField(data);
            string = scanner.nextLine();
            if (string.equals("male") || string.equals("female") || string.equals("")) {
                check = false;
            } else {
                printer.invalidData();
            }
        }
        return string;
    }

    public String getStringData(Scanner scanner, Printer printer, String data) {
        String string;
        int count = 0;
        while (true) {
            string = inputStringData(scanner, printer, data);
            if (!string.equals("")) {
                return string;
            }
            if (count >= 0) {
                printer.invalidData();
            }
            count++;
        }
    }

    public int getIntData(Scanner scanner, Printer printer, Validate validate, String data) {
        String string;
        int count = 0;
        while (true) {
            string = inputNumberData(scanner, printer, validate, data);
            if (!string.equals("")) {
                return Integer.parseInt(string);
            }
            if (count >= 0) {
                printer.invalidData();
            }
            count++;
        }
    }

    public double getDoubleData(Scanner scanner, Printer printer, Validate validate, String data) {
        String string;
        int count = 0;
        while (true) {
            string = inputNumberData(scanner, printer, validate, data);
            if (!string.equals("")) {
                return Double.parseDouble(string);
            }
            if (count >= 0) {
                printer.invalidData();
            }
            count++;
        }
    }

    public boolean getGender(Scanner scanner, Printer printer, String data) {
        String string;
        string = inputGenderData(scanner, printer, data);
        return string.equals("male");
    }

    public Student inputStudent(Scanner scanner, Printer printer, Validate validate) {
        StudentManager manager = StudentManager.getInstance();
        String id = null;
        boolean check = true;
        while (check) {
            id = getStringData(scanner, printer, "ID");
            if (!manager.checkDuplicateId(id)) {
                check = false;
            }
        }
        String name = getStringData(scanner, printer, "name");
        int age = getIntData(scanner, printer, validate, "age");
        boolean gender = getGender(scanner, printer, "gender");
        String address = getStringData(scanner, printer, "address");
        double average = getDoubleData(scanner, printer, validate, "average");
        return new Student(id, name, age, gender, address, average);
    }

    public Student updateStudent(String id, Scanner scanner, Printer printer, Validate validate) {
        StudentManager manager = StudentManager.getInstance();
        Student student = manager.getStudentByID(id);
        String name, address;
        int age;
        double average;
        boolean gender;
        name = inputStringData(scanner, printer, "name");
        if (name.equals("")) {
            name = student.getName();
        }
        String ageStr = inputNumberData(scanner, printer, validate, "age");
        if (ageStr.equals("")) {
            age = student.getAge();
        } else {
            age = Integer.parseInt(ageStr);
        }
        String genderStr = inputGenderData(scanner, printer, "age");
        if (genderStr.equals("")) {
            gender = student.isGender();
        } else {
            gender = genderStr.equals("male");
        }
        address = inputStringData(scanner, printer, "address");
        if (address.equals("")) {
            address = student.getName();
        }
        String averageStr = inputNumberData(scanner, printer, validate, "average");
        if (averageStr.equals("")) {
            average = student.getAverage();
        } else {
            average = Double.parseDouble(ageStr);
        }
        return new Student(id, name, age, gender, address, average);
    }
}
