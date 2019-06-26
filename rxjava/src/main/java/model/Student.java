package model;

/**
 * Created by riven_chris on 2016/1/26.
 */
public class Student {

    private Course[] courses = null;

    private String tag = null;

    public Student(Course[] course) {
        this.courses = course;
    }

    public Course[] getCourses() {
        return courses;
    }

    public void setCourses(Course[] courses) {
        this.courses = courses;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
