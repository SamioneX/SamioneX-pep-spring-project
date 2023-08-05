package com.example.Utils.ModelUtils;

import com.example.account.Account;

public class AccountUtils {
    public static boolean accountIsValid(Account account) {
        String username = account.getUsername();
        String password = account.getPassword();
        return username != null 
            && !username.isBlank() 
            && password != null 
            && password.length() >= 4;
    }
}
