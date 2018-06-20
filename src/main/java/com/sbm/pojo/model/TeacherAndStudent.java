package com.sbm.pojo.model;

public class TeacherAndStudent {

    Teacher teacher;
    Student student;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "TeacherAndStudent [teacher=" + teacher + ", student=" + student + "]";
    }


}
