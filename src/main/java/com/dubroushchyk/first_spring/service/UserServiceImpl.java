package com.dubroushchyk.first_spring.service;

import com.dubroushchyk.first_spring.entity.UserAccount;
import com.dubroushchyk.first_spring.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserAccountService {


    private UserAccountRepository userAccountRepository;

    @Autowired
    public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    public List<UserAccount> getAllUsersAccounts() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUserAccount(String userName) {
        return userAccountRepository.getUserAccountByUserName(userName);
    }

    public UserAccount getUserAccountById(int id) {
        return userAccountRepository.getById(id);
    }

    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.saveAndFlush(userAccount);
    }

    public void deleteUserAccount(int id) {
        userAccountRepository.deleteById(id);
    }
}
