package com.learning.university;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Course {

    public @Id @GeneratedValue int id;

    public Course(int id, String cname, int maxStudentAllowed, List<Subject> subjects) {
        this.id = id;
        this.cname = cname;
        this.maxStudentAllowed = maxStudentAllowed;
        this.subjects = subjects;
    }

    public String cname;
    public int maxStudentAllowed;

    public Course() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public int getMaxStudentAllowed() {
        return maxStudentAllowed;
    }

    public void setMaxStudentAllowed(int maxStudentAllowed) {
        this.maxStudentAllowed = maxStudentAllowed;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    @OneToMany(cascade = CascadeType.ALL)
    public List<Subject> subjects;
}
