package StudentManagerment;
import java.util.ArrayList;
import java.util.Scanner;

class Student {
    private String studentID;
    private String name;
    private double score;
    private String rank;

    // Constructor
    public Student(String studentID, String name, double score) {
        this.studentID = studentID;
        this.name = name;
        setScore(score); // Sử dụng setter để tự động cập nhật xếp hạng
    }

    // Getter và Setter (Bảo vệ dữ liệu và Ẩn thông tin)
    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score >= 0 && score <= 10) {
            this.score = score;
            this.rank = calculateRank(score); // Cập nhật xếp hạng khi điểm thay đổi
        } else {
            System.out.println("Điểm không hợp lệ. Điểm phải nằm trong khoảng từ 0 đến 10.");
        }
    }

    public String getRank() {
        return rank;
    }

    // Phương thức tính toán xếp hạng được ẩn bên trong lớp (Trừu tượng hóa)
    private String calculateRank(double score) {
        if (score < 5.0) return "Không đạt";
        if (score < 6.5) return "Trung bình";
        if (score < 7.5) return "Tốt";
        if (score < 9.0) return "Rất tốt";
        if (score <= 10) return "Xuất sắc";
        return "Điểm không hợp lệ";
    }
}

public class StudentManagerment {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Nhập số lượng học sinh tối đa: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();

        ArrayList<Student> students = new ArrayList<>();
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (students.size() < maxStudents) {
                        addStudent(students);
                    } else {
                        System.out.println("Số lượng học sinh đã đạt tối đa.");
                    }
                    break;
                case 2:
                    updateStudent(students);
                    break;
                case 3:
                    deleteStudent(students);
                    break;
                case 4:
                    sortStudents(students);
                    displayStudents(students);
                    break;
                case 5:
                    searchStudent(students);
                    break;
                case 6:
                    displayStudents(students);
                    break;
                case 0:
                    System.out.println("Thoát chương trình.");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    // Hiển thị menu
    public static void showMenu() {
        System.out.println("======== Quản lý học sinh ========");
        System.out.println("1. Thêm học sinh                ||");
        System.out.println("2. Sửa thông tin học sinh       ||");
        System.out.println("3. Xóa học sinh                 ||");
        System.out.println("4. Sắp xếp học sinh theo điểm   ||");
        System.out.println("5. Tìm kiếm học sinh theo mã    ||");
        System.out.println("6. Hiển thị danh sách học sinh  ||");
        System.out.println("0. Thoát                        ||");
        System.out.println("==================================");
        System.out.println("Nhập lựa chọn của bạn: ");
    }

    // Phương thức tìm kiếm học sinh theo mã số
    public static Student searchStudentByID(ArrayList<Student> students, String studentID) {
        for (Student s : students) {
            if (s.getStudentID().equals(studentID)) {
                return s;
            }
        }
        return null;
    }

    // Thêm học sinh
    public static void addStudent(ArrayList<Student> students) {
        System.out.print("Nhập mã số học sinh: ");
        String studentID = scanner.nextLine();

        if (searchStudentByID(students, studentID) != null) {
            System.out.println("Mã số học sinh đã tồn tại. Vui lòng nhập mã khác.");
            return;
        }
        System.out.print("Nhập tên học sinh: ");
        String name = scanner.nextLine();

        System.out.print("Nhập điểm học sinh (0-10): ");
        double score = scanner.nextDouble();
        scanner.nextLine();
        if (score > 10 || score < 0) {
            System.out.println("Điểm không hợp lệ. Vui lòng nhập lại.");
            return;
        }

        students.add(new Student(studentID, name, score));
        System.out.println("Học sinh đã được thêm thành công.");
    }

    // Sửa thông tin học sinh
    public static void updateStudent(ArrayList<Student> students) {
        System.out.print("Nhập mã số học sinh cần sửa: ");
        String studentID = scanner.nextLine();

        Student student = searchStudentByID(students, studentID);
        if (student != null) {
            System.out.print("Nhập tên mới: ");
            String newName = scanner.nextLine();

            System.out.print("Nhập điểm mới (0-10): ");
            double newScore = scanner.nextDouble();
            scanner.nextLine();

            student.setName(newName);
            student.setScore(newScore);

            System.out.println("Thông tin học sinh đã được cập nhật.");
        } else {
            System.out.println("Không tìm thấy học sinh.");
        }
    }

    // Xóa học sinh
    public static void deleteStudent(ArrayList<Student> students) {
        System.out.print("Nhập mã số học sinh cần xóa: ");
        String studentID = scanner.nextLine();

        Student student = searchStudentByID(students, studentID);
        if (student != null) {
            students.remove(student);
            System.out.println("Học sinh đã được xóa.");
        } else {
            System.out.println("Không tìm thấy học sinh.");
        }
    }

    // Sắp xếp học sinh theo điểm
    public static void sortStudents(ArrayList<Student> students) {
        System.out.println("Chọn kiểu sắp xếp:");
        System.out.println("1. Sắp xếp từ thấp đến cao");
        System.out.println("2. Sắp xếp từ cao đến thấp");
        System.out.print("Nhập lựa chọn của bạn: ");
        int sortOption = scanner.nextInt();
        scanner.nextLine();

        students.sort((s1, s2) -> {
            if (sortOption == 1) {
                return Double.compare(s1.getScore(), s2.getScore());
            } else {
                return Double.compare(s2.getScore(), s1.getScore());
            }
        });

        System.out.println("Danh sách học sinh đã được sắp xếp.");
    }

    // Tìm kiếm học sinh theo mã số
    public static void searchStudent(ArrayList<Student> students) {
        System.out.print("Nhập mã số học sinh cần tìm: ");
        String studentID = scanner.nextLine();

        Student student = searchStudentByID(students, studentID);
        if (student != null) {
            System.out.println("Thông tin học sinh:");
            System.out.println("Mã số: " + student.getStudentID());
            System.out.println("Họ và tên: " + student.getName());
            System.out.println("Điểm: " + student.getScore());
            System.out.println("Xếp hạng: " + student.getRank());
        } else {
            System.out.println("Không tìm thấy học sinh.");
        }
    }

    // Hiển thị danh sách học sinh
    public static void displayStudents(ArrayList<Student> students) {
        System.out.println("Danh sách học sinh:");
        for (Student s : students) {
            System.out.println("Mã số: " + s.getStudentID() + ", Tên: " + s.getName() + ", Điểm: " + s.getScore() + ", Xếp hạng: " + s.getRank());
        }
    }
}
