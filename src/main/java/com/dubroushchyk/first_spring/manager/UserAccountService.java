package com.dubroushchyk.first_spring.manager;

import com.dubroushchyk.first_spring.entity.UserAccount;
import com.dubroushchyk.first_spring.enums.EnumStatus;
import com.dubroushchyk.first_spring.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class UserAccountService implements UserDetailsService {

    private UserAccountRepository userAccountRepository;

    public List<UserAccount> getAllUserAccounts() {
        return userAccountRepository.findAll();
    }

    public UserAccount getUserAccount(String userName) {
        return userAccountRepository.getUserAccountByUserName(userName);
    }

    public UserAccount getById(int id) {
        return userAccountRepository.getById(id);
    }

    public void saveUserAccount(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
        userAccountRepository.flush();
    }

    public void deleteUserAccount(int id) {
        userAccountRepository.deleteById(id);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserAccount userAccount = userAccountRepository
                .getUserAccountByUserName(userName);

        if (userAccount == null
                || userAccount.getStatus().equals(EnumStatus.INACTIVE)) {
            throw new UsernameNotFoundException(
                    "User " + userName + " was not found in the database");
        }

        return new User(
                userAccount.getUserName(),
                userAccount.getPassword(),
                Collections.singletonList(
                        new SimpleGrantedAuthority(
                                userAccount.getRole().toString()
                        )
                )
        );
    }

    @Autowired
    public void setUserAccountRepository(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

}
