package dao;

import java.sql.SQLException;

public interface GradeDAO {
    void allStudentsGPA () throws SQLException;
    void topPerformers() throws SQLException;
    void fullStudentReport() throws SQLException;
}
