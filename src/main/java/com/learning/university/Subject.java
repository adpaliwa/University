package com.learning.university;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Subject {

    public Subject(int subjectId, String sname, String facility) {
        this.subjectId = subjectId;
        this.sname = sname;
        this.facility = facility;
    }

    public Subject() {

    }

    public int getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }

    public String getSName() {
        return sname;
    }

    public void setSName(String sname) {
        this.sname = sname;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public @Id @GeneratedValue int subjectId;

    public String sname;

    public String facility;
}
