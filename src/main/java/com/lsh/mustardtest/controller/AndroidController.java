package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.pojo.ProductImage;
import com.lsh.mustardtest.pojo.User;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;
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
    public @ResponseBody List<WareHouse> home() throws Exception{
        List<WareHouse> ws = wareHouseService.list();
        productService.fill(ws);
        return ws;
    }

    @RequestMapping("register")
    public @ResponseBody String register(User user) {
        Long phone = user.getPhone();
        boolean exist = userService.isExist(phone);
        String msg = "";

        if (exist) {
            msg = "该号码已经被注册，不能使用";
            return msg;
        }
        userService.add(user);
        msg = "注册成功";
        return msg;
    }

}
