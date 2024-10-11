package StudentMain;// Main.java
import java.util.Scanner;
import Students.Student;
import StudentManager.StudentManager;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhập số lượng học sinh tối đa: ");
        int maxStudents = scanner.nextInt();
        scanner.nextLine();

        StudentManager studentManager = new StudentManager(maxStudents);
        int choice;

        do {
            showMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Nhập mã số học sinh: ");
                    String studentID = scanner.nextLine();
                    System.out.print("Nhập tên học sinh: ");
                    String name = scanner.nextLine();
                    System.out.print("Nhập điểm học sinh: ");
                    double score = scanner.nextDouble();
                    scanner.nextLine();
                    studentManager.addStudent(studentID, name, score);
                    break;
                case 2:
                    System.out.print("Nhập mã số học sinh cần sửa: ");
                    String updateID = scanner.nextLine();
                    System.out.print("Nhập tên mới: ");
                    String newName = scanner.nextLine();
                    System.out.print("Nhập điểm mới: ");
                    double newScore = scanner.nextDouble();
                    scanner.nextLine();
                    studentManager.updateStudent(updateID, newName, newScore);
                    break;
                case 3:
                    System.out.print("Nhập mã số học sinh cần xóa: ");
                    String deleteID = scanner.nextLine();
                    studentManager.deleteStudent(deleteID);
                    break;
                case 4:
                    System.out.println("Sắp xếp từ thấp đến cao (1) hoặc từ cao đến thấp (2)?");
                    int sortChoice = scanner.nextInt();
                    scanner.nextLine();
                    studentManager.sortStudents(sortChoice == 1);
                    studentManager.displayStudents();
                    break;
                case 5:
                    System.out.print("Nhập mã số học sinh cần tìm: ");
                    String searchID = scanner.nextLine();
                    Student student = studentManager.findStudentByID(searchID);
                    if (student != null) {
                        System.out.println("Thông tin học sinh: Mã số: " + student.getStudentID() + ", Tên: " + student.getName() + ", Điểm: " + student.getScore() + ", Xếp hạng: " + student.getRank());
                    } else {
                        System.out.println("Không tìm thấy học sinh.");
                    }
                    break;
                case 6:
                    studentManager.displayStudents();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    public static void showMenu() {
        System.out.println("======== Quản lý học sinh =========");
        System.out.println("1. Thêm học sinh                 ||");
        System.out.println("2. Sửa thông tin học sinh        ||");
        System.out.println("3. Xóa học sinh                  ||");
        System.out.println("4. Sắp xếp học sinh theo điểm    ||");
        System.out.println("5. Tìm kiếm học sinh theo mã     ||");
        System.out.println("6. Hiển thị danh sách học sinh   ||");
        System.out.println("===================================");
        System.out.print("Nhập lựa chọn của bạn: ");
    }
}
