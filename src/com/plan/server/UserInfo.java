package com.plan.server;/**
 * Created by snow on 15-5-31.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.UserEntity;
import com.plan.function.CheckToken;
import com.plan.function.DataOpetate;
import com.plan.function.MD5;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class UserInfo extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private String account;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }


    private String token;
    private String user_account;

    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("UserInfo:" + account + "," + token + "," + user_account);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                List it = dataOpetate.SelectTb("from UserEntity as user where user.account =:para1", user_account);
                if (it.size() == 1) {
                    UserEntity user = (UserEntity) it.get(0);
                    obj.put("status", 1);
                    obj.put("nickname", user.getNickname());
                    obj.put("avatar_url", user.getAvatag());
                }
            } else
                obj.put("status", 0);
        } catch (Exception e) {
            try {
                obj.put("status", 0);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
        ret = obj.toString();
        PrintToHtml.PrintToHtml(response, ret);
        return null;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }
}