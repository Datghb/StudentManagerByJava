package Students;
// Student.java
public class Student {
    private final String studentID;// Final : khi được gán với một biến tức là biến đó sẽ không bị thay đổi chỉ được gán một lần duy nhất
    private final String name;
    private double score;
    private String rank;

    public Student(String studentID, String name, double score) {
        this.studentID = studentID;
        this.name = name;
        this.score = score;
        this.rank = Setrank(score);
    }

    public String getStudentID() {
        return studentID;
    }
    public String getName() {
        return name;
    }
    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
        this.rank = Setrank(score);
    }

    public String getRank() {
        return rank;
    }

    private String Setrank(double score) {
        if (score < 5.0) return "Không đạt";
        if (score < 6.5) return "Trung bình";
        if (score < 7.5) return "Tốt";
        if (score < 9.0) return "Rất tốt";
        if (score <= 10) return "Xuất sắc";
        return "Điểm bạn nhập sai định dạng không thể xếp hạng";
    }
}
