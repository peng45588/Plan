package com.plan.function;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by snow on 15-6-10.
 */
public interface DataOperateimp {
    Session session = null;
    SessionFactory sf = null;
    Configuration config = null;

    public Session Start();
    public void Close();
    public void Save(Object o);
}
