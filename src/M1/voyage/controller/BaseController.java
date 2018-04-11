package M1.voyage.controller;

import M1.voyage.models.Client;
import M1.voyage.models.HotelView;
import M1.voyage.models.Reservation;
import M1.voyage.models.User;
import M1.voyage.services.AuthService;
import M1.voyage.services.BaseService;
import M1.voyage.services.FiltreService;
import M1.voyage.services.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jws.Oneway;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Andrianina_pc on 03/04/2018.
 */

@Controller
public class BaseController {

    @Autowired
    protected BaseService baseService;
    @Autowired
    protected AuthService authService;
    @Autowired
    protected ReservationService reservationService;
    @Autowired
    protected FiltreService filtreService;


    protected String allCommodite;

    public int getNbrReservationByClient(HttpSession session) throws Exception
    {
        if(authService.checkSession(session)){
            Reservation res=new Reservation();
            Client client=(Client)session.getAttribute("client_voyage");
            res.setId_client(client.getId());
            return reservationService.getNbrReservationByClient(res);
        }
        return 0;
    }

}
