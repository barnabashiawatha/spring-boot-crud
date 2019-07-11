package crud.controller;

import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CrudController {

    @GetMapping("/crud")
    public String crud() {
        return "index.html";
    }

    @GetMapping("/")
    public String home(Principal principal) {
        if (principal == null) return "redirect:/login";

        return "redirect:/crud";
    }
}
