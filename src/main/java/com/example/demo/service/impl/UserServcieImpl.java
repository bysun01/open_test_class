package com.example.demo.service.impl;

import com.example.demo.common.BizEnum;
import com.example.demo.entity.User;
import com.example.demo.exception.BizException;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CacheService;
import com.example.demo.service.UserService;
import com.example.demo.utils.JwtHelper;
import com.example.demo.utils.MD5Util;
import com.example.demo.vo.UserVo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.*;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

/**
 * @author ares
 */
@Service
public class UserServcieImpl implements UserService {

    private UserRepository userRepository;
    private CacheService cacheService;

    @Autowired
    public void getRepository(UserRepository userRepository, CacheService cacheService) {
        this.userRepository = userRepository;
        this.cacheService = cacheService;
    }

    @Override
    public UserVo login(String username, String password) {
        // 校验账号密码
        User user = new User();
        user.setUsername(username);
        Example<User> of = Example.of(user);
        user = userRepository.findOne(of).get();
        if (user == null || MD5Util.getMD5Code(password).equals(user.getPassword())) {
            throw new BizException(BizEnum.NAME_OR_PASSWOR_IS_ERROR);
        }
        // 获取token 放入缓存
        HashMap<String, String> map = Maps.newHashMap();
        map.put("id", String.valueOf(user.getId()));
        String token = JwtHelper.genToken(map);
        cacheService.setCommonCache(token, user);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        userVo.setToken(token);
        return userVo;
    }

    @Override
    public Boolean checkToken(String token) {
        Object object = cacheService.getCommonCache(token);
        if (object == null) {
            return false;
        }
        return true;
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User delUser(Integer id) {
        User user = userRepository.findById(id).get();
        user.setIsDel((byte) 0);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<User> getUsers(Integer pageNo, Integer pageSize, String condition, Integer roleId) {
        Pageable page = PageRequest.of(pageNo, pageSize);
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Path<String> username = root.get("username");
                Path<String> nickname = root.get("nickname");
                Path<String> no = root.get("no");
                Path<Integer> roleId1 = root.get("roleId");
                Predicate p4 = null;
                Predicate p5 = null;
                List<Predicate> lstPredicates = Lists.newArrayList();
                if (StringUtils.isNotBlank(condition)) {
                    Predicate p1 = cb.like(username, "%" + condition + "%");
                    Predicate p2 = cb.like(nickname, "%" + condition + "%");
                    Predicate p3 = cb.like(no, "%" + condition + "%");
                    p5 = cb.or(p1, p2, p3);
                }
                if (roleId1 != null) {
                    p4 = cb.equal(roleId1, roleId);
                }
                if (p4 != null && p5 != null) {
                    return cb.and(p4, p5);
                }
                if (p4 != null && p5 == null) {
                    return p4;
                }
                if (p4 == null && p5 != null) {
                    return p5;
                }
                return null;
            }
        };
        return userRepository.findAll(specification, page);
    }
}
