package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.LocationOfPlanEntity;
import com.plan.data.PeopleInPlanEntity;
import com.plan.data.PlanEntity;
import com.plan.data.TimeOfPlanEntity;
import com.plan.function.Config;
import com.plan.function.DataOperate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class Start extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private String account;
    private String token;
    private String title;
    private String info;
    private long ddl;
    private String time_list;
    private String location_list;
    private String people;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("Start:"+account+","+token+","+title+","+info+","+ddl+","+time_list+","+location_list+","+people);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOperate dataop = new DataOperate();
            boolean istoken = Config.CheckToken(dataop, account, token);
            if (istoken) {//token正確
                PlanEntity pe = new PlanEntity();
                pe.setTitle(title);
                pe.setDeadline(ddl);
                pe.setInfo(info);

                dataop.Save(pe);
                List list = dataop.SelectTb("from PlanEntity where title = :para1 and info = :para2",title,info);
                if (list.size()==1)
                    pe = (PlanEntity) list.get(0);
                else{//title info 衝突
                    obj.put("status",0);
                    ret = obj.toString();
                    PrintToHtml.PrintToHtml(response, ret);
                    return null;
                }
                //存time location perple 三個表
                JSONArray jsarray = new JSONArray(time_list);
                for (int i = 0;i<jsarray.length();i++){
                    TimeOfPlanEntity tope = new TimeOfPlanEntity();
                    tope.setPlanId(pe.getPlanId());
                    tope.setTime(jsarray.getLong(i));
                    dataop.Save(tope);
                }
                jsarray = new JSONArray(location_list);
                for (int i = 0;i<jsarray.length();i++){
                    JSONObject jsonObject = jsarray.getJSONObject(i);
                    LocationOfPlanEntity lope = new LocationOfPlanEntity();
                    lope.setPlanId(pe.getPlanId());
                    lope.setLocation(jsonObject.getString("location"));
                    lope.setLat(jsonObject.getDouble("lat"));
                    lope.setLon(jsonObject.getDouble("lon"));
                    dataop.Save(lope);
                }
                jsarray = new JSONArray(people);
                for (int i = 0;i<jsarray.length();i++){
                    PeopleInPlanEntity pipe = new PeopleInPlanEntity();
                    pipe.setPlanId(pe.getPlanId());
                    pipe.setAccount(jsarray.getString(i));
                    dataop.Save(pipe);
                }
                obj.put("status",1);
                //for (int i=0;i<)
            }else {
                obj.put("status",2);
            }
        }catch (Exception e){
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public long getDdl() {
        return ddl;
    }

    public void setDdl(long ddl) {
        this.ddl = ddl;
    }

    public String getTime_list() {
        return time_list;
    }

    public void setTime_list(String time_list) {
        this.time_list = time_list;
    }

    public String getLocation_list() {
        return location_list;
    }

    public void setLocation_list(String location_list) {
        this.location_list = location_list;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }


}