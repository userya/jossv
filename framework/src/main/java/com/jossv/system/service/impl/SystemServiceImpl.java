package com.jossv.system.service.impl;

import java.util.List;

import com.jossv.framework.dao.CommonDAO;
import com.jossv.framework.dao.DaoFactory;
import com.jossv.framework.dao.sql.Condition;
import com.jossv.system.model.entity.App;
import com.jossv.system.model.entity.Stage;
import com.jossv.system.model.table.AppVO;
import com.jossv.system.service.SystemService;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class SystemServiceImpl implements SystemService {

    private DaoFactory daoFactory;

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public AppVO getApp(Long id) {
//    	CommonDAO<App> dao = daoFactory.getDAO(App.class);
//    	Condition c = new Condition("${a.id} = :id"); 
//    	c.setParameter("id", "a.id", "1");
//    	List<Stage> list = dao.queryChildren(c, "stage", null, Stage.class);
//    	System.out.println(list);
////    	CommonDAO<Stage> dao1 = daoFactory.getDAO(Stage.class);
////    	dao1.query(null);
//    	System.out.println(dao.query(null));
//    	
//    	CommonDAO<Stage> d = daoFactory.getDAO(Stage.class);
//    	System.out.println(d.findByIdWithChild(1L));
    	
    	CommonDAO<App> dao = daoFactory.getDAO(App.class);
    	Condition c = new Condition("${a.id} = :id"); 
    	c.setParameter("id", "a.id", "1");
    	List<App>  list = dao.queryWithChildren(c);
    	System.out.println(list);
        return null;
    }
}
