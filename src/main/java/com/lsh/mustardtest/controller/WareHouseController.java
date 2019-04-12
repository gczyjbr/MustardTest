package com.lsh.mustardtest.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lsh.mustardtest.pojo.WareHouse;
import com.lsh.mustardtest.service.WareHouseService;
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

/**
 * 刘森华
 * 2019/04/12
 */

@Controller
@RequestMapping("")
public class WareHouseController {

    @Autowired
    WareHouseService wareHouseService;

    /**
     * @param model 渲染模型
     * @param page  Page对象
     * @return jsp文件路径，具体路径为classpath/admin/listWareHouse.jsp
     */
    @RequestMapping("admin_warehouse_list")
    public String list(Model model, Page page) {
        PageHelper.offsetPage(page.getStart(), page.getCount());
        List<WareHouse> ws = wareHouseService.list();
        int total = (int) new PageInfo<>(ws).getTotal();
        page.setTotal(total);
        model.addAttribute("ws", ws);
        return "admin/listWareHouse";
    }

    /**
     * @param w                 WareHouse对象
     * @param session           HttpSession对象
     * @param uploadedImageFile 上传的图片文件
     * @return 重定向url(admin_warehouse_list), 即list方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_warehouse_add")
    public String add(WareHouse w, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        System.out.println(w.getId());
        wareHouseService.add(w);
        System.out.println(w.getId());
        addImage(w, session, uploadedImageFile);

        return "redirect:/admin_warehouse_list";
    }

    /**
     * @param id      warehouse id
     * @param session HttpSession对象
     * @return 重定向url(admin_warehouse_list), 即list方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_warehouse_delete")
    public String delete(Integer id, HttpSession session) throws IOException {
        wareHouseService.delete(id);

        File file = getFile(session, id);
        file.delete();

        return "redirect:/admin_warehouse_list";
    }

    /**
     * @param id    warehouse id
     * @param model 渲染模型
     * @return jsp文件路径，具体路径为classpath/admin/editWareHouse.jsp
     * @throws IOException
     */
    @RequestMapping("admin_warehouse_edit")
    public String edit(Integer id, Model model) throws IOException {
        WareHouse w = wareHouseService.get(id);
        model.addAttribute("w", w);
        return "admin/editWareHouse";
    }

    /**
     * @param w                 WareHouse对象
     * @param session           HttpSession对象
     * @param uploadedImageFile 上传的图片文件
     * @return 重定向url(admin_warehouse_list), 即list方法映射的路径
     * @throws IOException
     */
    @RequestMapping("admin_warehouse_update")
    public String update(WareHouse w, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        wareHouseService.update(w);
        MultipartFile image = uploadedImageFile.getImage();
        if (null != image && !image.isEmpty()) {
            /*File file = getFile(session, w.getId());
            image.transferTo(file);
            writeImg(file);*/
            File imageFolder = new File(session.getServletContext().getRealPath("img/warehouse"));
            File file = new File(imageFolder, w.getId() + ".jpg");
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        }
        return "redirect:/admin_warehouse_list";
    }

    /**
     * @param w                 WareHouse对象
     * @param session           HttpSession对象
     * @param uploadedImageFile 上传的图片文件
     * @throws IOException
     */
    private void addImage(WareHouse w, HttpSession session, UploadedImageFile uploadedImageFile) throws IOException {
        File file = createFolder(w, session);
        outputImageFile(uploadedImageFile, file);
        transfer(uploadedImageFile, file);
    }

    /**
     * @param uploadedImageFile 上传的图片文件
     * @param file              File对象
     * @throws IOException
     */
    private void transfer(UploadedImageFile uploadedImageFile, File file) throws IOException {
        uploadedImageFile.getImage().transferTo(file);
        writeImg(file);
    }

    private void writeImg(File file) throws IOException {
        BufferedImage img = ImageUtil.change2jpg(file);
        ImageIO.write(img, "jpg", file);
    }

    /**
     * @param uploadedImageFile 上传的图片文件
     * @param file              File对象
     */
    private void outputImageFile(UploadedImageFile uploadedImageFile, File file) {
        System.out.println(uploadedImageFile);
        System.out.println(uploadedImageFile.getImage());
        System.out.println(file);
    }

    /**
     * @param w       WareHouse对象
     * @param session HttpSession对象
     * @return
     */
    private File createFolder(WareHouse w, HttpSession session) {
        File file = getFile(session, w.getId());
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        return file;
    }

    /**
     * @param session HttpSession对象
     * @param id      warehouse id
     * @return
     */
    private File getFile(HttpSession session, Integer id) {
        File imageFolder = new File(session.getServletContext().getRealPath("img/warehouse"));
        return new File(imageFolder, id + ".jpg");
    }
}
