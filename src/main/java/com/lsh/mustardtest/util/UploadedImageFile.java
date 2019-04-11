package com.lsh.mustardtest.util;

import org.springframework.web.multipart.MultipartFile;

/**
 * 刘森华
 * 2019/04/11
 */

public class UploadedImageFile {
    MultipartFile image;

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
