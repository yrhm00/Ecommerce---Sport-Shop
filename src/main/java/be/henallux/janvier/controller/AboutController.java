package be.henallux.janvier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/a-propos")
public class AboutController {

    @GetMapping
    public String showAbout() {
        return "a-propos";
    }
}
