package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.LocationOfPlanEntity;
import com.plan.data.PeopleInPlanEntity;
import com.plan.data.TimeOfPlanEntity;
import com.plan.function.CheckToken;
import com.plan.function.DataOpetate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ReturnPlan extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private String account;
    private String token;
    private String plan_id;
    private long time;
    private String location_list;
    private String time_list;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    public String execute() {
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                String hql = "from PeopleInPlanEntity pe where pe.planId=" + plan_id;
                List list = dataOpetate.SelectTb(hql);
                if (list.size() == 1) {
                    PeopleInPlanEntity pe = (PeopleInPlanEntity) list.get(0);
                    pe.setReturnTime(time);
                    dataOpetate.Save(pe);
                }else {
                    obj.put("status",0);
                    PrintToHtml.PrintToHtml(response, ret);
                    return null;
                }
                hql = "from LocationOfPlanEntity pe where pe.planId=" + plan_id;
                list = dataOpetate.SelectTb(hql);
                for (int i = 0; i < list.size(); i++) {
                    LocationOfPlanEntity pe = (LocationOfPlanEntity) list.get(i);
                    if (location_list.contains("\""+pe.getLocation()+"\"")) {
                        pe.setNumber(pe.getNumber() + 1);
                        dataOpetate.Save(pe);
                    }
                }
                hql = "from TimeOfPlanEntity pe where pe.planId=" + plan_id;
                list = dataOpetate.SelectTb(hql);
                for (int i = 0; i < list.size(); i++) {
                    TimeOfPlanEntity pe = (TimeOfPlanEntity) list.get(0);
                    if (time_list.contains("\""+pe.getTime()+"\"")){
                        pe.setNumber(pe.getNumber()+1);
                        dataOpetate.Save(pe);
                    }
                }
                obj.put("status",1);
            }else {
                obj.put("status",0);
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

    public String getLocation_list() {
        return location_list;
    }

    public void setLocation_list(String location_list) {
        this.location_list = location_list;
    }

    public String getTime_list() {
        return time_list;
    }

    public void setTime_list(String time_list) {
        this.time_list = time_list;
    }


}