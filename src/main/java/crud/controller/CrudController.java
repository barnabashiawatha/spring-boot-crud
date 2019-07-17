package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class CrudController {


    //TODO change index.html to crud.html
    @GetMapping("/crud")
    public String crud(Principal principal) {
        return "crud.html";
    }
}
