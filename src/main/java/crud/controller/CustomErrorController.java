package crud.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error(Principal principal, HttpServletResponse response) {
        response.setStatus(404);
        return principal != null ? "crud.html" : "auth.html";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
