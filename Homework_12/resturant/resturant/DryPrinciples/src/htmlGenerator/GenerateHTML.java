package htmlGenerator;

import java.util.ArrayList;
import java.util.List;

public class GenerateHTML {

	public static void main(String[] args) {
		List<Course> courses = new ArrayList<>();
        courses.add(new Course("Spring 2023", "Web Development"));
        courses.add(new Course("Spring 2023", "Natural Language Process"));
        courses.add(new Course("Spring 2023", "Database Systems"));
        courses.add(new Course("Fall 2023", "Software Engineering"));
        courses.add(new Course("Fall 2023", "Datastructure and Algorithms"));
        courses.add(new Course("Fall 2023", "Database Design"));
        

        // Generate the HTML content using a template
        String htmlContent = HTMLGenerator.generateHTML(courses);

        // Write the HTML content to a file
        HTMLFileWriter.writeHTMLToFile("hw3.html", htmlContent);
    }

	

}
