package pl.restaurant.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogOutController {
    @GetMapping("/logout1")
    public String logout(){
        return "logout";
    }
}
