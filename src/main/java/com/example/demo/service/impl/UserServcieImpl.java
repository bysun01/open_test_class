package com.example.demo.service.impl;

import com.example.demo.common.BizEnum;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.BizException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CacheService;
import com.example.demo.service.UserService;
import com.example.demo.utils.CopyUtil;
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
import java.util.Date;
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
    private RoleRepository roleRepository;

    @Autowired
    public void getRepository(UserRepository userRepository, CacheService cacheService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.cacheService = cacheService;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserVo login(String username, String password) {
        // 校验账号密码
        User user = new User();
        user.setUsername(username);
        user.setIsDel(1);
        Example<User> of = Example.of(user);
        user = userRepository.findOne(of).get();
        if (user == null || !MD5Util.getMD5Code(password).equals(user.getPassword())) {
            throw new BizException(BizEnum.NAME_OR_PASSWOR_IS_ERROR);
        }
        // 获取token 放入缓存
        HashMap<String, String> map = Maps.newHashMap();
        map.put("id", String.valueOf(user.getId()));
        String token = JwtHelper.genToken(map);
        cacheService.setCommonCache(token, user);
        UserVo userVo = new UserVo();
        CopyUtil.copyProperties(user, userVo);
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
    public User save(UserVo uservo) {
        Role role = roleRepository.findById(uservo.getRoleId()).get();
        User user = new User();
        CopyUtil.copyProperties(uservo, user);
        if (user.getId() == null) {
            User user1 = new User();
            user1.setUsername(user.getUsername());
            user1.setIsDel(1);
            Example<User> of = Example.of(user1);
            List<User> all = userRepository.findAll(of);
            if (all.size() > 0) {
                throw new BizException(BizEnum.USERNAME_EXIST);
            }
            user.setPassword(MD5Util.getMD5Code(user.getPassword()));
            user.setRole(role);
            user.setCreateBy(1L);
            user.setUpdateBy(1L);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setIsDel(1);
            return userRepository.save(user);
        } else {
            if (StringUtils.isNotBlank(user.getPassword())) {
                user.setPassword(MD5Util.getMD5Code(user.getPassword()));
            }
            User user1 = userRepository.findById(user.getId()).get();
            CopyUtil.copyProperties(user, user1);
            user1.setRole(role);
            user.setUpdateTime(new Date());
            user.setUpdateBy(1L);
            return userRepository.save(user1);
        }

    }

    @Override
    public User delUser(Long id) {
        User user = userRepository.findById(id).get();
        user.setUpdateTime(new Date());
        user.setIsDel(0);
        return userRepository.save(user);
    }

    @Override
    public User getUser(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public Page<User> getUsers(Integer pageNo, Integer pageSize, String condition, Long roleId) {
        Pageable page = PageRequest.of(pageNo - 1, pageSize);
        Specification<User> specification = new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Path<String> username = root.get("username");
                Path<String> nickname = root.get("nickname");
                Path<String> no = root.get("no");
                Path<Role> role = root.get("role");
                Path<Integer> isDel = root.get("isDel");
                Predicate p4 = null;
                Predicate p5 = null;
                Predicate equal = cb.equal(isDel, 1);
                List<Predicate> lstPredicates = Lists.newArrayList();
                if (StringUtils.isNotBlank(condition)) {
                    Predicate p1 = cb.like(username, "%" + condition + "%");
                    Predicate p2 = cb.like(nickname, "%" + condition + "%");
                    Predicate p3 = cb.like(no, "%" + condition + "%");
                    p5 = cb.or(p1, p2, p3);
                }
                if (roleId != null) {
                    p4 = cb.equal(role, roleRepository.findById(roleId).get());
                }
                if (p4 != null && p5 != null) {
                    return cb.and(p4, p5, equal);
                }
                if (p4 != null && p5 == null) {
                    return cb.and(p4, equal);
                }
                if (p4 == null && p5 != null) {
                    return cb.and(p5, equal);
                }
                return equal;
            }
        };
        return userRepository.findAll(specification, page);
    }
}
