package xu.gateway.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/** 
 * @Description: 搭配 SecurityTestConfig 配置类，返回thymeleaf视图
 * @Author: xuyujun
 * @Date: 2022/5/17 
 */ 
@Controller
public class SecurityTestController {

    @RequestMapping(value = {"/home", "/"})
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/success")
    public String success() {
        return "success";
    }

    @RequestMapping(value = "/failure")
    public String failure() {
        return "failure";
    }

    @RequestMapping("/info")
    @ResponseBody
    public String info() {
        String userDetails;
//        使用SecurityContextHolder.getContext().getAuthentication().getPrincipal();获取当前的登录信息
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            userDetails = ((UserDetails) principal).getUsername();
        } else {
            userDetails = principal.toString();
        }
        return userDetails;
    }
}
