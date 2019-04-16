package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.PropertyValue;
import com.lsh.mustardtest.service.ProductService;
import com.lsh.mustardtest.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("")
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;

    /**
     *
     * @param model 渲染模型
     * @param productID
     * @return jsp文件路径，具体路径为classpath:/admin/editPropertyValue.jsp
     */
    @RequestMapping("admin_propertyValue_edit")
    public String edit(Model model, Integer productID) {
        Product p = productService.get(productID);
        propertyValueService.init(p);
        List<PropertyValue> pvs = propertyValueService.list(p.getId());

        model.addAttribute("p", p);
        model.addAttribute("pvs", pvs);
        return "admin/editPropertyValue";
    }

    @RequestMapping("admin_propertyValue_update")
    @ResponseBody
    public String update(PropertyValue pv) {
        propertyValueService.update(pv);
        return "success";
    }
}
