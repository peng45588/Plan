package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.FriendEntity;
import com.plan.data.PlanEntity;
import com.plan.function.Config;
import com.plan.function.DataOperate;
import com.plan.function.PrintToHtml;

import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetUserEvent extends ActionSupport implements ServletResponseAware {
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
        System.err.println("GetUserEvent:"+account+","+token);
        String ret = "";
        JSONObject obj = new JSONObject();
        try{
            DataOperate dataop = new DataOperate();
            boolean istoken = Config.CheckToken(dataop, account, token);
            if (istoken) {//token正確
                String hql = "from PlanEntity as pe where pe.account =:para1";
                List list = dataop.SelectTb(hql,account);
                JSONArray jsonArray = new JSONArray();
                for (int i=0;i<list.size();i++){
                    PlanEntity pe = (PlanEntity) list.get(i);
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("plan_id",pe.getPlanId());
                    jsonObject.put("title",pe.getTitle());
                    jsonObject.put("info",pe.getInfo());
                    jsonArray.put(jsonObject);
                }
                obj.put("status",1);
                obj.put("plan_list",jsonArray);
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

}