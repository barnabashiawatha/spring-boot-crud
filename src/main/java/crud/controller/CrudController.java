package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CrudController {

    @GetMapping("/crud")
    public String crud() {
        return "crud.html";
    }
}
