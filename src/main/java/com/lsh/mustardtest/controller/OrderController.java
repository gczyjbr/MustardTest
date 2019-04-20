package com.lsh.mustardtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.mustardtest.pojo.Order;
import com.lsh.mustardtest.pojo.OrderItem;
import com.lsh.mustardtest.service.OrderItemService;
import com.lsh.mustardtest.service.OrderService;
import com.lsh.mustardtest.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderItemService orderItemService;

    /**
     *
     * @param model 渲染模型
     * @param page Page对象
     * @return jsp文件路径，具体路径为classpath:/admin/listOrder.jsp
     */
    @RequestMapping("admin_order_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());

        List<Order> os = orderService.list();

        int total = (int) new PageInfo<>(os).getTotal();
        page.setTotal(total);

        orderItemService.fill(os);

        model.addAttribute("os", os);
        model.addAttribute("page", page);

        return "admin/listOrder";
    }

    /**
     *
     * @param o Order对象
     * @return 重定向url(admin_order_list),即list方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_order_confirm")
    public String confirm(Order o) throws IOException {
        o.setConfirmDate(orderService.date(new Date()));
        o.setStatus(OrderService.finish);
        orderService.update(o);
        return "redirect:admin_order_list";
    }
}
