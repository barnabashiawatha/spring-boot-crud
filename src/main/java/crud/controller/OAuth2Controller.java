package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.security.Principal;

//TODO this class should be removed after react-router is added to project
@Controller
@RequestMapping("/oauth2")
public class OAuth2Controller {

   @GetMapping("/redirect")
   public String oauth2Redirect(Principal principal) {
      return principal != null ? "redirect:/" : "../oauth2Redirect.html";
   }
}
