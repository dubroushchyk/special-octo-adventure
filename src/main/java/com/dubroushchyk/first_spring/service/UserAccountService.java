package com.dubroushchyk.first_spring.service;

import com.dubroushchyk.first_spring.entity.UserAccount;

import java.util.List;

public interface UserAccountService {

    public List<UserAccount> getAllUsersAccounts();

    public void saveUserAccount(UserAccount userAccount);

    public UserAccount getUserAccount(String userName);

    public UserAccount getUserAccountById(int id);

    public void deleteUserAccount(int id);
}
