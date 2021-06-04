package com.dubroushchyk.first_spring.repository;

import com.dubroushchyk.first_spring.entity.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

    UserAccount getUserAccountByUserName(String userName);

}
