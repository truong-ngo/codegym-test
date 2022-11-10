import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentManager {
    private static StudentManager instance;
    private String path = "data/students.csv";
    private List<Student> students;
    private IOFile<Student> ioFile;

    public StudentManager() {
        ioFile = new IOFile<>();
        students = ioFile.readFileCSV(path);
    }

    public List<Student> getStudents() {
        return students;
    }

    public IOFile<Student> getIoFile() {
        return ioFile;
    }

    public static StudentManager getInstance() {
        if (instance == null) {
            instance = new StudentManager();
        }
        return instance;
    }

    public void add(Student student) {
        students.add(student);
        ioFile.writeFileCSV(students, path);
    }

    public void update(Student student, String id) {
        int index = getIndexByID(id);
        students.set(index, student);
        ioFile.writeFileCSV(students, path);
    }

    public void delete(String id) {
        int index = getIndexByID(id);
        students.remove(index);
        ioFile.writeFileCSV(students, path);
    }

    public int getIndexByID(String id) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getId().equals(id))
                return i;
        }
        return -1;
    }

    public boolean checkDuplicateId(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkId(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public Student getStudentByID(String id) {
        if (!checkId(id)) {
            return null;
        } else {
            for (Student student : students) {
                if (student.getId().equals(id)) {
                    return student;
                }
            }
        }
        return null;
    }

    public void display(Scanner scanner) {
        for (Student student : students) {
            System.out.println(student);
            while (true) {
                String input = scanner.nextLine();
                if (input.equals("")) {
                    break;
                }
            }
        }
    }

    public void display(List<Student> students) {
        for (Student student : students) {
            System.out.println(student);
        }
    }

    public void writeToFile() {
        ioFile.writeFileCSV(students, path);
    }

    public void readFromFile() {
        students = ioFile.readFileCSV(path);
    }
}
