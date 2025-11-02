package model;

public class Enrollment {

    private int enrollment_id;
    private int student_id;
    private int course_id;

    public Enrollment() {}

    public Enrollment (int enrollment_id, int student_id, int course_id) {
        this.enrollment_id = enrollment_id;
        this.student_id = student_id;
        this.course_id = course_id;
    }

    public void setEnrollment_id(int enrollment_id) {this.enrollment_id = enrollment_id;}

    public int getStudent_id() {return student_id;}
    public void setStudent_id(int student_id) {this.student_id = student_id;}

    public int getCourse_id() {return course_id;}
    public void setCourse_id(int course_id) {this.course_id = course_id;}
}
