package com.example.account;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("FROM Account WHERE username = :username AND password = :password")
    Account login(@Param("username") String username, @Param("password") String password);

    default Account login(Account account) {
        return login(account.getUsername(), account.getPassword());
    }

    @Modifying
    @Query(value="INSERT INTO Account (username, password) VALUES (:username, :password)", nativeQuery=true)
    void registerAccount(@Param("username") String username, @Param("password") String password);


    @Query("SELECT COUNT(u) > 0 FROM Account a WHERE a.account_id = :id")
    boolean accountWithIdExists(@Param("id") int id);

    @Query("SELECT COUNT(u) > 0 FROM Account a WHERE a.username = :username")
    boolean accountWithUsernameExists(@Param("username") String username);
}
