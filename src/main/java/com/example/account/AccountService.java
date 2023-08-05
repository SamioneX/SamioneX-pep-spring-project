package com.example.account;

import org.springframework.stereotype.Service;

import com.example.Utils.ModelUtils.AccountUtils;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class AccountService {
    @Autowired(required = true)
    private AccountRepository accountDAO;

    public Account addAccount(Account account) {
        if (AccountUtils.accountIsValid(account) 
            && !accountDAO.accountWithUsernameExists(account.getUsername())) {
            return accountDAO.save(account);
        }
        return null;
    }

    public Account addAccount(String username, String password) {
        return addAccount(new Account(0, username, password));
    }

    public Account login(Account accountInfo) {
        return accountDAO.login(accountInfo);
    }

    public Account login(String username, String password) {
        return accountDAO.login(username, password);
    }

}
