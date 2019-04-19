package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.msg.AccountMsg;
import com.lsh.mustardtest.msg.ProductMsg;
import com.lsh.mustardtest.pojo.*;
import com.lsh.mustardtest.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
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
    @Autowired
    ProductImageService productImageService;

    private static final int SUCCESS = 100;
    private static final int ID_IS_NULL = 200;
    private static final int OBJECT_IS_NULL = 300;

    @RequestMapping(value = "home")
    @ResponseBody
    public List<WareHouse> home() throws Exception {
        List<WareHouse> ws = wareHouseService.list();
        productService.fill(ws);
        return ws;
    }

    /** 用户管理 **/

    /**
     * 注册
     * @param request http请求
     * @return AccountMsg对象
     * @throws Exception
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public AccountMsg register(HttpServletRequest request) throws Exception {
        Long phone = Long.valueOf(request.getParameter("phone"));
        String password = request.getParameter("password");
        System.out.println("账号: " + phone);
        System.out.println("密码: " + password);

        boolean exist = userService.isExist(phone);
        AccountMsg accountMsg = new AccountMsg();
        User user = new User();

        if (exist) {
            accountMsg.setFlag(false);
            accountMsg.setUser(null);
            accountMsg.setMessage("该号码已被注册");
            System.out.println("该账号已注册");
        }
        else {
            user.setPhone(phone);
            user.setPassword(password);
            userService.add(user);
            accountMsg.setFlag(true);
            accountMsg.setMessage("注册成功");
            accountMsg.setUser(userService.get(phone, password));
            System.out.println("注册成功");
        }
        return accountMsg;
    }

    /**
     * 登录
     * @param request http请求
     * @return AccountMsg对象
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public AccountMsg login(HttpServletRequest request) throws Exception {
        AccountMsg accountMsg = new AccountMsg();
        Long phone = Long.valueOf(request.getParameter("phone"));
        String password = request.getParameter("password");
        System.out.println("账号: " + phone);
        System.out.println("密码: " + password);

        User user = userService.get(phone, password);
        if (null == user) {
            accountMsg.setFlag(false);
            accountMsg.setMessage("该号码未注册");
            accountMsg.setUser(null);
            System.out.println("该号码未注册");
        }
        else {
            accountMsg.setUser(user);
            accountMsg.setFlag(true);
            accountMsg.setMessage("登录成功");
            System.out.println("登录成功");
        }
        return accountMsg;
    }

    /**
     * 修改密码
     * @param request http请求
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public int updatePassword(HttpServletRequest request) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String password = request.getParameter("password");
        if (null == id) {
            System.out.println("updatePassword.id为空");
            return ID_IS_NULL;
        }
        else {
            User user = userService.get(id);
            if (null == user) {
                System.out.println("账户不存在");
                return OBJECT_IS_NULL;
            }
            else {
                user.setPassword(password);
                userService.update(user);
                System.out.println("修改密码成功");
                return SUCCESS;
            }
        }

    }

    /**
     * 编辑用户信息
     * @param user User对象
     * @throws Exception
     */
    @RequestMapping(value = "editUser", method = RequestMethod.POST)
    @ResponseBody
    public int editUser(@RequestBody User user) throws Exception {
        System.out.println("id: " + user.getId());
        System.out.println("性别: " + user.getSex());
        System.out.println("姓名: " + user.getUserName());
        if (null == user.getId()) {
            System.out.println("editUser.id为空");
            return ID_IS_NULL;
        }
        else {
            if (null == userService.get(user.getId())) {
                System.out.println("账户不存在");
                return OBJECT_IS_NULL;
            } else {
                System.out.println("编辑个人资料成功");
                userService.update(user);
                return SUCCESS;
            }
        }
    }

    /**
     * 用户信息
     * @param request http请求
     * @return User对象
     * @throws Exception
     */
    @RequestMapping("userInformation")
    @ResponseBody
    public User userInformation(HttpServletRequest request) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        System.out.println("id: " + id);
        System.out.println("获取用户信息成功");
        return userService.get(id);
    }

    /** 产品管理 **/

    /**
     * 产品列表
     * @param request
     * @return 产品列表
     * @throws Exception
     */
    @RequestMapping("products")
    @ResponseBody
    public List<Product> products(HttpServletRequest request) throws Exception {
        Integer wareHouseID = Integer.valueOf(request.getParameter("wareHouseID"));
        System.out.println("wareHouseID: " + wareHouseID);
        System.out.println("获取产品列表成功");
        return  productService.usableProducts(wareHouseID);
    }

    /**
     *
     * @param request http请求
     * @return ProductMsg对象
     * @throws Exception
     */
    @RequestMapping("product")
    @ResponseBody
    public ProductMsg product(HttpServletRequest request) throws Exception {
        Integer productID = Integer.valueOf(request.getParameter("productID"));
        Product p = productService.get(productID);

        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.type_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);
        List<PropertyValue> pvs = propertyValueService.list(p.getId());


        ProductMsg productMsg = new ProductMsg();
        productMsg.setProduct(p);
        productMsg.setPropertyValues(pvs);

        System.out.println("productID: " + productID);
        System.out.println("获取产品信息成功");

        return productMsg;
    }

    /** 订单管理 **/
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    @ResponseBody
    public Order createOrder(@RequestBody Order order, HttpServletRequest request) throws Exception {
        Integer wareHouseID = 0;
        int numSS = 0;
        int numS = 0;
        int numM = 0;
        int numL = 0;
        OrderItem oi = new OrderItem();

        WareHouse w = wareHouseService.get(wareHouseID);

        if (numSS != 0) {
            if (w.getTiny_stock() < numSS)
                System.out.println("微仓库存不足");
            else {
                List products = productService.productsByType(wareHouseID, "小型");
                Product p;
                for (int i = 0; i < numSS; i++) {
                    p = (Product) products.get(i);
                    p.setUsed(true);
                    p.setUserID(order.getUserID());
                    productService.update(p);
                    oi.setProductID(p.getId());
                    oi.setUserID(order.getUserID());
                    oi.setOrderID(order.getId());
                }
            }
        }
            List<Product> products = productService.list(wareHouseID);
        return new Order();
    }
}
