package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ResourceController {

    @GetMapping("/login")
    public String authenticateUser(Principal principal) {
       return principal != null ? "redirect:/" : "login.html";
    }

    @GetMapping("/signup")
    public String registerUser(Principal principal) {
        return principal != null ? "redirect:/" : "signup.html";
    }

    @GetMapping("/")
    public String home(Principal principal) {
        return principal == null ? "redirect:/login" : "redirect:/crud";
    }
}
