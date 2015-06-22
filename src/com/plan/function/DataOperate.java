package com.plan.function;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Created by snow on 15-6-10.
 */
public class DataOperate {
    private static SessionFactory sf = null;
    Session session ;
    static Configuration config = null;
    public DataOperate(){getInstance();
    }
    public static SessionFactory getInstance(){
        if (sf==null) {
            config = new Configuration();
            sf = config.configure().buildSessionFactory();
        }
        return sf;
    }

    Session getSession(){
        return session;
    }
    void Close(){
        this.sf.close();
    }

    void opBegin(){
        this.session = sf.openSession();
        this.session.beginTransaction();
    }
    void opEnd(){
        this.session.getTransaction().commit();
        this.session.close();
    }
    /**
     * 插入
     * @param o 插入的對象
     */
    public void Save(Object o){
        opBegin();
        this.session.save(o);
        opEnd();
    }

    /**
     * 模糊查詢
     * @param hql hql語句:"select user from Test as user where user.name like:name";
     * @return
     */
    public List SelectTb(String hql,String para1){
        opBegin();
        List it;
        Query query = this.session.createQuery(hql);
        query.setString("para1",para1);
        it = query.list();
        opEnd();
        return it;
    }
    public List SelectTb(String hql,int para1){
        opBegin();
        List it;
        Query query = this.session.createQuery(hql);
        query.setInteger("para1",para1);
        it = query.list();
        opEnd();
        return it;
    }
    public List SelectTb(String hql,String para1,String para2){
        opBegin();
        List it;
        Query query = this.session.createQuery(hql);
        query.setString("para1",para1);
        query.setString("para2",para2);
        it = query.list();
        opEnd();
        return it;
    }
    public List SelectTb(String hql,String para1,long para2){
        opBegin();
        List it;
        Query query = this.session.createQuery(hql);
        query.setString("para1",para1);
        query.setLong("para2",para2);
        it = query.list();
        opEnd();
        return it;
    }
    public List SelectTb(String hql){
        opBegin();
        List it;
        Query query = this.session.createQuery(hql);
        it = query.list();
        opEnd();
        return it;
    }
    /**
     * 更新表
     * @param o 更新的對象
     */
    public void UpdataTb(Object o){
        opBegin();
        this.session.update(o);
        opEnd();
    }

    //delete FriendEntity as fe where fe.userAccount=" + account;
    public void DeleteTb(String hql){
        opBegin();
        this.session.createQuery(hql).executeUpdate();
        opEnd();
    }
    public void Delete(Object o){
        opBegin();
        this.session.delete(o);
        opEnd();
    }
}
