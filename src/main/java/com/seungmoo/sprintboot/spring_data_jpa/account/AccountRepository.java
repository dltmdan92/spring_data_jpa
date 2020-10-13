package com.seungmoo.sprintboot.spring_data_jpa.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByUsername(String username);

    @Query(nativeQuery = true, value="select * from account where username = '{0}'")
    Optional<Account> findByUsernameWithQuery(String username);
}
