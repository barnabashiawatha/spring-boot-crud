package crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
public class OAuth2RedirectController {

   @GetMapping("/oauth2/redirect")
   public String oauth2Redirect(Principal principal, HttpServletResponse response) {
      if (principal != null) {
       return "redirect:/";
      } else {
         response.setStatus(302);

         return  "../auth.html";
      }
   }
}
