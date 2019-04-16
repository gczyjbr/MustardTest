package com.lsh.mustardtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.ProductService;
import com.lsh.mustardtest.service.WareHouseService;
import com.lsh.mustardtest.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 刘森华
 * 2019/04/12
 */

@Controller
@RequestMapping("")
public class ProductController {
    @Autowired
    WareHouseService wareHouseService;
    @Autowired
    ProductService productService;

    /**
     * @param model 渲染模型
     * @param p     Product对象
     * @return 重定型url(admin_product_list)，即list方法映射的路径
     */
    @RequestMapping("admin_product_add")
    public String add(Model model, Product p) {
        productService.add(p);
//        System.out.println("ProductController.add.wareHouseID: " + p.getWareHouseID());
        return "redirect:admin_product_list?warehouseID=" + p.getWareHouseID();
    }

    /**
     * @param id product id
     * @return 重定向url(admin_product_list)，即list方法映射的路径
     */
    @RequestMapping("admin_product_delete")
    public String delete(Integer id) {
        Product p = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?warehouseID=" + p.getWareHouseID();
    }

    /**
     * @param model 渲染模型
     * @param id    product id
     * @return jsp文件路径，具体路径为classpath:/jsp//admin/editProduct.jsp
     */
    @RequestMapping("admin_product_edit")
    public String edit(Model model, Integer id) {
        Product p = productService.get(id);
        WareHouse w = wareHouseService.get(p.getWareHouseID());
        p.setWareHouse(w);
        model.addAttribute("p", p);
        return "admin/editProduct";
    }

    /**
     * @param p Product对象
     * @return 重定向url(admin_product_list)，即list方法映射的路径
     */
    @RequestMapping("admin_product_update")
    public String update(Product p) {
        productService.update(p);
        return "redirect:admin_product_list?warehouseID=" + p.getWareHouseID();
    }

    /**
     * @param warehouseID
     * @param model       渲染模型
     * @param page        Page对象
     * @return jsp文件路径，具体路径为classpath:/jsp/admin/listProduct.jsp
     */
    @RequestMapping("admin_product_list")
    public String list(Integer warehouseID, Model model, Page page) {
        WareHouse w = wareHouseService.get(warehouseID);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> ps = productService.list(warehouseID);

        setPage(page, w, ps);
        setModel(model, page, w, ps);

        return "admin/listProduct";
    }

    /**
     * @param model 渲染模型
     * @param page  Page对象
     * @param w     Warehouse对象
     * @param ps    product列表
     */
    private void setModel(Model model, Page page, WareHouse w, List<Product> ps) {
        model.addAttribute("ps", ps);
        model.addAttribute("w", w);
        model.addAttribute("page", page);
    }

    /**
     * @param page Page对象
     * @param w    WareHouse对象
     * @param ps   product对象列表
     */
    private void setPage(Page page, WareHouse w, List<Product> ps) {
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&warehouseID=" + w.getId());
    }

}
