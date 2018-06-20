package com.sbm.pojo.model;

import java.util.List;

public class Student {

    String Sid;
    String Sname;
    List<Teacher> teachers;

    public String getSid() {
        return Sid;
    }

    public void setSid(String sid) {
        Sid = sid;
    }

    public String getSname() {
        return Sname;
    }

    public void setSname(String sname) {
        Sname = sname;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student [Sid=" + Sid + ", Sname=" + Sname + ", teachers=" + teachers + "]";
    }


}
