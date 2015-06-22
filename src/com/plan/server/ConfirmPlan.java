package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.PeopleInPlanEntity;
import com.plan.data.PlanEntity;
import com.plan.function.Config;
import com.plan.function.DataOperate;
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
            DataOperate dataop = new DataOperate();
            boolean istoken = Config.CheckToken(dataop, account, token);
            if (istoken) {//token正確
                String hql = "from PlanEntity pe where pe.planId=:para1";
                List list = dataop.SelectTb(hql,plan_id);
                if (list.size()==1){
                    PlanEntity pe = (PlanEntity) list.get(0);
                    long thisTime = System.currentTimeMillis();//ms TODO 客户端一般存的是ms还是s
                    //判断是否可以设置时间地点
                    hql = "from PeopleInPlanEntity as pp where pp.planId=:para1";
                    List listPeople = dataop.SelectTb(hql,plan_id);
                    for (int i=0;i<listPeople.size();i++){
                        PeopleInPlanEntity pp = (PeopleInPlanEntity) listPeople.get(i);
                        if (pp.getReturnTime()==null||pp.getReturnTime()==-1) {
                            obj.put("status", 3);
                            ret = obj.toString();
                            PrintToHtml.PrintToHtml(response, ret);
                            return null;
                        }
                    }
                    if (pe.getTime()==null&&pe.getLocation()==null){
                        JSONObject jsonObject = new JSONObject(location);
                        pe.setTime(time);
                        pe.setLocation(jsonObject.getString("location"));
                        pe.setLocationLat(jsonObject.getDouble("lat"));
                        pe.setLocationLon(jsonObject.getDouble("lon"));
                        dataop.UpdataTb(pe);
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