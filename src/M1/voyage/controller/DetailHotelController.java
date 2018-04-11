package M1.voyage.controller;

import M1.voyage.models.*;
import org.omg.CORBA.INTERNAL;
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
 * Created by Andrianina_pc on 08/04/2018.
 */
@Controller
public class DetailHotelController extends BaseController {

    @RequestMapping(value="/detailHotel",method = RequestMethod.GET)
    public ModelAndView detailHotel(
            @RequestParam(value="id_hotel") String idhotel,
            @RequestParam(value="page") String page,
            HttpSession session) throws Exception{

        boolean editMode = false;
        int id_hotel = Integer.parseInt(idhotel);

        if(id_hotel!=0){
            ModelAndView model = new ModelAndView("detail_hotel");
            allCommodite=filtreService.getCommodite();
            Hotel hotel=new Hotel(id_hotel);
            baseService.findById(hotel);
            ChambreView chambre=new ChambreView();
            chambre.setNom_hotel(hotel.getNom_hotel());
            List<ChambreView> chambres = ((List<ChambreView>)(Object)baseService.findAll(chambre));
            String find="";

            model.addObject("chambres",chambres);
            model.addObject("editMode",editMode);
            model.addObject("reservation", new Reservation());
            model.addObject("page",Integer.parseInt(page));
            model.addObject("find",find);
            model.addObject("allCommodite",allCommodite);
            model.addObject("nbrReservation",this.getNbrReservationByClient(session));
            return model;
        }
        return new ModelAndView("redirect:/" + "");
    }

    @RequestMapping(value="/reserverModal",method = RequestMethod.GET)
    public ModelAndView reserverModal(
            @RequestParam(value="id_chambre") String idchambre,
            @RequestParam(value="id_hotel") String idhotel,
            @RequestParam(value="page") String page,
            HttpSession session) throws Exception{

        int id_chambre = Integer.parseInt(idchambre);
        int id_hotel = Integer.parseInt(idhotel);

        if(authService.checkSession(session)){
            if(id_hotel!=0 && id_chambre!=0){
                ModelAndView model = new ModelAndView("detail_hotel");
                allCommodite=filtreService.getCommodite();
                Hotel hotel=new Hotel(id_hotel);
                baseService.findById(hotel);
                ChambreView chambre=new ChambreView();
                chambre.setNom_hotel(hotel.getNom_hotel());
                List<ChambreView> chambres = ((List<ChambreView>)(Object)baseService.findAll(chambre));
                String find="";

                boolean editMode = true;
                Chambre room = new Chambre(id_chambre);
                baseService.findById(room);
                Reservation reservation = new Reservation();
                reservation.setId_chambre(room.getId());
                reservation.setId_client(authService.getSessionClient(session).getId());

                model.addObject("chambre",room);
                model.addObject("chambres",chambres);
                model.addObject("editMode",editMode);
                model.addObject("reservation", reservation);
                model.addObject("page",Integer.parseInt(page));
                model.addObject("find",find);
                model.addObject("allCommodite",allCommodite);
                model.addObject("nbrReservation",this.getNbrReservationByClient(session));
                return model;
            }
            else return new ModelAndView("redirect:/" + "");
        }
        return new ModelAndView("redirect:/" + "login");

    }

    @RequestMapping(value="/reserver",method = RequestMethod.POST)
    public ModelAndView reserver(
            @RequestParam(value="id_hotel") String idhotel,
            @RequestParam(value="page") String page,
            @RequestParam(value="id_chambre") String id_chambre,
            @RequestParam(value="id_client") String id_client,
            @RequestParam(value="date_debut") String date_debut,
            @RequestParam(value="date_fin") String date_fin,
            HttpSession session) throws Exception{

        if(authService.checkSession(session)){
            try{
                Reservation reservation = new Reservation(Date.valueOf(date_debut), Date.valueOf(date_fin), Integer.parseInt(id_chambre),Integer.parseInt(id_client));
                baseService.save(reservation);
                return new ModelAndView("redirect:/" + "");
            }catch (Exception ex){
                ex.printStackTrace();
                return new ModelAndView("/redirect:/" +"detailHotel?id_hotel="+idhotel+"&page="+page);
            }
        }
        return new ModelAndView("redirect:/" + "login");
    }





}
