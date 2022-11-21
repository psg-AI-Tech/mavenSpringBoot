package com.example.mavenSpringBoot.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.*;
import java.awt.image.BufferedImage;


import com.example.mavenSpringBoot.utils.IdentifyCodeUtils;
import com.example.mavenSpringBoot.entity.User;
import com.example.mavenSpringBoot.api.CommonResult;
// import com.example.mavenSpringBoot.api.*;

// @RestController
// public class LoginController {
 
//     @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
//     public CommonResult login(@RequestBody User user) {
//         if (user.getUsername().equals("admin") && user.getPassword().equals("123456"))
//             return CommonResult.success("admin");
//         else
//             return CommonResult.validateFailed();
//     }
// }


@Controller
public class UserLogin {
    @RequestMapping("/loginShow")
    public String loginShow(){
        return "login.html";
        // return "login";
    }
    @PostMapping("/login")
    public String login(String username,String password,String identifyCode,HttpSession session){
        System.out.println("用户名:"+username);
        System.out.println("密码:"+password);
        System.out.println("验证码:"+identifyCode);
        //从session中取出验证码
        String sessionCode = (String)session.getAttribute("identifyFyCode");
        if (identifyCode.equalsIgnoreCase(sessionCode)){
            System.out.println("验证码正确");
            //进行登录判断的逻辑大家自己写，这里就不演示了
            // return "success";
            // helloWorldHome();
        }else{
            System.out.println("验证码错误");
            //重定向到登录画面
            return "redirect:/loginShow";
        }
         return "";
    }

    @RequestMapping(value = "/admin/login", method = RequestMethod.POST)
    public CommonResult login_vue(@RequestBody User user) {
        if (user.getUsername().equals("admin") && user.getPassword().equals("123456")){

            System.out.println("get request");
            return CommonResult.success("admin");
        }
        else
            return CommonResult.validateFailed();
    }

    /**
     * 给前端返回一个验证码图片
     * @return
     */
    @RequestMapping("/identifyImage")
    public void identifyImage(HttpServletResponse response, HttpSession session){
        //创建随机验证码
        IdentifyCodeUtils utils = new IdentifyCodeUtils();
        String identifyCode = utils.getIdentifyCode();
        //session存入验证码
        session.setAttribute("identifyFyCode", identifyCode);
        //根据验证码创建图片
        BufferedImage identifyImage = utils.getIdentifyImage(identifyCode);
        //回传给前端
        utils.responseIdentifyImg(identifyImage,response);

    }


    @ResponseBody
    public String helloWorldHome(){
        // return "index";
        return "hello world, vscode";
    }
}

