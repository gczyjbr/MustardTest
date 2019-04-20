package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.msg.*;
import com.lsh.mustardtest.pojo.*;
import com.lsh.mustardtest.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 刘森华
 * 2019/04/18
 */

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
     *
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
        } else {
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
     *
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
        } else {
            accountMsg.setUser(user);
            accountMsg.setFlag(true);
            accountMsg.setMessage("登录成功");
            System.out.println("登录成功");
        }
        return accountMsg;
    }

    /**
     * 修改密码
     *
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
        } else {
            User user = userService.get(id);
            if (null == user) {
                System.out.println("账户不存在");
                return OBJECT_IS_NULL;
            } else {
                user.setPassword(password);
                userService.update(user);
                System.out.println("修改密码成功");
                return SUCCESS;
            }
        }

    }

    /**
     * 编辑用户信息
     *
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
        } else {
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
     *
     * @param request http请求
     * @return User对象
     * @throws Exception
     */
    @RequestMapping("userInformation")
    @ResponseBody
    public User userInformation(HttpServletRequest request) throws Exception {
        Integer id = Integer.valueOf(request.getParameter("id"));
        System.out.println("userID: " + id);
        if (null == id) {
            System.out.println("userInformation.id为空");
            return null;
        } else {
            System.out.println("获取用户信息成功");
            return userService.get(id);
        }
    }

    @RequestMapping("myProducts")
    @ResponseBody
    public List<ProductMsg> myProducts(HttpServletRequest request) throws Exception {
        Integer userID = Integer.valueOf(request.getParameter("userID"));
        System.out.println("userID: " + userID);
        if (null == userID) {
            System.out.println("userID为空");
            return null;
        } else {
            List<Product> products = productService.myProducts(userID);
            if (products.isEmpty()) {
                System.out.println("该用户还未租用产品");
                return null;
            } else {
                List<ProductMsg> list = new ArrayList<>();
                for (Product p : products)
                    list.add(productDetail(p));
                System.out.println("获取我的产品列表成功");
                return list;
            }
        }
    }

    /** 产品管理 **/

    /**
     * 产品列表
     *
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
        return productService.usableProducts(wareHouseID);
    }

    /**
     * @param p Product对象
     * @return ProductMsg对象
     * @throws Exception
     */
    private ProductMsg productDetail(Product p) throws Exception {

        List<ProductImage> productSingleImages = productImageService.list(p.getId(), ProductImageService.type_single);
        List<ProductImage> productDetailImages = productImageService.list(p.getId(), ProductImageService.type_detail);
        p.setProductSingleImages(productSingleImages);
        p.setProductDetailImages(productDetailImages);
        List<PropertyValue> pvs = propertyValueService.list(p.getId());


        ProductMsg productMsg = new ProductMsg();
        productMsg.setProduct(p);
        productMsg.setPropertyValues(pvs);

        System.out.println("productID: " + p.getId());
        System.out.println("获取产品信息成功");

        return productMsg;
    }

    /** 订单管理 **/


    /**
     * 获取订单
     *
     * @param request HttpRequest对象
     * @return list 订单列表
     * @throws Exception
     */
    @RequestMapping("getOrder")
    @ResponseBody
    public List<Order> getOrder(HttpServletRequest request) throws Exception {
        Integer userID = Integer.valueOf(request.getParameter("userID"));
        String status = request.getParameter("status");
        System.out.println("userID: " + userID);
        System.out.println("status: " + status);
        if (null == userID) {
            System.out.println("userID为空");
            return null;
        } else if (status == null) {
            System.out.println("status: " + status);
            return null;
        } else {
            List list = orderService.list(userID, status);
            System.out.println("获取" + status + "状态的订单成功");
            return list;
        }
    }

    /**
     * 我的订单
     * @param request HttpRequest对象
     * @return 订单列表
     */
    @RequestMapping("myOrders")
    @ResponseBody
    public List<Order> myOrders(HttpServletRequest request) {
        Integer userID = Integer.valueOf(request.getParameter("userID"));
        if (null == userID) {
            System.out.println("userID为空");
            return null;
        }
        else {
            List list = orderService.list(userID);
            System.out.println("获取订单成功");
            return list;
        }
    }

    /**
     * 创建订单
     *
     * @param orderMsg OrderMSG对象
     * @throws Exception
     */
    @RequestMapping(value = "createOrder", method = RequestMethod.POST)
    @ResponseBody
    public int createOrder(@RequestBody OrderMsg orderMsg) throws Exception {
        System.out.println("orderCode: " + orderMsg.getOrderCode());
        if (null == orderMsg.getOrderCode())
            return ID_IS_NULL;
        else {
            Order order = new Order();
            order.setOrderCode(orderMsg.getOrderCode());
            order.setAddress(orderMsg.getAddress());
            order.setPost(orderMsg.getPost());
            order.setUserMessage(orderMsg.getUserMessage());
            order.setUserID(orderMsg.getUserID());
            order.setSender(order.getSender());
            order.setEndDate(orderMsg.getEndDate());
            order.setDuration(orderMsg.getDuration());
            order.setCreateDate(orderMsg.getCreateDate());
            order.setStatus(orderMsg.getStatus());
            order.setConfirmDate(orderMsg.getConfirmDate());
            orderService.add(order);
            System.out.println("创建订单成功");
            return SUCCESS;
        }
    }

    /**
     * 改变订单状态
     *
     * @param requestAlterState RequestAlterState对象
     * @throws Exception
     */
    @RequestMapping(value = "changeOrderStatus", method = RequestMethod.POST)
    @ResponseBody
    public int changeOrderStatus(@RequestBody RequestAlterState requestAlterState) throws Exception {
        System.out.println("orderCode: " + requestAlterState.getOrderCode());
        if (null == requestAlterState.getOrderCode())
            return ID_IS_NULL;
        else {
            Order order = orderService.get(requestAlterState.getOrderCode());
            String status = requestAlterState.getStatus();
            WarehouseNumBean warehouseNumBean = requestAlterState.getWarehouseNumBean();
            if (null == order || null == status || null == warehouseNumBean)
                return OBJECT_IS_NULL;
            else if (OrderService.waitConfirm == status) {
                processOrder(order, warehouseNumBean);
            }
            order.setStatus(status);
            orderService.update(order);
            System.out.println("更改订单状态成功");
            return SUCCESS;
        }
    }

    /**
     * 续期
     *
     * @param requestRenew RequestRenew对象
     * @throws Exception
     */
    @RequestMapping(value = "Renewal", method = RequestMethod.POST)
    @ResponseBody
    public int Renewal(@RequestBody RequestRenew requestRenew) throws Exception {
        Integer productID = requestRenew.getProductID();
        System.out.println("productID: " + productID);
        if (null == productID)
            return ID_IS_NULL;
        Product p = productService.get(productID);
        Integer userID = p.getUserID();
        List list = orderService.list(userID, OrderService.finish);
        Order historyOrder = (Order) list.get(list.size() - 1);
        if (null == p || null == historyOrder)
            return OBJECT_IS_NULL;
        Order order = new Order();
        order.setSender(historyOrder.getSender());
        order.setMobile(historyOrder.getMobile());
        order.setUserID(userID);
        order.setUserMessage(historyOrder.getUserMessage());
        order.setPost(historyOrder.getPost());
        order.setAddress(historyOrder.getAddress());
        order.setOrderCode(requestRenew.getOrderCode());
        order.setCreateDate(requestRenew.getCreateDate());
        order.setPayDate(requestRenew.getCreateDate());
        order.setDuration(requestRenew.getDuration());
        order.setEndDate(requestRenew.getEndDate());
        orderService.add(order);
        System.out.println("创建订单成功");

        OrderItem oi = new OrderItem();
        oi.setProductID(productID);
        oi.setUserID(userID);
        oi.setOrderID(order.getId());
        orderItemService.add(oi);
        System.out.println("创建订单项成功");
        System.out.println("续期成功");
        return SUCCESS;
    }

    /**
     * 处理订单
     *
     * @param order            Order对象
     * @param warehouseNumBean WarehouseNumBean对象
     * @throws Exception
     */
    private void processOrder(Order order, WarehouseNumBean warehouseNumBean) throws Exception {
        Integer wareHouseID = warehouseNumBean.getWareHouseID();
        int numSS = warehouseNumBean.getNumSS();
        int numS = warehouseNumBean.getNumS();
        int numM = warehouseNumBean.getNumM();
        int numL = warehouseNumBean.getNumL();

        WareHouse w = wareHouseService.get(wareHouseID);
        int tiny_stock = w.getTiny_stock();
        int small_stock = w.getSmall_stock();
        int middle_stock = w.getMiddle_stock();
        int big_stock = w.getBig_stock();

        if (0 != numSS) {
            if (w.getTiny_stock() < numSS)
                System.out.println("微仓库存不足");
            else {
                processOrder(order, warehouseNumBean, "微型");
                w.setTiny_stock(tiny_stock - numSS);
            }
        }

        if (0 != numS) {
            if (w.getSmall_stock() < numS)
                System.out.println("小仓库存不足");
            else {
                processOrder(order, warehouseNumBean, "小型");
                w.setSmall_stock(small_stock - numS);
            }

        }

        if (0 != numM) {
            if (w.getMiddle_stock() < numM)
                System.out.println("中仓库存不足");
            else {
                processOrder(order, warehouseNumBean, "中型");
                w.setMiddle_stock(middle_stock - numM);
            }
        }

        if (0 != numL) {
            if (w.getBig_stock() < numL)
                System.out.println("大仓库存不足");
            else {
                processOrder(order, warehouseNumBean, "大型");
                w.setBig_stock(big_stock - numL);
            }
        }
        wareHouseService.update(w);
        System.out.println("处理订单成功");

    }

    /**
     * 处理订单
     *
     * @param order            Order对象
     * @param warehouseNumBean WarehouseNumBean对象
     * @param type             类型
     * @throws Exception
     */
    private void processOrder(Order order, WarehouseNumBean warehouseNumBean, String type) throws Exception {
        List products = productService.productsByType(warehouseNumBean.getWareHouseID(), type);
        int j = selector(type, warehouseNumBean);
        Product p;
        for (int i = 0; i < j; i++) {
            p = (Product) products.get(i);
            p.setUsed(true);
            p.setUserID(order.getUserID());
            productService.update(p);
            OrderItem oi = new OrderItem();
            oi.setProductID(p.getId());
            oi.setUserID(order.getUserID());
            oi.setOrderID(order.getId());
            oi.setNumber(1);
            orderItemService.add(oi);
        }
        System.out.println("更改产品及创建订单项成功");
    }

    /**
     * 选择器
     *
     * @param type             类型
     * @param warehouseNumBean WareHouseBean对象
     * @return
     * @throws Exception
     */
    private int selector(String type, WarehouseNumBean warehouseNumBean) throws Exception {
        switch (type) {
            case "微型":
                return warehouseNumBean.getNumSS();
            case "小型":
                return warehouseNumBean.getNumS();
            case "中型":
                return warehouseNumBean.getNumM();
            case "大型":
                return warehouseNumBean.getNumL();
            default:
                return 0;
        }
    }
}
