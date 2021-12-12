package pl.restaurant.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.restaurant.app.entity.CurrentUser;
import pl.restaurant.app.entity.User;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

    /*@GetMapping("/about")
    @ResponseBody
    public String about(@AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        return "Hello " + entityUser.getLogin();}*/
    @GetMapping("/about")
    @ResponseBody
    public String about() { return "Here you can find some details for logged users"; }

}
