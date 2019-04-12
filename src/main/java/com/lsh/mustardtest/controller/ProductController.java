package com.lsh.mustardtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.mustardtest.pojo.Category;
import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.CategoryService;
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
    CategoryService categoryService;
    @Autowired
    ProductService productService;
    @Autowired
    WareHouseService wareHouseService;

    @RequestMapping("admin_product_add")
    public String add(Model model, Product p) {
        productService.add(p);
        return "redirect:admin_product_list?categoryID=" + p.getCategoryID();
    }

    @RequestMapping("admin_product_delete")
    public String delete(Integer id) {
        Product p = productService.get(id);
        productService.delete(id);
        return "redirect:admin_product_list?categoryID=" + p.getCategoryID();
    }

    @RequestMapping("admin_product_edit")
    public String edit(Model model, Integer id) {
        Product p = productService.get(id);
        Category c = categoryService.get(p.getCategoryID());
        WareHouse w = wareHouseService.get(p.getWareHouseID());
        p.setCategory(c);
        p.setWareHouse(w);
        model.addAttribute("p", p);
        return "admin/editProduct";
    }

    @RequestMapping("admin_product_update")
    public String update(Product p) {
        productService.update(p);
        return "redirect:admin_product_list?categoryID=" + p.getCategoryID();
    }

    @RequestMapping("admin_product_list")
    public String list(Integer categoryID, Model model, Page page) {
        Category c = categoryService.get(categoryID);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Product> ps = productService.listByCategory(categoryID);

        setPage(page, c, ps);
        setModel(model, page, c, ps);

        return "admin/listProduct";
    }

    /**
     * @param model 渲染模型
     * @param page  Page对象
     * @param c     Category对象
     * @param ps    product列表
     */
    private void setModel(Model model, Page page, Category c, List<Product> ps) {
        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);
    }

    private void setPage(Page page, Category c, List<Product> ps) {
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&categoryID=" + c.getId());
    }

}
