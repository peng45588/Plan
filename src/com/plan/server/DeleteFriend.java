package com.plan.server;/**
 * Created by snow on 15-6-7.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.FriendEntity;
import com.plan.function.CheckToken;
import com.plan.function.DataOpetate;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

public class DeleteFriend extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;
    private HttpServletResponse response;
    private String account;
    private String token;
    private String friend_account;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    public String execute() {
        System.err.println("DeleteFriend:"+account+","+token+","+friend_account);
        String ret = "";
        JSONObject obj = new JSONObject();
        try {
            DataOpetate dataOpetate = new DataOpetate();
            boolean istoken = CheckToken.CheckToken(dataOpetate, account, token);
            if (istoken) {
                FriendEntity fe = new FriendEntity();
                fe.setUserAccount(account);
                fe.setFriendAccount(friend_account);
                dataOpetate.Delete(fe);
                fe.setUserAccount(friend_account);
                fe.setFriendAccount(account);
                dataOpetate.Delete(fe);
                obj.put("status", 1);
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

    public String getFriend_account() {
        return friend_account;
    }

    public void setFriend_account(String friend_account) {
        this.friend_account = friend_account;
    }


}