package htmlGenerator;

public class Course {
	private String semester;
	private String course;
	
	public Course(String semester, String course) {
		this.semester = semester;
		this.course = course;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}
	
	

}
