package pl.restaurant.app.controller;


import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.restaurant.app.entity.CurrentUser;
import pl.restaurant.app.entity.Post;
import pl.restaurant.app.entity.Restaurant;
import pl.restaurant.app.entity.User;
import pl.restaurant.app.repository.PostRepository;
import pl.restaurant.app.repository.RestaurantRepository;
import pl.restaurant.app.repository.UserRepository;
import pl.restaurant.app.search.RestaurantSearchMode;
import pl.restaurant.app.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final PostRepository postRepository;
    private final RestaurantRepository restaurantRepository;

    public UserController(UserRepository userRepository,  UserService userService, PostRepository postRepository, RestaurantRepository restaurantRepository) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.postRepository = postRepository;
        this.restaurantRepository = restaurantRepository;
    }



    @GetMapping("/create-user")
    @ResponseBody
    public String createUser() {
        User user = new User();
        user.setLogin("admin");
        user.setPassword("admin");
        userService.saveUser(user);
        return "admin";
    }



    @GetMapping("/dashboard")
    public String dashboard(Model model, @AuthenticationPrincipal CurrentUser customUser) {
        User entityUser = customUser.getUser();
        User user = userRepository.getById(entityUser.getId());
        model.addAttribute("user", user);
        List<Post> posts = postRepository.findAllByCity(entityUser.getCity());
        model.addAttribute("posts", posts);

        return "user/dashUser";

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
        return "user/dashUserRest";

    }

    @GetMapping("/findRestaurant")
    public String findRestaurant(@RequestParam Long idToFind, Model model){
        model.addAttribute("restaurant", restaurantRepository.getById(idToFind));
        return "user/showRest";
    }

/*    @GetMapping("/admin")
    @ResponseBody
    public String userInfo(@AuthenticationPrincipal UserDetails customUser) {
        log.info("customUser class {} " , customUser.getClass());
        return "You are logged as " + customUser;
    }*/

}