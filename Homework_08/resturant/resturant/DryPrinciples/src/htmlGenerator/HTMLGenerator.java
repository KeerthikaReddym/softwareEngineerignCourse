package htmlGenerator;

import java.util.List;

public class HTMLGenerator {
	public static String generateHTML(List<Course> courses) {
		 StringBuilder htmlContent = new StringBuilder();
	        htmlContent.append("<!DOCTYPE html>\n");
	        htmlContent.append("<html>\n");
	        htmlContent.append("<head>\n");
	        htmlContent.append("    <title>Courses taken</title>\n");
	        htmlContent.append("    <style>\n");
	        htmlContent.append("        table {\n");
	        htmlContent.append("            width: 90%;\n");
	        htmlContent.append("            margin: 20px auto;\n");
	        htmlContent.append("            font-size: 35px;\n");
	        htmlContent.append("        }\n");
	        htmlContent.append("        table, th, td {\n");
	        htmlContent.append("            border: 2px solid;\n");
	        htmlContent.append("        }\n");
	        htmlContent.append("        th, td {\n");
	        htmlContent.append("            border: 2px solid;\n");
	        htmlContent.append("            padding: 12px;\n");
	        htmlContent.append("            text-align: left;\n");
	        htmlContent.append("        }\n");
	        htmlContent.append("        th {\n");
	        htmlContent.append("            font-weight: bold;\n");
	        htmlContent.append("        }\n");
	        htmlContent.append("        h1 {\n");
	        htmlContent.append("            text-align: center;\n");
	        htmlContent.append("        }\n");
	        htmlContent.append("    </style>\n");
	        htmlContent.append("</head>\n");
	        htmlContent.append("<body>\n");
	        htmlContent.append("    <h1>My Course History</h1>\n");
	        htmlContent.append("    <table>\n");
	        htmlContent.append("        <tr>\n");
	        htmlContent.append("            <th>Semester</th>\n");
	        htmlContent.append("            <th>Course</th>\n");
	        htmlContent.append("        </tr>\n");

	        // Generate table rows dynamically
	        for (Course course : courses) {
	            htmlContent.append("        <tr>\n");
	            htmlContent.append("            <td>").append(course.getSemester()).append("</td>\n");
	            htmlContent.append("            <td>").append(course.getCourse()).append("</td>\n");
	            htmlContent.append("        </tr>\n");
	        }

	        htmlContent.append("    </table>\n");
	        htmlContent.append("</body>\n");
	        htmlContent.append("</html>\n");

	        return htmlContent.toString();
	    }
}


