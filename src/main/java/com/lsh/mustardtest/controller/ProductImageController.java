package com.lsh.mustardtest.controller;

import com.lsh.mustardtest.pojo.Product;
import com.lsh.mustardtest.pojo.ProductImage;
import com.lsh.mustardtest.service.ProductImageService;
import com.lsh.mustardtest.service.ProductService;
import com.lsh.mustardtest.util.ImageUtil;
import com.lsh.mustardtest.util.UploadedImageFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;

/**
 * 刘森华
 * 2019/04/16
 */

@Controller
@RequestMapping("")
public class ProductImageController {
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;

    /**
     * @param pi                ProductImage对象
     * @param session           HttpSession对象
     * @param uploadedImageFile 上传的图片文件
     * @return 重定向url(" admin_productImage "), 即list方法映射的路径
     */
    @RequestMapping("admin_productImage_add")
    public String add(ProductImage pi, HttpSession session, UploadedImageFile uploadedImageFile) {
        productImageService.add(pi);
        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;
        if (ProductImageService.type_single.equals(pi.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
        } else
            imageFolder = session.getServletContext().getRealPath("img/productDetail");

        File f = new File(imageFolder, fileName);
        f.getParentFile().mkdirs();
        try {
            uploadedImageFile.getImage().transferTo(f);
            BufferedImage img = ImageUtil.change2jpg(f);
            ImageIO.write(img, "jpg", f);

            if (ProductImageService.type_single.equals(pi.getType())) {
                File f_small = new File(imageFolder_small, fileName);
                File f_middle = new File(imageFolder_middle, fileName);

                ImageUtil.resizeImage(f, 56, 56, f_small);
                ImageUtil.resizeImage(f, 217, 190, f_middle);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:admin_productImage_list?productID=" + pi.getProductID();
    }

    /**
     * @param id      product image id
     * @param session HttpSession对象
     * @return 重定向url(" admin_productImage_list "), 即list方法映射的路径
     */
    @RequestMapping("admin_productImage_delete")
    public String delete(Integer id, HttpSession session) {
        ProductImage pi = productImageService.get(id);

        String fileName = pi.getId() + ".jpg";
        String imageFolder;
        String imageFolder_small = null;
        String imageFolder_middle = null;

        if (ProductImageService.type_single.equals(pi.getType())) {
            imageFolder = session.getServletContext().getRealPath("img/productSingle");
            imageFolder_small = session.getServletContext().getRealPath("img/productSingle_small");
            imageFolder_middle = session.getServletContext().getRealPath("img/productSingle_middle");
            File imageFile = new File(imageFolder, fileName);
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            imageFile.delete();
            f_small.delete();
            f_middle.delete();
        } else {
            imageFolder = session.getServletContext().getRealPath("img/productDetail");
            File imageFile = new File(imageFolder, fileName);
            imageFile.delete();
        }

        productImageService.delete(id);

        return "redirect:admin_productImage_list?productID=" + pi.getProductID();
    }

    /**
     * @param productID
     * @param model     渲染模型
     * @return jsp文件路径，具体路径为classpath:/admin/listProductImage.jsp
     */
    @RequestMapping("admin_productImage_list")
    public String list(Integer productID, Model model) {
        Product p = productService.get(productID);
        List<ProductImage> pisSingle = productImageService.list(productID, ProductImageService.type_single);
        List<ProductImage> pisDetail = productImageService.list(productID, ProductImageService.type_detail);

        model.addAttribute("p", p);
        model.addAttribute("pisSingle", pisSingle);
        model.addAttribute("pisDetail", pisDetail);

        return "admin/listProductImage";
    }
}
