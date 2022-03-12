package com.tc.springboot.repository;

import com.tc.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
    @Query("select u from tc_user u where u.tc_id = :tc_id")
    User findUserBytc_id(String tc_id);

    @Query("select u from tc_user u where u.tc_id = :tc_id")
    User findtc_passwordBytc_id(String tc_id);
}
