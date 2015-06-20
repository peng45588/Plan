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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SearchUser extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }


    private String account;
    private String token;


    private String nickname;

    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("SearchUser:" + account + "," + token+","+nickname);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                List it = dataOpetate.SelectTb("from UserEntity as user where user.nickname like '%"+ nickname +"%'");

                JSONArray jsonArray = new JSONArray();
                for (int i = 0; i < it.size(); i++) {
                    UserEntity user = (UserEntity) it.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("account", user.getAccount());
                    jsonObject.put("nickname", user.getNickname());
                    jsonObject.put("avatar_url", user.getAvatag());
                    jsonArray.put(jsonObject);
                }
                obj.put("status", 1);
                obj.put("users", jsonArray);
            }else
                obj.put("status", 2);
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}