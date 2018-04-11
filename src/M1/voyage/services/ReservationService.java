package M1.voyage.services;


import java.util.List;

import M1.voyage.models.Reservation;
import M1.voyage.services.BaseService;
import org.hibernate.Query;
import org.hibernate.Session;
import M1.voyage.dao.HibernateDao;
import M1.voyage.models.BaseModel;
import org.hibernate.query.NativeQuery;
import org.springframework.stereotype.Component;

@Component
public class ReservationService extends BaseService
{
	//-------------------- getNombre de reservation par client -----------------//
	 public int getNbrReservationByClient(Reservation res) throws Exception
    {
    	Session session = null;
    	String sql="SELECT COUNT(*) FROM reservation WHERE id_client=:id_client";
    	try{
    		session = this.getHibernateDao().getSessionFactory().openSession();
    		NativeQuery query = session.createNativeQuery(sql);
    		query.setParameter("id_client", res.getId_client());
    		List results = query.list();
    		return Integer.parseInt(String.valueOf(results.get(0)));
    	}
    	catch(Exception ex){
    		ex.printStackTrace();
    		throw ex;
    	}
    	finally{
    		session.close();
    	}
    	
    }
}
