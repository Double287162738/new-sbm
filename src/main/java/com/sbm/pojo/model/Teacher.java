package com.sbm.pojo.model;

import java.util.List;

public class Teacher {

    String Tid;
    String Tname;
    List<Student> student;

    public String getTid() {
        return Tid;
    }

    public void setTid(String tid) {
        Tid = tid;
    }

    public String getTname() {
        return Tname;
    }

    public void setTname(String tname) {
        Tname = tname;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Teacher [Tid=" + Tid + ", Tname=" + Tname + ", student=" + student + "]";
    }


}
