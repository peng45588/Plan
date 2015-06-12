package com.plan.function;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Objects;

/**
 * Created by snow on 15-6-10.
 */
public class DataOpetate implements DataOperateimp {
    private static SessionFactory sf;
    Session session ;
    static{
        Configuration config = new Configuration();
        sf = config.configure().buildSessionFactory();
    }
    public Session getSession(){
        return session;
    }
    public void Close(){
        this.sf.close();
    }

    /**
     * 插入
     * @param o 插入的對象
     */
    public void Save(Object o){
        this.session = sf.openSession();
        this.session.beginTransaction();
        this.session.save(o);
        this.session.getTransaction().commit();
        this.session.close();
    }

    /**
     * 模糊查詢
     * @param hql hql語句:"select user from Test as user where user.name like:name";
     * @return
     */
    public List SelectTb(String hql){
        List it = null;
        this.session = sf.openSession();
        this.session.beginTransaction();
        Query query = this.session.createQuery(hql);
        //query.setString("account","%"+name+"%");
        it = query.list();
        this.session.getTransaction().commit();;
        this.session.close();
        return it;
    }

    /**
     * 更新表
     * @param o 更新的對象
     */
    public void UpdataTb(Object o){
        session = sf.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }
    //TODO
    public void DeleteTb(){
        session = sf.openSession();
        session.beginTransaction();

        session.close();
    }
}
