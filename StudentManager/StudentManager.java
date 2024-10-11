package StudentManager;// StudentManager.java
import java.util.ArrayList;
import Students.Student;

public class StudentManager {
    private final ArrayList<Student> students = new ArrayList<>();
    private final int maxStudents;
    public StudentManager(int maxStudents) {
        this.maxStudents = maxStudents;
    }
    public void addStudent(String studentID, String name, double score) {
        if (students.size() >= maxStudents) {
            System.out.println("Số lượng học sinh đã đầy.");
            return;
        }
        if (findStudentByID(studentID) != null) {
            System.out.println("Mã số học sinh đã tồn tại.");
            return;
        }
        students.add(new Student(studentID, name, score));
        System.out.println("Học sinh đã được thêm.");
    }
    public void updateStudent(String studentID, String newName, double newScore) {
        Student student = findStudentByID(studentID);
        if (student != null) {
            student.setScore(newScore);
            System.out.println("Thông tin học sinh đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy học sinh.");
        }
    }

    // delete dùng for
    public void deleteStudent(String studentID) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentID().equals(studentID)) {
                students.remove(i);
                System.out.println("Học sinh đã được xóa.");
                return;
            }
        }
        System.out.println("Không tìm thấy học sinh với ID này.");
    }
    public void sortStudents(boolean ascending) {
        for (int i = 1; i < students.size(); i++) {
            Student currentStudent = students.get(i);
            int j = i - 1;

            // Sắp xếp theo thứ tự tăng dần
            if (ascending) {
                while (j >= 0 && students.get(j).getScore() > currentStudent.getScore()) {
                    students.set(j + 1, students.get(j));
                    j--;
                }
            }
            // Sắp xếp theo thứ tự giảm dần
            else {
                while (j >= 0 && students.get(j).getScore() < currentStudent.getScore()) {
                    students.set(j + 1, students.get(j));
                    j--;
                }
            }
            students.set(j + 1, currentStudent);
        }
    }

    public Student findStudentByID(String studentID) {
        for (Student s : students) {
            if (s.getStudentID().equals(studentID)) {
                return s;
            }
        }
        return null;
    }
    public void displayStudents() {
        for (Student s : students) {
            System.out.println("Mã số: " + s.getStudentID() + ", Tên: " + s.getName() + ", Điểm: " + s.getScore() + ", Xếp hạng: " + s.getRank());
        }
    }
}
