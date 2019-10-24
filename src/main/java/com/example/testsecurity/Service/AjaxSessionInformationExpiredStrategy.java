package com.example.testsecurity.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service
public class AjaxSessionInformationExpiredStrategy implements SessionInformationExpiredStrategy {
    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException{
        HttpServletResponse response = event.getResponse();
//        HttpServletRequest request = event.getRequest();
        JSONObject obj = new JSONObject();
//        判断是否为ajax 请求
//        boolean isAjax = request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
        obj.put("code", 401);
        obj.put("msg","登录过期");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().print(obj.toJSONString());
        response.flushBuffer();
    }
}
