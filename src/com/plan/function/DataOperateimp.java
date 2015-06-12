package com.plan.function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by snow on 15-6-10.
 */
public interface DataOperateimp {
    Session session = null;
    SessionFactory sf = null;
    Configuration config = null;

    public Session getSession();
    public void Close();
    public void Save(Object o);
    public List SelectTb(String hql);
    public void UpdataTb(Object o);
    public void DeleteTb();
}
