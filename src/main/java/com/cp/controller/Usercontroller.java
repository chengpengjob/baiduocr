package com.cp.controller;

import com.cp.entity.User;
import com.cp.service.UserService;
import com.cp.util.Base64Util;
import com.cp.util.DateUtil;
import com.cp.util.FileUtil;
import com.cp.util.HttpUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ChengPeng
 * @create 2019-10-28 11:00
 */
@Controller
@RequestMapping("/user")
public class Usercontroller {
    @Autowired
    private UserService userService;

    @Value("${userImageFilePath}")
    private String userImageFilePath;

    /**
     * 上传图像
     */
    @ResponseBody
    @RequestMapping("/uploadImage")
    public Map<String,Object> uploadImage(MultipartFile file, HttpSession session)throws Exception{
        Map<String,Object> map = new HashMap<String,Object>();
        if(!file.isEmpty()){
            String fileName = file.getOriginalFilename();
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            String newFileName = DateUtil.getCurrentDateStr()+suffixName;
            FileUtils.copyInputStreamToFile(file.getInputStream(),new File(userImageFilePath+newFileName));
            map.put("code ",0);
            map.put("msg","上传成功");
            Map<String,Object> map2 = new HashMap<String,Object>();
            map2.put("src","/userImages/"+newFileName);
            map2.put("title",newFileName);
            map.put("data",map2);
            Date uploadtime = DateUtil.getNowDate();

            User user = new User();
            user.setImageName(newFileName);
            user.setUploadDate(uploadtime);
            userService.save(user);
        }
        return map;
    }
    /**
     * 解析idcard图片内容
     */
    @ResponseBody
    @RequestMapping("/idcard")
    public String analysisidcard(){
        //识别url
        String idcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/idcard";
        User user = userService.findByUserName();
        String name = user.getImageName();
        //获取上传的图片
        String filePath = "E:/userImages" + "/" + name;
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            // 识别身份证正面id_card_side=front;识别身份证背面id_card_side=back;
            String params = "id_card_side=front&" + URLEncoder.encode("image", "UTF-8") + "="
                    + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.aac625bd15a2d5660a94a43fc908e9b2.2592000.1574217790.282335-17578013";
            String result = HttpUtil.post(idcardIdentificate, accessToken, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析bankcard图片内容
     * @return
     */
    @ResponseBody
    @RequestMapping("/bankcard")
    public String analysisbankcard(){
        // 识别url
        String bankcardIdentificate = "https://aip.baidubce.com/rest/2.0/ocr/v1/bankcard";
        User user = userService.findByUserName();
        String name = user.getImageName();
        //获取上传的图片
        String filePath = "E:/userImages" + "/" + name;
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.aac625bd15a2d5660a94a43fc908e9b2.2592000.1574217790.282335-17578013";
            String result = HttpUtil.post(bankcardIdentificate, accessToken, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }
    /**
     * 解析business图片内容
     */
    @ResponseBody
    @RequestMapping("/business")
    public String analysisbusiness(){
        // 通用识别url
        String otherHost = "https://aip.baidubce.com/rest/2.0/ocr/v1/general";
        // 本地图片路径
        String filePath = "E:/images/business.jpg";
        try {
            byte[] imgData = FileUtil.readFileByBytes(filePath);
            String imgStr = Base64Util.encode(imgData);
            String params = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(imgStr, "UTF-8");
            /**
             * 线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
             */
            String accessToken = "24.aac625bd15a2d5660a94a43fc908e9b2.2592000.1574217790.282335-17578013";
            String result = HttpUtil.post(otherHost, accessToken, params);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
