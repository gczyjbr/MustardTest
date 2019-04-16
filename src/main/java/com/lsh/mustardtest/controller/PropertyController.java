package com.lsh.mustardtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.mustardtest.pojo.Property;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.PropertyService;
import com.lsh.mustardtest.service.WareHouseService;
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
    WareHouseService wareHouseService;
    @Autowired
    PropertyService propertyService;

    /**
     * @param warehouseID
     * @param model      渲染模型
     * @param page       Page对象
     * @return jsp文件路径，具体路径为classpath/admin/listProperty.jsp
     */
    @RequestMapping("admin_property_list")
    public String list(Integer warehouseID, Model model, Page page) {
        System.out.println("PropertyController.list.warehouseID: " + warehouseID);
        WareHouse w = wareHouseService.get(warehouseID);

        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<Property> ps = propertyService.list(warehouseID);

        setPage(page, w, ps);

        setModel(model, page, w, ps);

        return "admin/listProperty";
    }

    /**
     * @param model 渲染模型
     * @param page  Page对象
     * @param w     WareHouse对象
     * @param ps    property列表
     */
    private void setModel(Model model, Page page, WareHouse w, List<Property> ps) {
        model.addAttribute("ps", ps);
        model.addAttribute("w", w);
        model.addAttribute("page", page);
    }

    /**
     * @param page Page对象
     * @param w    WareHouse对象
     * @param ps   property列表
     */
    private void setPage(Page page, WareHouse w, List<Property> ps) {
        int total = (int) new PageInfo<>(ps).getTotal();
        page.setTotal(total);
        page.setParam("&warehouseID=" + w.getId());
    }

    /**
     * @param model 渲染模型
     * @param p     Property对象
     * @return 重定向url(admin_property_list), 即List方法映射的路径
     */
    @RequestMapping("admin_property_add")
    public String add(Model model, Property p) {
        propertyService.add(p);
        return "redirect:admin_property_list?warehouseID=" + p.getWarehouseID();
    }

    /**
     * @param model 渲染模型
     * @param id    property id
     * @return jsp文件路径，具体路径为classpath/admin/editProperty.jsp
     */
    @RequestMapping("admin_property_edit")
    public String edit(Model model, Integer id) {
        Property p = propertyService.get(id);
        WareHouse w = wareHouseService.get(p.getWarehouseID());
        p.setWareHouse(w);
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
        return "redirect:admin_property_list?warehouseID=" + p.getWarehouseID();
    }

    /**
     *
     * @param id property id
     * @return 重定向url(admin_property_list),即list方法映射的路径
     */
    @RequestMapping("admin_property_delete")
    public String delete(Integer id) {
        Property p = propertyService.get(id);
        propertyService.delete(id);
        return "redirect:admin_property_list?warehouseID=" + p.getWarehouseID();
    }
}
