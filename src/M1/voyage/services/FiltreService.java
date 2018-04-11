
package M1.voyage.services;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import M1.voyage.models.BaseModel;
import M1.voyage.models.Commodite;
import M1.voyage.models.HotelView;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
public class FiltreService extends BaseService
{
	//----------------- lecture ecriture fichier ------------------//
	public String readFile(File file) throws IOException
	{
		String ligne="";
		String ret="";
		BufferedReader buffR=null;
		try {
			FileReader fileR=new FileReader(file);
			buffR=new BufferedReader(fileR);
			while((ligne=buffR.readLine()) !=null)
			{
				ret+=ligne;
			}
			System.out.println("entrain de lire");
			return ret;
		}catch (Exception e){
            throw e;
        }finally {
            if(buffR != null){
            	buffR.close();
            }
        }
	}
	public void writeFile(File file,List<BaseModel> allList) throws IOException
	{
		FileWriter fileW=new FileWriter(file);
		BufferedWriter buffW=null;
		try{
			buffW=new BufferedWriter(fileW);
			for(BaseModel list : allList)
			{	
				Commodite com=(Commodite)list;
				buffW.write("<div class=\"inline field\"><div class=\"ui toggle checkbox\"><input type=\"checkbox\" tabindex=\"0\" name=\"list_id_commodites\" value=\""+com.getId()+"\" class=\"hidden\"><label>"+com.getNom_commodite()+"</label></div></div>");
			}
			System.out.println("entrain d'ecrire");
		}catch (Exception e){
            throw e;
        }finally {
            if(buffW != null){
            	buffW.close();
            }
        }
	 }
	//------------ transform list commodite to list string --------//
	public void updateFile(File file) throws Exception
	{
		List<BaseModel> allCommodite=this.findAll(new Commodite());
		this.writeFile(file, allCommodite);
	}
	//----------------------------- mila tsabona
	//------------ liste des commodites dans le cache -------------//
	public String getCommodite() throws Exception
	{
		//----------------------------- mila tsabona
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpServletRequest request = attr.getRequest();
		File file=new File(request.getRealPath("/commodite.cache"));
		if(!file.exists())
		{
			file.createNewFile();
			System.out.println("file existe pas");
			updateFile(file);
		}
		return readFile(file);
	}
	//--------------- recherche d'hotel ou de destination -----------------//
	public List<BaseModel> findHotelOrDestination(HotelView obj, String find)  throws Exception{
        Session session = null;
        try{
            session = this.getHibernateDao().getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(obj.getClass());
            Criterion nom_hotel=Restrictions.like("nom_hotel", "%"+find+"%");
            Criterion nom_destination= Restrictions.like("nom_destination","%"+find+"%");
            criteria.add(Restrictions.or(nom_hotel, nom_destination));
            criteria.addOrder(Order.asc("id"));
           return criteria.list();
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
	//--------------- recherche d'hotel ou de destination -----------------//
	public List<BaseModel> findByCommodite(HotelView obj,String[] list_id_commodites)  throws Exception{
        Session session = null;
        try{
            session = this.getHibernateDao().getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(obj.getClass());
            Disjunction or = Restrictions.disjunction();
            for(String id_com : list_id_commodites)
            {
            	 Criterion id_commodite=Restrictions.eq("id_commodite",Integer.parseInt(id_com));
            	 or.add(id_commodite);
            }
            criteria.add(or);
            criteria.addOrder(Order.asc("id"));
           return criteria.list();
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
}
//SELECT * FROM hotel_view WHERE id_commodite=1 OR id_commodite=2 OR id_commodite=4 OR id_commodite ORDER BY id_hotel