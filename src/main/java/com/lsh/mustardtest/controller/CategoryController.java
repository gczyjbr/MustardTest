package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.pojo.Category;
import com.lsh.mustardtest.service.CategoryService;
import com.lsh.mustardtest.util.ImageUtil;
import com.lsh.mustardtest.util.Page;
import com.lsh.mustardtest.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    /**
     * @param model 渲染模型
     * @param page  分页
     * @return jsp文件路径，具体路径为classpath/admin/listCategory.jsp
     */
    @RequestMapping("admin_category_list")
    public String List(Model model, Page page) {
        List<Category> cs = categoryService.list(page);
        int total = categoryService.total();
        page.setTotal(total);
        model.addAttribute("cs", cs);
        return "admin/listCategory";
    }

    /**
     * @param c                 Category对象
     * @param session           HttpSession（会话）对象，
     * @param uploadedImageFile 上传的图片文件
     * @return 重定向url(admin_category_list), 即List方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_category_add")
    public String add(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(c.getId());
        categoryService.add(c);
        System.out.println(c.getId());
        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, c.getId() + ".jpg");
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);
        uploadedImageFile.getImage().transferTo(file);
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);

        return "redirect:/admin_category_list";
    }

    /**
     * @param id      category id
     * @param session HttpSession对象
     * @return 重定向url(admin_category_list), 即List方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_category_delete")
    public String delete(int id, HttpSession session) throws IOException {
        categoryService.delete(id);

        File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder, id + ".jpg");
        file.delete();

        return "redirect:/admin_category_list";
    }

    /**
     * @param id    category id
     * @param model 渲染模型
     * @return jsp文件路径，具体路径为classpath/admin/editCategory.jsp
     * @throws IOException
     */
    @RequestMapping("admin_category_edit")
    public String edit(int id, Model model) throws IOException {
        Category c = categoryService.get(id);
        model.addAttribute("c", c);
        return "admin/editCategory";
    }

    /**
     * @param c                 category对象
     * @param session           HttpSession对象
     * @param uploadedImageFile
     * @return 重定向url(admin_category_list), 即List方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_category_update")
    public String update(Category c, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        categoryService.update(c);
        MultipartFile image = uploadedImageFile.getImage();
        if (null != image && !image.isEmpty()) {
            File imageFolder = new File(session.getServletContext().getRealPath("img/category"));
            File file = new File(imageFolder, c.getId() + ".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_category_list";
    }
}
