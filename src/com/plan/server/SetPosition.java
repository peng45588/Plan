package com.plan.server;/**
 * Created by snow on 15-5-31.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.UserEntity;
import com.plan.function.Config;
import com.plan.function.DataOperate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SetPosition extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private String account;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }


    private String token;
    private String my_position;


    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("SetPosition:" + account + "," + token + "," + my_position);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOperate dataop = new DataOperate();
            boolean istoken = Config.CheckToken(dataop, account, token);
            if (istoken) {//token正確
                List it = dataop.SelectTb("from UserEntity as user where user.account =:para1", account);
                if (it.size() == 1) {
                    UserEntity user = (UserEntity) it.get(0);
                    JSONObject jsonObject = new JSONObject(my_position);
                    user.setLat(jsonObject.getDouble("lat"));
                    user.setLon(jsonObject.getDouble("lon"));
                    dataop.UpdataTb(user);
                } else
                    obj.put("status", 0);
            } else
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

    public String getMy_position() {
        return my_position;
    }

    public void setMy_position(String my_position) {
        this.my_position = my_position;
    }
}