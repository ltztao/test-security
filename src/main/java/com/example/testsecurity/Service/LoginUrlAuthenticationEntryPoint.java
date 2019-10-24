package com.example.testsecurity.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class LoginUrlAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Performs the redirect (or forward) to the login form URL.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException{
        // redirect to login page. Use https if forceHttps true
        JSONObject obj = new JSONObject();
        obj.put("code", 203);
        obj.put("msg","未登录");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(obj.toJSONString());// 返回 JSON 信息
        response.flushBuffer();
    }
}
