package org.gfg.FoodDeliveryApp.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.gfg.FoodDeliveryApp.Model.User;
import org.gfg.FoodDeliveryApp.Request.UserRequest;
import org.gfg.FoodDeliveryApp.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userOnbaord/{$userType}")
    public User userOnboard(@RequestBody @Valid UserRequest user) throws JsonProcessingException {
           return userService.userOnboard(user);
    }
}
