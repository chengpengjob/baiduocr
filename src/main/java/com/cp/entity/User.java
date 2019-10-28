package com.cp.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * @author ChengPeng
 * @create 2019-10-28 10:50
 */
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;//编号

    @Column(length = 100)
    private String imageName;//上传的图像

    @Column(length = 255)
    private String imageurl;//上传图片保存的url地址

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    private String typeImage;//上传图片的类型

    private Date uploadDate;//上传日期

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getTypeImage() {
        return typeImage;
    }

    public void setTypeImage(String typeImage) {
        this.typeImage = typeImage;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
