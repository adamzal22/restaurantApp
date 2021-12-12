package pl.restaurant.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.restaurant.app.entity.CurrentUser;
import pl.restaurant.app.entity.Role;
import pl.restaurant.app.entity.User;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)

    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/success"}, method = RequestMethod.GET)
    public String showDashboard(@AuthenticationPrincipal CurrentUser currentUser) {
        User user = currentUser.getUser();
        Set<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toSet());
        Set<Long> roleID = user.getRoles().stream().map(Role::getId).collect(Collectors.toSet());
        if (roleID.contains(1L)) {
            //render admin page
            return "redirect:user/dashboard";
        } else if (roleID.contains(2L)) {
            //render vendor page
            return "redirect:vendor/dashboard";
        } else
        return "/403";
    }


}
