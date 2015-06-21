package com.plan.server;/**
 * Created by snow on 15-5-31.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.PlanEntity;
import com.plan.data.UserEntity;
import com.plan.function.Config;
import com.plan.function.DataOperate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SelectPosition extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private String account;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }


    private String token;
    private int plan_id;
    private String location_list;


    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("SelectPosition:" + account + "," + token + "," + plan_id + "," + location_list);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOperate dataop = new DataOperate();
            boolean istoken = Config.CheckToken(dataop, account, token);
            if (istoken) {//token正確
                List it = dataop.SelectTb("from PlanEntity as pe where pe.planId =:para1", plan_id);
                if (it.size() == 1) {
                    PlanEntity pe = (PlanEntity) it.get(0);
                    JSONObject jsonObject = new JSONObject(location_list);
                    pe.setLocation(jsonObject.getString("location"));
                    pe.setLocationLat(jsonObject.getDouble("lat"));
                    pe.setLocationLon(jsonObject.getDouble("lon"));
                    dataop.UpdataTb(pe);
                    obj.put("status", 1);
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

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }

    public String getLocation_list() {
        return location_list;
    }

    public void setLocation_list(String location_list) {
        this.location_list = location_list;
    }
}