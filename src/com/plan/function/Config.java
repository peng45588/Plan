package com.plan.function;

import com.plan.data.UserEntity;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.security.MessageDigest;
import java.util.List;

/**
 * Created by snow on 15-6-21.
 */
public class Config {


    static BeanFactory factory = null;

    //获得beanfactory的单例
    public static BeanFactory getInstance(){
        if(factory==null)
            factory = new ClassPathXmlApplicationContext("spring-config.xml");
        return factory;
    }
    //check token
    public static boolean CheckToken(DataOpetate dataOpetate, String account, String token) {
        try {
            List it = dataOpetate.SelectTb("select user from UserEntity as user where user.account =:para1",account);
            if (it.size()==1) {
                UserEntity user = (UserEntity) it.get(0);
                if (user.getToken().equals(token)) {//判斷token是否正確
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    //md5 加密
    public final static String MD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
