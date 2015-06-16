package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.UserEntity;
import com.plan.function.CheckToken;
import com.plan.function.DataOpetate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class Recommend extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private String account;
    private String token;
    private String phone_list;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("Recommend:"+account+","+token+","+phone_list);
        String ret = "";
        JSONObject obj = new JSONObject();
        JSONArray jsarray = new JSONArray();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            List it = dataOpetate.SelectTb("from UserEntity");
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                //List<UserEntity> list = new ArrayList<>();
                for (int i = 0; i < it.size(); i++) {
                    UserEntity user = (UserEntity) it.get(i);
                    //TODO 未測試
                    if (phone_list.contains("\"" + user.getPhone() + "\"")) {
                        JSONObject jsob = new JSONObject();
                        jsob.put("account", user.getAccount());
                        jsob.put("nickname", user.getNickname());
                        jsob.put("avatag", user.getAvatag());
                        jsarray.put(jsob);
                        //list.add(user);
                    }
                }
                obj.put("status", 1);
                obj.put("person", jsarray);
            }else {
                obj.put("status",2);
            }
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

    public String getPhone_list() {
        return phone_list;
    }

    public void setPhone_list(String phone_list) {
        this.phone_list = phone_list;
    }
}