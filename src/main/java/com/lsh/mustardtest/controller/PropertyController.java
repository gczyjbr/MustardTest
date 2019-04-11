package com.lsh.mustardtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.mustardtest.pojo.Category;
import com.lsh.mustardtest.pojo.Property;
import com.lsh.mustardtest.service.CategoryService;
import com.lsh.mustardtest.service.PropertyService;
import com.lsh.mustardtest.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 刘森华
 * 2019/04/11
 */

@Controller
@RequestMapping("")
public class PropertyController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;

    /**
     * @param categoryID
     * @param model      渲染模型
     * @param page       分页
     * @return jsp文件路径，具体路径为classpath/admin/listProperty.jsp
     */
    @RequestMapping("admin_property_list")
    public String list(Integer categoryID, Model model, Page page) {
        Category c = categoryService.get(categoryID);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> ps = propertyService.list(categoryID);

        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&categoryID=" + c.getId());

        model.addAttribute("ps", ps);
        model.addAttribute("c", c);
        model.addAttribute("page", page);

        return "admin/listProperty";
    }

    /**
     * @param model 渲染模型
     * @param p     Property对象
     * @return 重定向url(admin_property_list), 即List方法映射的路径
     */
    @RequestMapping("admin_property_add")
    public String add(Model model, Property p) {
        propertyService.add(p);
        return "redirect:admin_property_list?categoryID=" + p.getCategoryID();
    }

    /**
     * @param model 渲染模型
     * @param id    property id
     * @return jsp文件路径，具体路径为classpath/admin/editProperty.jsp
     */
    @RequestMapping("admin_property_edit")
    public String edit(Model model, int id) {
        Property p = propertyService.get(id);
        Category c = categoryService.get(p.getCategoryID());
        p.setCategory(c);
        model.addAttribute("p", p);
        return "admin/editProperty";
    }

    /**
     * @param p property对象
     * @return 重定向url(admin_property_list), 即List方法映射的路径
     */
    @RequestMapping("admin_property_update")
    public String update(Property p) {
        propertyService.update(p);
        return "redirect:admin_property_list?categoryID=" + p.getCategoryID();
    }

    public String delete(int id) {
        Property p = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?categoryID=" + p.getCategoryID();
    }
}
