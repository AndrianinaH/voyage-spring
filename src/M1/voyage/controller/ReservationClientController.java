package M1.voyage.controller;


import M1.voyage.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.List;

/**
 * Created by Andrianina_pc on 10/04/2018.
 */
@Controller
public class ReservationClientController extends BaseController {

    @RequestMapping(value="/reservationClient",method = RequestMethod.GET)
    public ModelAndView reservationClient(HttpSession session) throws Exception{

        if(authService.checkSession(session)){
            ModelAndView model = new ModelAndView("reservation_client");
            allCommodite=filtreService.getCommodite();

            String find="";

            boolean editMode = false;
            ReservationView res=new ReservationView();
            Client client=(Client)session.getAttribute("client_voyage");
            res.setNom_client(client.getNom_client());
            List<ReservationView> reservations = (((List<ReservationView>)(Object)baseService.findAll(res)));


            model.addObject("editMode",editMode);
            model.addObject("reservations",reservations);
            model.addObject("reservation",new Reservation());
            model.addObject("find",find);
            model.addObject("allCommodite",allCommodite);
            model.addObject("nbrReservation",this.getNbrReservationByClient(session));
            return model;
        }
        return new ModelAndView("redirect:/" + "");

    }

    //--------------- anuler reservation client  -------------------//
    @RequestMapping(value="/annulerReservation",method = RequestMethod.GET)
    public ModelAndView annulerReservation(
            @RequestParam(value="id_reservation") String id_reservation,
            HttpSession session) throws Exception{

        if(authService.checkSession(session)){
            try {
                Reservation reservation = new Reservation(Integer.parseInt(id_reservation));
                baseService.delete(reservation);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ModelAndView("redirect:/" + "reservationClient");
            }
        }
        return new ModelAndView("redirect:/" + "reservationClient");

    }

    //--------------- modifier reservation client -------------------//
    @RequestMapping(value="/modifReservation",method = RequestMethod.GET)
    public ModelAndView modifierModal(
            @RequestParam(value="id_reservation") String id_reservation,
            HttpSession session) throws Exception{

        if(authService.checkSession(session)){

            Reservation reservation = new Reservation(Integer.parseInt(id_reservation));
            baseService.findById(reservation);

            ModelAndView model = new ModelAndView("reservation_client");
            allCommodite=filtreService.getCommodite();

            String find="";

            boolean editMode = true;
            ReservationView res=new ReservationView();
            Client client=(Client)session.getAttribute("client_voyage");
            res.setNom_client(client.getNom_client());
            List<ReservationView> reservations = (((List<ReservationView>)(Object)baseService.findAll(res)));

            model.addObject("editMode",editMode);
            model.addObject("reservation",reservation);
            model.addObject("reservations",reservations);
            model.addObject("find",find);
            model.addObject("allCommodite",allCommodite);
            model.addObject("nbrReservation",this.getNbrReservationByClient(session));
            return model;

        }
        return new ModelAndView("redirect:/" + "reservationClient");
    }

    @RequestMapping(value="/updateReservationClient",method = RequestMethod.POST)
    public ModelAndView updateReservationClient(
            @ModelAttribute("reservation") Reservation reservation,
            HttpSession session) throws Exception{

        if(authService.checkSession(session)){
            try{
//                Reservation reservation = new Reservation(Date.valueOf(date_debut), Date.valueOf(date_fin), Integer.parseInt(id_chambre),Integer.parseInt(id_client));
                baseService.update(reservation);
                return new ModelAndView("redirect:/" + "");
            }catch (Exception ex){
                ex.printStackTrace();
                return new ModelAndView("redirect:/" + "reservationClient");
            }
        }
        return new ModelAndView("redirect:/" + "reservationClient");
    }





}
