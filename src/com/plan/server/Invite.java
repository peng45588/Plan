package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.PlanEntity;
import com.plan.function.Config;
import com.plan.function.DataOpetate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Invite extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private String account;
    private String token;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("Invite:"+account+","+token);
        String ret = "";
        JSONObject obj = new JSONObject();
        JSONArray jsarray = new JSONArray();
        try {
            DataOpetate dataOpetate = (DataOpetate) Config.getInstance().getBean("dataop");
            boolean istoken = Config.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                String hql = "from PlanEntity p where p.planId = " +
                        "any(select planId from PeopleInPlanEntity pe where pe.account=:para1" +
                        " and pe.returnTime is null)";
                List list = dataOpetate.SelectTb(hql,account);
                //TODO person 不知道該怎麼存與傳
                for (int i = 0; i < list.size(); i++) {
                    PlanEntity pe = (PlanEntity) list.get(i);
                    JSONObject jsob = new JSONObject();
                    jsob.put("title", pe.getTitle());
                    jsob.put("info", pe.getInfo());
                    jsob.put("time", pe.getTime());
                    jsob.put("location", pe.getLocation());
                    jsob.put("plan_id", pe.getPlanId());
                    jsarray.put(jsob);
                }
                obj.put("status", 1);
                obj.put("planlist", jsarray);
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


}