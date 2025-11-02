package model;

public class Grade {

    private int grade_id;
    private int enrollment_id;
    private double grade_value;

    public Grade (int grade_id, int enrollment_id, double grade_value) {
        this.grade_id = grade_id;
        this.enrollment_id = enrollment_id;
        this.grade_value = grade_value;
    }

}
