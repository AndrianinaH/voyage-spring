package M1.voyage.dao;

import M1.voyage.models.BaseModel;
import M1.voyage.utilitaire.Fonction;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Component
public class HibernateDao {
    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void save(BaseModel obj) throws Exception{
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.save(obj);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public void update(BaseModel obj) throws Exception{
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.update(obj);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public void delete(BaseModel obj) throws Exception{
        Session session = null;
        Transaction tr=null;
        try{
            session = getSessionFactory().openSession();
            tr=session.beginTransaction();
            session.delete(obj);
            tr.commit();
        }catch (Exception ex){
            if(tr!=null)
                tr.rollback();
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }

    public void findById(BaseModel obj) throws Exception{
        Session session = null;
        try{
            session = getSessionFactory().openSession();
            session.load(obj,obj.getId());
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }


    public List<BaseModel> findAll(BaseModel obj,int...limit)  throws Exception{
        Session session = null;
        Iterator map = getAttributValue(obj);
        try{
            session = getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(obj.getClass());
            if(limit !=null && limit.length>0){
	          	 criteria.setFirstResult(limit[1]);
	          	 criteria.setMaxResults(limit[0]);	 
            }
            while(map.hasNext()){
               Map.Entry thisEntry = (Map.Entry) map.next();
               criteria.add(Restrictions.eq(String.valueOf(thisEntry.getKey()),thisEntry.getValue()));
               System.out.println(String.valueOf(thisEntry.getKey())+" "+thisEntry.getValue());
            }
            criteria.addOrder(Order.asc("id"));
           return criteria.list();
        }catch (Exception ex){
            throw ex;
        }finally {
            if(session!=null)
                session.close();
        }
    }
    public Iterator  getAttributValue(Object e) throws Exception{
        Field [] fields = Fonction.getFieldsExceptList(e.getClass().getDeclaredFields());
        HashMap ret = new HashMap();
        for(Field field : fields)
        {
            Method temp = e.getClass().getDeclaredMethod("get"+Fonction.toUpperFirst(field.getName()));
            try
            {
	            if(temp.invoke(e)!=null)
	            {
	                String str = (String)temp.invoke(e);
	                if(!str.equals("0")){
	                	ret.put(field.getName(),temp.invoke(e));
	                }
	            }
            }
            catch(Exception ex){}
        }   
    return ret.entrySet().iterator();
    }
    
    public int getCount(BaseModel baseModel) throws Exception
    {
    	Session session = null;
    	String sql ="SELECT COUNT(*) FROM "+baseModel.getClass().getSimpleName();
    	try{
    		session = sessionFactory.openSession();
    		SQLQuery query = session.createSQLQuery(sql);
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
