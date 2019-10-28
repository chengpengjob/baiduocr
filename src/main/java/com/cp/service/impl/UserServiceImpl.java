package com.cp.service.impl;

import com.cp.entity.User;
import com.cp.repository.UserRepository;
import com.cp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ChengPeng
 * @create 2019-10-28 10:55
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByUserName() {
        return userRepository.findByUserImage();
    }
}