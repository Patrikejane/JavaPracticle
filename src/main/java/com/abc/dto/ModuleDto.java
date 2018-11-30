package com.abc.dto;

import com.abc.domain.Student;

import java.util.List;

/**
 * Created by SunimalM on 11/30/2018.
 */
public class ModuleDto {

    private String moduleName;
    private List<Student> students;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

}
