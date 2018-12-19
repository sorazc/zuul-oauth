package cn.zc.oauth.service.impl;

import cn.zc.oauth.entity.User;
import cn.zc.oauth.model.SimpleUserDetails;
import cn.zc.oauth.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;

@Service
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(username);

        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));

        SimpleUserDetails simpleUserDetails = new SimpleUserDetails();
        simpleUserDetails.setUsername(user.getUsername());
        simpleUserDetails.setId(user.getId());
        simpleUserDetails.setPassword(user.getPassword());
        simpleUserDetails.setStatus(user.getStatus());

        Collection<SimpleGrantedAuthority> authorities = new LinkedList<>();
        simpleUserDetails.setAuthorities(authorities);

        return simpleUserDetails;
    }
}
