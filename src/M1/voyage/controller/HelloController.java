package M1.voyage.controller;

import M1.voyage.models.Destination;
import M1.voyage.models.HotelView;
import M1.voyage.services.BaseService;
import M1.voyage.services.FiltreService;
import M1.voyage.services.ReservationService;
import com.google.gson.Gson;
import javassist.ClassPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private BaseService baseService;
    @Autowired
    private FiltreService filtreService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView afficheHello() throws Exception {
        ModelAndView model = new ModelAndView("hello");

        List list = baseService.findAll(new HotelView());
        Gson gson = new Gson();
        System.out.println(list.size());
        System.out.println(gson.toJson(list));

        System.out.println(filtreService.getCommodite());
        model.addObject("message", "Hello Spring MVC Framework qq ako!!!!");
        return model;
    }
}
