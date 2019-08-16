package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ResourceController {

    @GetMapping(value = {"/login", "/signup"})
    public String authenticatePage(Principal principal) {
       return principal != null ? "redirect:/crud" : "auth.html";
    }

    @GetMapping("/")
    public String home(Principal principal) {
        return principal == null ? "redirect:/login" : "redirect:/crud";
    }
}
