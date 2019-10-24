package com.example.testsecurity.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Service
public class RestAuthAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException e) throws IOException{
        JSONObject obj = new JSONObject();
        obj.put("code", 203);
        obj.put("msg","权限拒绝");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(obj.toJSONString());
    }
}
