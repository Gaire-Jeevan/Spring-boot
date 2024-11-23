package com.ltp.workbook;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShowController {
    
    @GetMapping("/show")
    public String show() {
        return "shows";
    }
}
