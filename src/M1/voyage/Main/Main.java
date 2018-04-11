package M1.voyage.Main;

import M1.voyage.dao.HibernateDao;
import M1.voyage.models.ChambreView;
import M1.voyage.models.Destination;
import M1.voyage.models.Hotel;
import M1.voyage.models.HotelView;
import M1.voyage.services.BaseService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * Created by Andrianina_pc on 02/03/2018.
 */
public class Main {

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("contexte.xml");
        BaseService baseService = (BaseService) ctx.getBean("baseService");
        List list = baseService.findAll(new Hotel());
        System.out.println(list.size());

//        Destination dest = new Destination(2);
//        baseService.findById(dest);
//        System.out.println(dest.getNom_destination());
        int id_hotel = 2;
        Hotel hotel=new Hotel();
        hotel.setId(2);
        baseService.findById(hotel);
        ChambreView chambre=new ChambreView();
        chambre.setNom_hotel(hotel.getNom_hotel());
        List<ChambreView> chambres = ((List<ChambreView>)(Object)baseService.findAll(chambre));
        System.out.println(chambres.size());


    }
}
