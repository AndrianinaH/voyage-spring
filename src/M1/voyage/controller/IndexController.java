package M1.voyage.controller;

import M1.voyage.models.Hotel;
import M1.voyage.models.HotelView;
import M1.voyage.services.FiltreService;
import M1.voyage.services.ReservationService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Andrianina_pc on 03/04/2018.
 */
@Controller
@RequestMapping("/")
public class IndexController extends BaseController {

    //------- pagination setting
    int nbPage = 0;
    int maxResult = 2;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView index(HttpSession session) throws Exception {
        ModelAndView model = new ModelAndView("index");
        String find="";
        int nbr_reservation=0;
        allCommodite=filtreService.getCommodite();
        List<HotelView> hotels=((List<HotelView>)(Object)baseService.findAll(new HotelView(),pagination(1)));

        //------- pagination setting
        nbPage = baseService.count(new Hotel());

        model.addObject("find",find);
        model.addObject("hotels",hotels);
        model.addObject("allCommodite",allCommodite);
        model.addObject("page",1);
        model.addObject("maxResult",maxResult);
        model.addObject("nbPage",nbPage);
        model.addObject("nbrReservation",this.getNbrReservationByClient(session));

        return model;
    }

    @RequestMapping(value="/index")
    public ModelAndView paginate(@RequestParam(value="page") String page, HttpSession session) throws Exception {
        ModelAndView model = new ModelAndView("index");
        String find="";
        allCommodite=filtreService.getCommodite();
        List<HotelView> hotels=((List<HotelView>)(Object)baseService.findAll(new HotelView(),pagination(Integer.parseInt(page))));

        //------- pagination setting
        nbPage = baseService.count(new Hotel());

        model.addObject("find",find);
        model.addObject("hotels",hotels);
        model.addObject("allCommodite",allCommodite);
        model.addObject("page",page);
        model.addObject("maxResult",maxResult);
        model.addObject("nbPage",nbPage);
        model.addObject("nbrReservation",this.getNbrReservationByClient(session));

        return model;
    }

    public int[] pagination(int pages)
    {
        int[] ret = new int[2];
        ret[1] = (pages - 1) * maxResult;
        ret[0] = maxResult;
        return ret;
    }

    //--------- recherche hotel ou destination --------------//
    @RequestMapping(value="/find", method = RequestMethod.POST)
    public ModelAndView find(@RequestParam(value="find") String find, HttpSession session) throws Exception
    {
        ModelAndView model = new ModelAndView("index");
        allCommodite=filtreService.getCommodite();
        List<HotelView> hotels=((List<HotelView>)(Object)filtreService.findHotelOrDestination(new HotelView(),find));

        //------- pagination setting
        nbPage = baseService.count(new Hotel());

        model.addObject("find",find);
        model.addObject("hotels",hotels);
        model.addObject("allCommodite",allCommodite);
        model.addObject("page",1);
        model.addObject("maxResult",maxResult);
        model.addObject("nbPage",nbPage);
        model.addObject("nbrReservation",this.getNbrReservationByClient(session));

        return model;
    }

    //--------- recherche hotel par commodite --------------//
    @RequestMapping(value="/findByCommodite", method = RequestMethod.POST)
    public ModelAndView findByCommodite(
            @RequestParam(value="list_id_commodites", required = false) String[] list_id_commodites,
            HttpSession session) throws Exception {

        ModelAndView model = new ModelAndView("index");
        allCommodite=filtreService.getCommodite();
        List<HotelView> hotels;
        if(list_id_commodites == null)
           hotels = new ArrayList<HotelView>();
        else
            hotels=((List<HotelView>)(Object)filtreService.findByCommodite(new HotelView(),list_id_commodites));

        //------- pagination setting
        nbPage = baseService.count(new Hotel());

        model.addObject("find","Commodite");
        model.addObject("hotels",hotels);
        model.addObject("allCommodite",allCommodite);
        model.addObject("page",1);
        model.addObject("maxResult",maxResult);
        model.addObject("nbPage",nbPage);
        model.addObject("nbrReservation",this.getNbrReservationByClient(session));

        return model;
    }
}
