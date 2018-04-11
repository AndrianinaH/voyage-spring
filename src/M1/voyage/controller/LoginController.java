package M1.voyage.controller;

import M1.voyage.models.Client;
import M1.voyage.models.Hotel;
import M1.voyage.models.HotelView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Andrianina_pc on 08/04/2018.
 */
@Controller
public class LoginController extends BaseController {

    @RequestMapping(value="/login",method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView model = new ModelAndView("login");
        model.addObject("client", new Client());
        return model;
    }

    @RequestMapping(value="/login", method = RequestMethod.POST)
    public ModelAndView loginService(
            @RequestParam(value="red", required=false) String url,
            @ModelAttribute("client") Client client,
            HttpSession session) throws Exception {

        Client currentUser = authService.login(client);
        if (currentUser != null) {
            session.setAttribute("client_voyage", currentUser);
            return new ModelAndView("redirect:/" + "");
        } else {
            ModelAndView mv = new ModelAndView("login");
            String loginError = "Authentification echouer";
            mv.addObject("client", client);
            mv.addObject("loginError", loginError);
            return mv;
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session) {
        session.removeAttribute("client_voyage");
        return new ModelAndView("redirect:/login");
    }
}
