package com.example.demo.repository;

import com.example.demo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * @author ares
 */
public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

    @Query(value = "select * from t_user where if(:condition != '', " +
            "(username like %:condition% or nickname like %:condition% or no like %:condition%), 1= 1 )" +
            "if(:roleId != '', role_id = :roleId, 1 = 1)",
            nativeQuery = true,
            countQuery = "select count(1) from t_user")
    Page<User> findUsers(String condition, Integer roleId, Pageable page);
}
