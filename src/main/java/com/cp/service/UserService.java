package com.cp.service;

import com.cp.entity.User;

/**
 * @author ChengPeng
 * @create 2019-10-28 10:55
 */
public interface UserService {

    /**
     * 添加或者修改用户信息
     */
    public void save(User user);

    /**
     * 根据时间查询上传的图片
     */
    public User findByUserName();


}
