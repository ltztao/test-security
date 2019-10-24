package com.example.testsecurity.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class MyAuthenticationFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request,
                                        HttpServletResponse response,
                                        AuthenticationException exception) throws IOException{

        JSONObject obj = new JSONObject();
        obj.put("code", 401.1);
        obj.put("msg","登录失败");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(obj.toJSONString());// 返回 JSON 信息
        response.flushBuffer();
    }
}
