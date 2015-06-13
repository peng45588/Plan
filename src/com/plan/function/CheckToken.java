package com.plan.function;

import com.plan.data.UserEntity;

import java.util.List;

/**
 * Created by snow on 15-6-1.
 */
public class CheckToken {

    public static boolean CheckToken(DataOpetate dataOpetate, String account, String token) {
        try {
            List it = dataOpetate.SelectTb("select user from UserEntity as user where user.account like " + account);
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

}
