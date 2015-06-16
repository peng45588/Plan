package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.PlanEntity;
import com.plan.function.CheckToken;
import com.plan.function.DataOpetate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ConfirmPlan extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private String account;
    private String token;
    private String plan_id;
    private long time;
    private String location;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("ConfirmPlan:"+account+","+token+","+plan_id+","+time+","+location);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                String hql = "from PlanEntity pe where pe.planId="+plan_id;
                List list = dataOpetate.SelectTb(hql);
                if (list.size()==1){
                    PlanEntity pe = (PlanEntity) list.get(0);
                    if (pe.getTime()==null&&pe.getLocation()==null){
                        pe.setTime(time);
                        pe.setLocation(location);
                        dataOpetate.UpdataTb(pe);
                        obj.put("status",1);
                    }else {
                        obj.put("status",0);
                    }
                }else {
                    obj.put("status",0);
                }
            }else {
                obj.put("status",2);
            }
        }catch (Exception e){
            try {
                obj.put("status",0);
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

    public String getPlan_id() {
        return plan_id;
    }

    public void setPlan_id(String plan_id) {
        this.plan_id = plan_id;
    }


    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}