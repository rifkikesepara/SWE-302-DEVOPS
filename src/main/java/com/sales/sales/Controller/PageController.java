package com.sales.sales.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("message", "Our Professor is Ahmet Özmen");
        return "index";
    }

}
