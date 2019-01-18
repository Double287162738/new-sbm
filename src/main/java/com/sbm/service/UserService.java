package com.sbm.service;

import com.sbm.pojo.model.User;

public interface UserService {

    /**
     * 通过用户名查找用户
     *
     * @param phone
     * @return
     */
    User getUserByPhone(String phone);


    /**
     * 通过用户ID查找用户
     *
     * @param userId
     * @return
     */
    User getUserByUserId(String userId);

    /**
     * 判断用户是否存在
     *
     * @param userId
     * @return
     */
    Boolean userIfExist(String userId);


    /**
     * 更新用户信息（入参的user的userId必须存在，不能为空）
     *
     * @param user
     * @return
     */
    Boolean UpdateUser(User user);

    /**
     * 根据用户ID查找用户的头像图片的名字
     *
     * @param userId
     * @return
     */
    User getUserIdAndUserAvatar(String userId);

    /**
     * 注册用户
     * @param user
     * @return
     */
    int  registeredUSer(User user);
    /**
     * 注册用户
     * @param user
     * @return
     */
    int  forgetPassword(User user);


    int initPassword(User user);


}
