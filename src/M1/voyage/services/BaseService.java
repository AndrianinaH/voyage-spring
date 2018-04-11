package M1.voyage.services;

import M1.voyage.dao.HibernateDao;
import M1.voyage.models.BaseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class BaseService {
	@Autowired
	private HibernateDao hibernateDao;

	public List<BaseModel> findAll(BaseModel obj, int...limit) throws Exception
	{
		return hibernateDao.findAll(obj,limit);
	}
	public void findById(BaseModel obj) throws Exception
	{
		hibernateDao.findById(obj);
    }

	public void save(BaseModel obj)throws Exception
	{
		hibernateDao.save(obj);
	}
	public void update(BaseModel obj)throws Exception
	{
		hibernateDao.update(obj);
	}
	public void delete(BaseModel obj)throws Exception
	{
		hibernateDao.delete(obj);
	}
	public int count(BaseModel obj)throws Exception
	{
		return hibernateDao.getCount(obj);
	}
	
	
	
	public HibernateDao getHibernateDao() {
        return hibernateDao;
    }

    public void setHibernateDao(HibernateDao hibernateDao) {
        this.hibernateDao = hibernateDao;
    }
}
