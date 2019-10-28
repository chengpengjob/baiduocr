package com.cp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author ChengPeng
 * @create 2019-10-28 10:56
 */
@Controller
public class IndexController {

    /**
     * 网站根目录请求
     * @return
     */
    @RequestMapping("/")
    public ModelAndView root(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("index");
        return mav;
    }
}
