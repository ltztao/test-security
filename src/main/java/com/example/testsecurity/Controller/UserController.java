package com.example.testsecurity.Controller;

import com.example.testsecurity.Model.User;
import com.example.testsecurity.Utils.Result;
import com.example.testsecurity.Service.UserService;
import net.sf.jasperreports.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @Autowired
    RedisOperationsSessionRepository redisOperationsSessionRepository;

    @Autowired
    private JdbcTemplate   jdbcTemplate;
    @Autowired
    private ResourceLoader resourceLoader;

    @RequestMapping(value = "/getAll",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('FIND_USER')")
    public Result<List<User>> getAllUser(){
        Result<List<User>> result = new Result<>();
        try{
            result.setData(userService.findAllUser());
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/getCurrentUser",method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('FIND_CURRENTUSER')")
    public Result<User> getCurrentUser(){
        Result<User> result = new Result<>();
        try{
            result.setData((User)session.getAttribute("user"));
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }

    @RequestMapping(value = "/forceLogout",method = RequestMethod.GET)
    public Result<Void> forceLogout(int userId){
        Result<Void> result = new Result<>();
        try{
            String sessionId = userService.getSesssionIdByUserId(userId);
            redisOperationsSessionRepository.deleteById(sessionId);
        }catch (Exception e){
            result.setSuccess(false);
        }
        return result;
    }
    
}
