package com.plan.server;/**
 * Created by snow on 15-5-31.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.data.FriendEntity;
import com.plan.data.UserEntity;
import com.plan.function.DataOperate;
import com.plan.function.PrintToHtml;
import com.plan.function.UploadPhoto;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

public class Register extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private String account;
    private String password_md5;
    private String nickname;
    private String phone;
    private String avatag;


    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }

    //定义处理用户请求的execute方法
    public String execute() {
        String ret = "";
        String avatagUrl = UploadPhoto.UploadPhoto(avatag, account);
        System.err.println("Register:" + account + "," + password_md5 + "," + nickname + "," + phone + "," + avatag + "," + avatagUrl);
        UserEntity user = new UserEntity();
        user.setAccount(account);
        user.setPassword(password_md5);
        user.setNickname(nickname);
        user.setAvatag(avatagUrl);
        user.setPhone(phone);
        FriendEntity fe = new FriendEntity();
        fe.setFriendAccount(account);
        fe.setUserAccount(account);
        JSONObject obj = new JSONObject();
        try {
            DataOperate dataop = new DataOperate();
            dataop.Save(user);
            dataop.Save(fe);
            obj.put("status", 1);
        } catch (Exception e) {
            try {
                System.err.println(e + "\nshfe");
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

    public String getPassword_md5() {
        return password_md5;
    }

    public void setPassword_md5(String password_md5) {
        this.password_md5 = password_md5;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatag() {
        return avatag;
    }

    public void setAvatag(String avatag) {
        this.avatag = avatag;
    }


}