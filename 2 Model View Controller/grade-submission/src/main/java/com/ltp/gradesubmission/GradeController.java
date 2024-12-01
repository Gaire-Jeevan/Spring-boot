package com.ltp.gradesubmission;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

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

//    to update field after user submit
    List<Grade> studentGrade = new ArrayList<>();

    @GetMapping("/grades")
    public String sayGrades(Model model) {
//        this is required when we populate arraylist directly

//        studentGrades.add(new Grade("Harry", "Potions", "C-"));
//        studentGrades.add(new Grade("Hermione", "Arithmancy", "A+"));
//        studentGrades.add(new Grade("Neville", "Charms", "A-"));

//        studentGrade is after user submit the form it is directly fetch as after submission of form it is redirect to /grades url
        model.addAttribute("grades", studentGrade);
        return "grades";
    }

    @PostMapping("/handleSubmit")
    public String submitGrade(Grade grade) {
        System.out.println(grade);

//        first out springboot create empty grade object using our empty constructor and update every field using setter with the help of payload of POST request
        studentGrade.add(grade);

        return "redirect:/grades";
    }

    @GetMapping("/")
    public String gradeForm(Model model) {
//        bind entire form to a model object
        model.addAttribute("grade", new Grade());

        return "form";
    }
}
