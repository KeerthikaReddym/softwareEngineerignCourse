package com.example.demo.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.modal.Courses;

/**
 * Singleton class for managing a SQLite database connection and handling course data.
 */
public class Singleton {
    // Singleton instance
    private static Singleton instance;
    // Database connection
    private Connection connection;

    /**
     * Private constructor to prevent instantiation from outside the class.
     * Initializes the SQLite database connection and creates the 'courses' table if not exists.
     */
    private Singleton() {
        try {
            // Load the SQLite JDBC driver (if necessary)
            Class.forName("org.sqlite.JDBC");
            // Establish the database connection
            connection = DriverManager.getConnection("jdbc:sqlite:courses.db");
            // Initialize the database by creating the 'courses' table
            initializeDatabase();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the singleton instance of the class.
     *
     * @return The singleton instance of the Singleton class.
     */
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    /**
     * Initialize the database by creating the 'courses' table if not exists.
     */
    private void initializeDatabase() {
        try {
            String sqlCreateTable = "CREATE TABLE IF NOT EXISTS courses (" +
                    "semester TEXT, " +
                    "course TEXT, " +
                    "instructor TEXT, " +
                    "location TEXT);";
            connection.createStatement().execute(sqlCreateTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Insert a new course into the 'courses' table.
     *
     * @param semester   The semester of the course.
     * @param course     The name of the course.
     * @param instructor The instructor of the course.
     * @param location   The location of the course.
     */
    public void insertCourse(String semester, String course, String instructor, String location) {
        try {
            String sqlInsert = "INSERT INTO courses (semester, course, instructor, location) VALUES (?, ?, ?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(sqlInsert);
            pstmt.setString(1, semester);
            pstmt.setString(2, course);
            pstmt.setString(3, instructor);
            pstmt.setString(4, location);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieve all courses from the 'courses' table.
     *
     * @return A list of Courses containing the retrieved course data.
     */
    public List<Courses> getAllCourses() {
        List<Courses> courses = new ArrayList<>();
        try {
            String sqlSelect = "SELECT * FROM courses;";
            ResultSet rs = connection.createStatement().executeQuery(sqlSelect);
            while (rs.next()) {
                Courses course = new Courses();
                course.setSemester(rs.getString("semester"));
                course.setCourse(rs.getString("course"));
                course.setInstructor(rs.getString("instructor"));
                course.setLocation(rs.getString("location"));
                courses.add(course);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return courses;
    }
}
