package crud.controller;

import crud.model.Role;
import crud.model.User;
import crud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Collections;


@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/registration")
    public String registration(final Principal principal) {
        if (principal == null) return "registration.html";

        return "redirect:/";
    }

    @PostMapping("/registration")
    @ResponseBody
    public void addUser(@RequestBody User user, final Principal principal) {
//        if (principal == null) {
//            var userFromDb = userRepository.findByUsername(user.getName());
//
//            if (userFromDb != null) {
//                return;
//            }
//
//            user.setActive(true);
//            user.setRoles(Collections.singleton(Role.USER));
//            userRepository.save(user);
//        }
    }
}