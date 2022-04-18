package com.bobocode.service;

import com.bobocode.dao.AccountDao;
import com.bobocode.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Comparator.comparing;

/**
 * This class is marked as component, so Spring will crete an instance of this class, and will register it
 * in the application context. That instance is called a bean.
 *
 * This service has a relation to dao. If you want the container to inject (set) this relation, you can mark the field
 * with @{@link Autowired}.
 *
 */
@Service
public class AccountService {
    private final AccountDao accountDao;

    public AccountService(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public Optional<Account> findOldestClient() {
        return accountDao.getAllAccounts().stream().min(comparing(Account::getBirthday));
    }
}

