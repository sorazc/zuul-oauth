package cn.zc.oauth.service.impl;

import cn.zc.oauth.entity.User;
import cn.zc.oauth.mapper.UserMapper;
import cn.zc.oauth.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {



}
