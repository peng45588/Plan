package com.plan.server;/**
 * Created by snow on 15-5-31.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.LocationOfPlanEntity;
import com.plan.function.Config;
import com.plan.function.DataOperate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetPlanLocation extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private String account;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }


    private String token;
    private int plan_id;


    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("GetPlanLocation:" + account + "," + token + "," + plan_id);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOperate dataop = new DataOperate();
            boolean istoken = Config.CheckToken(dataop, account, token);
            if (istoken) {//token正確
                String hql = "from LocationOfPlanEntity as pe where pe.planId =:para1";
                List it = dataop.SelectTb(hql, plan_id);
                JSONArray jsonArray = new JSONArray();
                for (int i=0;i<it.size();i++){
                    LocationOfPlanEntity lope = (LocationOfPlanEntity) it.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("location",lope.getLocation());
                    jsonObject.put("lat",lope.getLat());
                    jsonObject.put("lon",lope.getLon());
                    jsonArray.put(jsonObject);
                }
                obj.put("status",1);
                obj.put("location_list",jsonArray);
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

    public int getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(int plan_id) {
        this.plan_id = plan_id;
    }
}