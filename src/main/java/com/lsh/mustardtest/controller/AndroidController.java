package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.msg.AccountMsg;
import com.lsh.mustardtest.pojo.User;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("")
public class AndroidController {
    @Autowired
    WareHouseService wareHouseService;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    @Autowired
    ProductImageService service;
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    @RequestMapping("home")
    @ResponseBody
    public List<WareHouse> home() throws Exception{
        List<WareHouse> ws = wareHouseService.list();
        productService.fill(ws);
        return ws;
    }

    @RequestMapping("register")
    @ResponseBody
    public AccountMsg register(HttpServletRequest request) {
        Long phone = (Long) request.getAttribute("phone");
        String password = (String) request.getAttribute("password");
        boolean exist = userService.isExist(phone);
        AccountMsg accountMsg = new AccountMsg();
        User user = new User();

        if (exist) {
            accountMsg.setFlag(false);
            accountMsg.setUser(null);
            accountMsg.setMessage("该号码已被注册");
        }
        else {
            user.setPhone(phone);
            user.setPassword(password);
            userService.add(user);
            accountMsg.setFlag(true);
            accountMsg.setMessage("注册成功");
            accountMsg.setUser(userService.get(phone, password));
        }
        return accountMsg;
    }

    @RequestMapping("login")
    @ResponseBody
    public AccountMsg login(HttpServletRequest request) {
        AccountMsg accountMsg = new AccountMsg();
        Long phone = (Long) request.getAttribute("phone");
        String password = (String) request.getAttribute("password");
        User user = userService.get(phone, password);
        if (null == user) {
            accountMsg.setFlag(false);
            accountMsg.setMessage("该号码未注册");
            accountMsg.setUser(null);
        }
        else {
            accountMsg.setUser(user);
            accountMsg.setFlag(true);
            accountMsg.setMessage("登录成功");
        }
        return accountMsg;
    }
}
