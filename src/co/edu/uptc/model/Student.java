package co.edu.uptc.model;

public class Student {
    private  String name;
    private  String lastName;
    private  int code;

    public Student(String name, String lastName, int code) {
        this.name = name;
        this.lastName = lastName;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", code=" + code +
                '}';
    }
}
