package com.plan.function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by snow on 15-6-10.
 */
public class DataOpetate implements DataOperateimp {
    Session session;
    SessionFactory sf;
    Configuration config;
    public Session Start(){
        this.config = new Configuration();
        this.sf = config.configure().buildSessionFactory();
        this.session = sf.openSession();
        return session;
    }
    public void Close(){
        this.session.close();
        this.sf.close();
    }
    public void Save(Object o){
        this.session.beginTransaction();
        this.session.save(o);
        this.session.getTransaction().commit();
    }
}
