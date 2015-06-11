package com.plan.server;/**
 * Created by snow on 15-5-31.
 */

import com.opensymphony.xwork2.ActionSupport;
import com.plan.function.PrintToHtml;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;

public class Login extends ActionSupport implements ServletResponseAware {
    private static final long serialVersionUID = 1L;

    private HttpServletResponse response;
    private String account;

    @Override
    public void setServletResponse(HttpServletResponse httpServletResponse) {
        this.response = httpServletResponse;
    }
    //private String password_md5;

    //定义处理用户请求的execute方法
    public String execute() {
        String ret = "account:" + account + ",";
        System.err.println(ret);
        JSONObject obj = new JSONObject();
        //ret = obj.toString();
        PrintToHtml.PrintToHtml(response, ret);
        return null;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

//    public String getPassword_md5() {
//        return password_md5;
//    }
//
//    public void setPassword_md5(String password_md5) {
//        this.password_md5 = password_md5;
//    }

}