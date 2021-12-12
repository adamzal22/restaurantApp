package pl.restaurant.app.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.restaurant.app.entity.*;
import pl.restaurant.app.repository.CityRepository;
import pl.restaurant.app.repository.PostRepository;
import pl.restaurant.app.repository.RestaurantRepository;
import pl.restaurant.app.repository.UserRepository;
import pl.restaurant.app.search.RestaurantSearchMode;
import pl.restaurant.app.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/vendor")
public class VendorController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final RestaurantRepository restaurantRepository;
    private final CityRepository cityRepository;

    public VendorController(UserService userService, UserRepository userRepository, PostRepository postRepository, RestaurantRepository restaurantRepository, CityRepository cityRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.restaurantRepository = restaurantRepository;
        this.cityRepository = cityRepository;
    }

    @GetMapping("/create-vendor")
    @ResponseBody
    public String createVendor() {
        User user = new User();
        user.setLogin("vendor");
        user.setPassword("vendor");
        userService.saveVendor(user);
        return "admin";
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        User user = userRepository.getById(entityUser.getId());
        model.addAttribute("user", user);
        List<Post> posts = postRepository.findAllByUser(entityUser);
        model.addAttribute("posts", posts);

        return "vendor/dashVendor";

    }

    @GetMapping("/dashboard/restaurants")
    public String dashboardRestaurants(Model model, @AuthenticationPrincipal CurrentUser customUser,
                                       @RequestParam(value = "field", required = false) RestaurantSearchMode field,
                                       @RequestParam(value = "query", required = false) String query) {
        if (field != null && StringUtils.isNotEmpty(query)) {
            switch (field) {
                case NAZWA:
                    model.addAttribute("restaurants", restaurantRepository.findAllByName(query));
                    break;
                case MIASTO:
                    model.addAttribute("restaurants", restaurantRepository.findAllByCityName(query));
                    break;
            }
            model.addAttribute("selectedField", field);
        } else {

            User entityUser = customUser.getUser();
            User user = userRepository.getById(entityUser.getId());
            model.addAttribute("user", user);
            List<Restaurant> restaurants = restaurantRepository.findAllByCity(entityUser.getCity());
            model.addAttribute("restaurants", restaurants);
        }
        model.addAttribute("query", query);
        model.addAttribute("searchMode", RestaurantSearchMode.values());
        return "vendor/dashVendorRest";

    }

    @GetMapping("/addPost")
    public String addUserTool(Model model, @AuthenticationPrincipal CurrentUser currentUser) {
        model.addAttribute("post", new Post());
        User entityUser = currentUser.getUser();
        model.addAttribute("restaurants", restaurantRepository.findAllByVendorId(entityUser.getId()));
        return "vendor/addPostForm";
    }

    @PostMapping("/addPost")
    public String addUserToolPost(@ModelAttribute("post") Post post, @AuthenticationPrincipal CurrentUser currentUser){

        post.setUser(currentUser.getUser());
        postRepository.save(post);
        return "redirect:/vendor/dashboard";
    }

    @GetMapping("/deletePost")
    public String prepareRemove(@RequestParam long toRemoveId, Model model) {
        model.addAttribute("post", postRepository.getById(toRemoveId));
        return "remove";
    }

    @PostMapping("/deletePost")
    public String remove(@RequestParam String confirmed, @RequestParam long toRemoveId) {
        if ("yes".equals(confirmed)) {
            postRepository.deleteById(toRemoveId);
        }
        return "redirect:/vendor/dashboard";
    }

    @GetMapping("/editPost")
    public String editUserTool(@RequestParam Long idToEdit, Model model, @AuthenticationPrincipal CurrentUser currentUser){
        model.addAttribute("post", postRepository.getById(idToEdit));
        User entityUser = currentUser.getUser();
        model.addAttribute("restaurants", restaurantRepository.findAllByVendorId(entityUser.getId()));
        return "vendor/addPostForm";
    }

    @PostMapping("/editPost")
    public String saveEdit (@ModelAttribute("post") Post post, @AuthenticationPrincipal CurrentUser currentUser){
        post.setUser(currentUser.getUser());
        postRepository.save(post);
        return "redirect:/vendor/dashboard";
    }
    @GetMapping("/addRestaurant")
    public String addRestaurant(Model model) {
        model.addAttribute("restaurant", new Restaurant());
        /*User entityUser = user.getUser();
        model.addAttribute("restaurant", restaurantRepository.findAllByVendorId(entityUser.getId()));*/
        return "vendor/addRestaurantForm";
    }

    @PostMapping("/addRestaurant")
    public String addRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, @AuthenticationPrincipal CurrentUser currentUser){
        restaurant.setVendor(currentUser.getUser());
        restaurantRepository.save(restaurant);
        return "redirect:/vendor/dashboard";
    }

    @GetMapping("/deleteRestaurant")
    public String prepareRemoveRestaurant(@RequestParam long toRemoveId, Model model) {
        model.addAttribute("post", restaurantRepository.getById(toRemoveId));
        return "remove";
    }

    @PostMapping("/deleteRestaurant")
    public String removeRestaurant(@RequestParam String confirmed, @RequestParam long toRemoveId) {
        if ("yes".equals(confirmed)) {
            restaurantRepository.deleteById(toRemoveId);
        }
        return "redirect:/vendor/dashboard";
    }

    @GetMapping("/dashboard/editRestaurant")
    public String editRestaurant(@RequestParam Long idToEdit, Model model){
        model.addAttribute("restaurant", restaurantRepository.getById(idToEdit));
        return "vendor/editRestaurant";
    }

    @PostMapping("/dashboard/editRestaurant")
    public String saveEditRestaurant (@ModelAttribute("restaurant") @Valid Restaurant restaurant, BindingResult result, @AuthenticationPrincipal CurrentUser currentUser){
        if (result.hasErrors()) {
            return "vendor/editRestaurant";
        }
        restaurant.setVendor(currentUser.getUser());
        restaurantRepository.save(restaurant);
        return "redirect:/vendor/dashboard";
    }
    /*@ModelAttribute("restaurants")
    public List<Restaurant> restaurants() {
        return restaurantRepository.findAll();
    }*/

    /*@ModelAttribute("restaurants")
    public List<Restaurant> restaurants(Long id) {
        return restaurantRepository.findAllByVendorId(id);
    }*/
    @ModelAttribute("cities")
    public List<City> cities() {
        return cityRepository.findAll();
    }
}
