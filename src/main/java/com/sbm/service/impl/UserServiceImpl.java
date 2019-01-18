package com.sbm.service.impl;

import com.sbm.mapper.UserMapper;
import com.sbm.pojo.model.User;
import com.sbm.pojo.model.UserExample;
import com.sbm.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByPhone(String phone) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserPhoneEqualTo(phone);
        User user = userMapper.selectOneByExample(userExample);
        return user;
    }


    @Override
    public User getUserByUserId(String userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        User user = userMapper.selectOneByExample(userExample);
        return user;
    }


    @Override
    public Boolean userIfExist(String userId) {
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        userExample.addSelectiveField("userId");
        User user = userMapper.selectOneByExample(userExample);
        if (user == null) {
            return false;
        }
        return true;
    }


    @Override
    public Boolean UpdateUser(User user) {
        String userId = user.getUserId();
        UserExample userExample = new UserExample();
        userExample.createCriteria().andUserIdEqualTo(userId);
        user.setUserId(null);
        userMapper.updateByExampleSelective(user, userExample);
        return true;
    }


    @Override
    public User getUserIdAndUserAvatar(String userId) {
        UserExample userExample = new UserExample();
        List<String> queryList = Arrays.asList("userId", "userAvatar");
        userExample.addSelectiveFields(queryList);
        userExample.createCriteria().andUserIdEqualTo(userId);
        return userMapper.selectOneByExample(userExample);
    }

    @Override
    public int registeredUSer(User user) {
        return userMapper.insertSelective(user);
    }

    @Override
    public int forgetPassword(User user) {
        UserExample  userExample= new  UserExample();
        userExample.createCriteria().andUserPhoneEqualTo(user.getUserPhone());
        return userMapper.updateByExampleSelective(user,userExample);
    }

    @Override
    public int initPassword(User user) {
        int result = userMapper.updateByPrimaryKeySelective(user);
        return result;
    }


}
