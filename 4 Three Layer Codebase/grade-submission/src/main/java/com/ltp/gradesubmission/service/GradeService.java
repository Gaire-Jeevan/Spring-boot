package com.ltp.gradesubmission.service;

import com.ltp.gradesubmission.Constants;
import com.ltp.gradesubmission.Grade;
import com.ltp.gradesubmission.repository.GradeRespository;

import java.util.List;

public class GradeService {

    GradeRespository gradeRespository = new GradeRespository();

    public Grade getGrade(int index) {
        return gradeRespository.getGrade(index);
    }

    public void addGrade(Grade grade) {
        gradeRespository.addGrade(grade);
    }

    public void updateGrade(Grade grade, int index) {
        gradeRespository.updateGrade(grade, index);
    }

    public List<Grade> getGrades() {
        return gradeRespository.getGrades();
    }

    public int getGradeIndex(String id) {
        for (int i = 0; i < getGrades().size(); i++) {
            if (getGrades().get(i).getId().equals(id)) return i;
        }
        return Constants.NOT_FOUND;
    }

    public Grade getGradeById(String id) {
        int index = getGradeIndex(id);
        return index == Constants.NOT_FOUND ? new Grade() : getGrade(index);
    }

    public void submitGrade(Grade grade) {
        int index = getGradeIndex(grade.getId());
        if (index == Constants.NOT_FOUND) {
            addGrade(grade);
        } else {
            updateGrade(grade, index);
        }
    }
}
