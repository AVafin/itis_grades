package repository;

import database.JDBC;
import database.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StudentRepository {
    public ArrayList <Student> getAllStudents() {
        ArrayList<Student> students = new ArrayList<>();

        try {
            Connection connection = JDBC.getConnection();
            PreparedStatement ps = connection.prepareStatement("");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return students;
    }
}
