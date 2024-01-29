package com.example.demo;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.data.Singleton;
import com.example.demo.modal.Courses;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
        try {
            // Read courses from JSON file
            List<Courses> coursesList = readCoursesFromJson("courses.json");
            // Get Singleton instance
            Singleton db = Singleton.getInstance();

            // Insert courses into the database
            for (Courses course : coursesList) {
                db.insertCourse(
                        course.getSemester(),
                        course.getCourse(),
                        course.getInstructor(),
                        course.getLocation()
                );
            }

            // Generate HTML file
            generateHtmlFile(db.getAllCourses());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to read courses from a JSON file.
     * 
     * @param jsonFilePath The path to the JSON file.
     * @return A list of Courses read from the JSON file.
     */
    public static List<Courses> readCoursesFromJson(String jsonFilePath) {
        try (InputStream inputStream = DemoApplication.class.getResourceAsStream("/" + jsonFilePath);
             JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            // Using Gson to parse the JSON file into a list of Course objects
            return new Gson().fromJson(jsonReader, new TypeToken<List<Courses>>() {}.getType());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * Generate an HTML file with a table displaying course information.
     * 
     * @param courses The list of Courses to be displayed in the HTML table.
     */
    public static void generateHtmlFile(List<Courses> courses) {
        try (FileWriter writer = new FileWriter("courses.html")) {
            writer.write("<html><head><style>table {border-collapse: collapse;width: 100%;}th, td {border: 1px solid #dddddd;text-align: left;padding: 8px;}th {background-color: #f2f2f2;}</style></head><body>");
            writer.write("<h2>Courses Table</h2>");
            writer.write("<table><tr><th>Semester</th><th>Course</th><th>Instructor</th><th>Location</th></tr>");

            // Populate the HTML table with course information
            for (Courses course : courses) {
                writer.write("<tr><td>" + course.getSemester() + "</td><td>" + course.getCourse() + "</td><td>" + course.getInstructor() + "</td><td>" + course.getLocation() + "</td></tr>");
            }

            writer.write("</table></body></html>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
