package pl.restaurant.app.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.restaurant.app.entity.*;
import pl.restaurant.app.repository.*;
import pl.restaurant.app.service.UserService;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RestaurantRepository restaurantRepository;
    private final UserService userService;
    private final CityRepository cityRepository;
    private final RoleRepository roleRepository;

    public AdminController(UserRepository userRepository, PostRepository postRepository,
                           RestaurantRepository restaurantRepository, UserService userService,
                           CityRepository cityRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.restaurantRepository = restaurantRepository;
        this.userService = userService;
        this.cityRepository = cityRepository;
        this.roleRepository = roleRepository;
    }

    @GetMapping("/create-admin")
    @ResponseBody
    public String createAdmin() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        Role userRole = roleRepository.findByName("ROLE_ADMIN");
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        userRepository.save(user);
        return "admin";
    }

    @GetMapping("/all")
    public String allUsers(Model model) {

        model.addAttribute("users", userRepository.findAll());
        return "admin/allUsers";
    }
    @GetMapping("/deleteUser")
    public String prepareRemoveUser(@RequestParam long toRemoveId, Model model) {
        model.addAttribute("user", userRepository.getById(toRemoveId));
        return "/admin/removeUser";
    }

    @PostMapping("/deleteUser")
    public String removeUser(@RequestParam String confirmed, @RequestParam long toRemoveId) {
        if ("yes".equals(confirmed)) {
            userRepository.deleteById(toRemoveId);
        }
        return "redirect:/admin/all";
    }
    @GetMapping("/editUser")
    public String editUser(@RequestParam Long idToEdit, Model model){
        model.addAttribute("user", userRepository.getById(idToEdit));
        return "/admin/editUser";
    }

    @PostMapping("/editUser")
    public String saveUser(@ModelAttribute("user") User user){
        userService.saveUser(user);
        return "redirect:/admin/all";
    }

    @GetMapping("/allPosts")
    public String allPosts(Model model) {

        model.addAttribute("posts", postRepository.findAll() );
        return "admin/allPosts";
    }

    @GetMapping("/allRestaurants")
    public String allRestaurants(Model model) {

        model.addAttribute("restaurants", restaurantRepository.findAll());
        return "admin/allRestaurants";
    }
    @GetMapping("/deletePost")
    public String prepareRemove(@RequestParam long toRemoveId, Model model) {
        model.addAttribute("post", postRepository.getById(toRemoveId));
        return "/common/removePost";
    }

    @PostMapping("/deletePost")
    public String remove(@RequestParam String confirmed, @RequestParam long toRemoveId) {
        if ("yes".equals(confirmed)) {
            postRepository.deleteById(toRemoveId);
        }
        return "redirect:/admin/allPosts";
    }

    @GetMapping("/editPost")
    public String editPost(@RequestParam Long idToEdit, Model model){
        model.addAttribute("post", postRepository.getById(idToEdit));
        return "/admin/editPostForm";
    }

    @PostMapping("/editPost")
    public String saveEditPost (@ModelAttribute("post") Post post, @AuthenticationPrincipal CurrentUser currentUser){
        post.setUser(currentUser.getUser());
        postRepository.save(post);
        return "redirect:/admin/allPost";
    }
    @GetMapping("/deleteRestaurant")
    public String prepareRemoveRestaurant(@RequestParam long toRemoveId, Model model) {
        model.addAttribute("post", restaurantRepository.getById(toRemoveId));
        return "/common/removeRest";
    }

    @PostMapping("/deleteRestaurant")
    public String removeRestaurant(@RequestParam String confirmed, @RequestParam long toRemoveId) {
        if ("yes".equals(confirmed)) {
            restaurantRepository.deleteById(toRemoveId);
        }
        return "redirect:/admin/allRestaurants";
    }

    @GetMapping("/editRestaurant")
    public String editRestaurant(@RequestParam Long idToEdit, Model model){
        model.addAttribute("restaurant", restaurantRepository.getById(idToEdit));
        return "admin/editRestaurant";
    }

    @PostMapping("/editRestaurant")
    public String saveEditRestaurant (@ModelAttribute("restaurant") @Valid Restaurant restaurant, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){
        if (result.hasErrors()) {
            return "vendor/editRestaurant";
        }
        restaurant.setVendor(currentUser.getUser());
        restaurantRepository.save(restaurant);
        return "redirect:/admin/allRestaurants";
    }

    @ModelAttribute("restaurants")
    public List<Restaurant> restaurants(Long id) {
        return restaurantRepository.findAllByVendorId(id);
    }
    @ModelAttribute("cities")
    public List<City> cities() {
        return cityRepository.findAll();
    }

}
