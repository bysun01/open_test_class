package com.example.demo;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.UserService;
import com.example.demo.utils.IdGen;
import com.example.demo.utils.MD5Util;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    private UserService userService;
    private RoleRepository roleRepository;

    @Autowired
    public void injectBean(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @Test
    public void contextLoads() {
        Role role = roleRepository.findById(1L).get();
        User user = new User();
        user.setUsername("Admin");
        user.setPassword(MD5Util.getMD5Code("Admin"));
        user.setIsDel((byte) 1);
        user.setAge(11);
        user.setCreateBy(1L);
        user.setUpdateBy(1L);
        user.setCreateTime(new Date());
        user.setCreateTime(new Date());
        user.setGender((byte) 1);
        user.setNickname("admin");
        user.setNo("1");
        user.setPhone("18202559941");
        user.setRole(role);
        userService.save(user);
    }

    @Test
    public void saveRole() {
        Role role = new Role();
        role.setRoleName("admin");
        role.setCreateBy(1L);
        role.setUpdateBy(1L);
        role.setCreateTime(new Date());
        role.setUpdateTime(new Date());
        role.setIsDel((byte) 1);
        Role save = roleRepository.save(role);
        System.out.println(save.toString());
    }

    @Test
    public void testGetUsers() {
        Page<User> users = userService.getUsers(1, 10, "dm", 1L);
        System.out.println(JSONArray.toJSONString(users.getContent()));
    }

    @Test
    public void testSaveUser() {
        Role role = roleRepository.findById(2L).get();
        User user = new User();
        user.setUsername("tea1");
        user.setPassword(MD5Util.getMD5Code("tea1"));
        user.setIsDel((byte) 1);
        user.setAge(11);
        user.setCreateBy(1L);
        user.setUpdateBy(1L);
        user.setCreateTime(new Date());
        user.setCreateTime(new Date());
        user.setGender((byte) 1);
        user.setNickname("tea1");
        user.setNo("1");
        user.setPhone("18202559941");
        user.setRole(role);
        user.setId(66L);
        userService.save(user);
    }

}
