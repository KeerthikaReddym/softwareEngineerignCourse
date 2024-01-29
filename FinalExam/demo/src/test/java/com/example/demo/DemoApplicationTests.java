package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.data.Singleton;
import com.example.demo.modal.Courses;

@SpringBootTest
class DemoApplicationTests {

	@Test
	void contextLoads() {
	}
	

	    @Test
	    void testReadCoursesFromJson() {
	        List<Courses> coursesList = DemoApplication.readCoursesFromJson("courses.json");

	        // Check if the courses are read successfully
	        assertEquals(4, coursesList.size());

	        Courses firstCourse = coursesList.get(0);
	        assertEquals("Fall 2023", firstCourse.getSemester());
	        assertEquals("ACS 560", firstCourse.getCourse());
	        assertEquals("Parker, Matthew", firstCourse.getInstructor());
	        assertEquals("KT 246", firstCourse.getLocation());

	        Courses secondCourse = coursesList.get(1);
	        assertEquals("Spring 2024", secondCourse.getSemester());
	        assertEquals("ACS 567", secondCourse.getCourse());
	        assertEquals("Parker, Matthew", secondCourse.getInstructor());
	        assertEquals("KT 246", secondCourse.getLocation());

	        Courses thirdCourse = coursesList.get(2);
	        assertEquals("Spring 2024", thirdCourse.getSemester());
	        assertEquals("ACS 545", thirdCourse.getCourse());
	        assertEquals("Chen, Zesheng", thirdCourse.getInstructor());
	        assertEquals("ET 112", thirdCourse.getLocation());
	    }

	    @Test
	    void testGenerateHtmlFile() {
	        Singleton db = Singleton.getInstance();
	        db.insertCourse("Fall 2023", "Computer Science", "John Doe", "Room 101");
	        db.insertCourse("Spring 2023", "Mathematics", "Alice Johnson", "Room 201");

	        // Generate HTML file and check if it exists
	        DemoApplication.generateHtmlFile(db.getAllCourses());
	        // Add assertions to check if the HTML file is generated successfully (e.g., check file existence, content, etc.)
	    }

}
