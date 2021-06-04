package com.dubroushchyk.first_spring.controller;

import com.dubroushchyk.first_spring.entity.UserAccount;
import com.dubroushchyk.first_spring.service.SecurityService;
import com.dubroushchyk.first_spring.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Controller
public class MyController {

    private UserAccountService iUserAccountService;
    private SecurityService securityService;
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public void setIUserAccountService(UserAccountService iUserAccountService) {
        this.iUserAccountService = iUserAccountService;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @GetMapping("/tt")
    public ModelAndView showAllUserAccountTT() {
        return new ModelAndView("_menu");
    }

    @GetMapping("/user")
    public String showAllUserAccount(Model model) {
        model.addAttribute("userAccounts", iUserAccountService.getAllUsersAccounts());
        return "/last/index";
    }

    @GetMapping(path = "/user/{id}", produces = APPLICATION_JSON_VALUE)
    public UserAccount getUserAccount(@PathVariable Integer id) {
        UserAccount userAccount = iUserAccountService.getUserAccountById(id);
        return userAccount;
    }

    @GetMapping ("/user/new")
    public String addNewUserAccount_new(Model model) {
        model.addAttribute("userAccount", new UserAccount());
        return "/last/new";
    }

    @PostMapping("/")
    public String addNewUserAccount_create(@ModelAttribute UserAccount userAccount,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/new";
        }
        String encodedPassword = passwordEncoder
                .encode(userAccount.getPassword());
        userAccount.setPassword(encodedPassword);
        iUserAccountService.saveUserAccount(userAccount);
        return "redirect:/user";
    }

//    @DeleteMapping("/user/delete/{id}")
//    public String delete (@PathVariable("id") int id) {
//        iUserAccountService.deleteUserAccount(id);
//        return "redirect:/user";
//    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUserAccount (@PathVariable int id) {
        iUserAccountService.deleteUserAccount(id);
        return "employee" + id + " was delete";
    }
//    @PostMapping ("/new")
//    public String addNewUserAccount_create(@ModelAttribute ("userAccount") @Valid UserAccount userAccount,
//                                    BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return "last/new";
//        }
//        iUserAccountService.saveUserAccount(userAccount);
//        return "redirect:/last/new";
//    }

//    @GetMapping ("/user/{id}/edit")
//    public String editUserAccount (Model model, @PathVariable("id") int id) {
//        model.addAttribute("userAccount", iUserAccountService.getUserAccountById(id));
//        return "/last/edit";
//    }
//
//    @PatchMapping("/{id}")
//    public String updateUserAccount(@ModelAttribute("userAccount") UserAccount userAccount
//            , @PathVariable("id") int id) {
//        iUserAccountService. //  КАК СДЕЛАТЬ
//        return "redirect:/people";
//    }


    @GetMapping("/user/{id}/edit")
    public String editUserAccount (@PathVariable Integer id, Model model) {
        model.addAttribute("userAccount", iUserAccountService.getUserAccountById(id));
        return "/last/edit";
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable Integer id, Model model) {
        model.addAttribute(
                "userAccounts",
                Collections.singletonList(
                        iUserAccountService.getUserAccountById(id)
                )
        );
        return "/last/index";
    }

    @PostMapping(value = "/user/{id}/edit")
    public String updateById(@ModelAttribute UserAccount userAccount) {
        iUserAccountService.saveUserAccount(userAccount);
        return "redirect:/user";
    }

}
