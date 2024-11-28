package com.ltp.gradesubmission;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

@Controller
public class GradeController {

    // List<Grade> studentGrades = new ArrayList<>(); as directly populating arraylist cause the duplicate row when we refresh our page
    List<Grade> studentGrades = Arrays.asList(
            new Grade("Harry", "Potions", "C-"),
            new Grade("Hermione", "Arithmancy", "A+"),
            new Grade("Neville", "Charms", "A-")
    );

    @GetMapping("/grades")
    public String sayGrades(Model model) {
//        this is required when we populate arraylist directly

//        studentGrades.add(new Grade("Harry", "Potions", "C-"));
//        studentGrades.add(new Grade("Hermione", "Arithmancy", "A+"));
//        studentGrades.add(new Grade("Neville", "Charms", "A-"));

        model.addAttribute("grades", studentGrades);
        return "grades";
    }
}
