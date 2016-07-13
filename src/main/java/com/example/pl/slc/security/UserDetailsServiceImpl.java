package com.example.pl.slc.security;

import com.example.pl.slc.model.User;
import com.example.pl.slc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * Created by slc on 11.07.16.
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println(userRepository);
        User user = userRepository.findByUserDetailsUsername(s);

        if(user == null) throw new UsernameNotFoundException("User not found!");

        return user.getUserDetails();
//            SimpleUserDetails ss = new SimpleUserDetails();
//        ss.setUsername("slc");
//        ss.setPassword("pswd");
//        return ss;
    }
}
