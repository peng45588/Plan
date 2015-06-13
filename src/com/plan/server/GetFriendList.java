package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.FriendEntity;
import com.plan.data.UserEntity;
import com.plan.function.CheckToken;
import com.plan.function.DataOpetate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetFriendList extends ActionSupport implements ServletResponseAware {
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
        String ret = "";
        JSONObject obj = new JSONObject();
        JSONArray jsarray = new JSONArray();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {//token正確
                String hql = "from UserEntity ue where ue.account=" +
                        "all(select friendAccount from FriendEntity fe where fe.userAccount="+account+")";
                List list = dataOpetate.SelectTb(hql);
                for (int i=0;i<list.size();i++){
                    UserEntity fe = (UserEntity) list.get(i);
                    JSONObject jsob = new JSONObject();
                    jsob.put("account",fe.getAccount());
                    jsob.put("nickname",fe.getNickname());
                    jsob.put("avatag",fe.getAvatag());
                    jsarray.put(jsob);
                }
                obj.put("status",1);
                obj.put("people",jsarray);
            }else {
                obj.put("status", 0);
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


}