package com.zhf.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author: 曾鸿发
 * @create: 2021-11-19 06:03
 * @description：自定义UserDetailsService实现类
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过mysql验证之后还需要封装为UserDetails对象信息
        // 不建议下面这样写，建议自定义编码器
        // String pw = BCrypt.hashpw("123456", BCrypt.gensalt());
        // UserDetails userDetails = new User("fox", "{bcrypt}" + pw,
        //         AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
        //
        // return userDetails;

        UserDetails userDetails = new User("fox", passwordEncoder.encode("123456"), AuthorityUtils.commaSeparatedStringToAuthorityList("admin,user"));
        return userDetails;
    }
}
