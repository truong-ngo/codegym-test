public class Student implements Comparable<Student> {
    private String id;
    private String name;
    private int age;
    private boolean gender;
    private double average;
    private String address;

    public Student() {

    }

    public Student(String id, String name, int age, boolean gender, String address, double average) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
        this.average = average;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    @Override
    public String toString() {
        String gender = this.gender ? "male" : "female";
        return "ID: " + id + ", name: " + name + ", age: " + age + ", gender:" + gender + ", address: " + address + ", GPA:" + average;
    }

    @Override
    public int compareTo(Student o) {
        return Double.compare(average, o.getAverage());
    }
    public String displayCSV() {
        String gender = this.gender ? "male" : "female";
        return id + "," + name + "," + age + "," + gender + "," + address +","+ average;
    }
}
