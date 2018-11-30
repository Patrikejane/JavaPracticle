package com.abc.dto;

import com.abc.domain.Student;

import java.util.List;

/**
 * Created by SunimalM on 11/30/2018.
 */
public class AddModuleDto {

    private int moduleId;
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

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }
}
