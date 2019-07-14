package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ResourceController {

    @GetMapping("/")
    public String home(Principal principal) {
        return principal != null ? "login.html" : "crud.html";
    }

    @GetMapping("/crud")
    public String crud(Principal principal) {
        return "index.html";
    }
}
