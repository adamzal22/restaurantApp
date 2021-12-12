package pl.restaurant.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import pl.restaurant.app.entity.City;
import pl.restaurant.app.entity.User;
import pl.restaurant.app.repository.CityRepository;
import pl.restaurant.app.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
public class RegistrationController {
    private final UserService userService;
    private final CityRepository cityRepository;

    public RegistrationController(UserService userService, CityRepository cityRepository) {
        this.userService = userService;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/addUser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "/registration/registration";
    }


    @PostMapping("/addUser")
    public String saveUser(@ModelAttribute("user") @Valid User user, BindingResult result){
        if (result.hasErrors()) {
            return "/registration/registration";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }

    @GetMapping("/addVendor")
    public String createVendor(Model model) {
        model.addAttribute("user", new User());
        return "/registration/registration";
    }


    @PostMapping("/addVendor")
    public String saveVendor(@ModelAttribute("user") User user){

        userService.saveVendor(user);
        return "redirect:/login";
    }

    @ModelAttribute("cities")
    public List<City> cities() {
        return cityRepository.findAll();
    }
}
