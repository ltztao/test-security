package com.example.testsecurity.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Service
public class MyLogoutSuccessHandler implements LogoutSuccessHandler{
    @Autowired
    HttpSession session;
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException {
        // 清除session
        session.invalidate();

        JSONObject obj = new JSONObject();
        obj.put("code", 200);
        obj.put("msg","登出成功");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(obj.toJSONString());
    }
}
