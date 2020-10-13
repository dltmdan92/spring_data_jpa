package com.seungmoo.sprintboot.spring_data_jpa.account;

import org.h2.engine.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest // slicing test (repository 관련 bean들만 만들어서 test 하기), embeded DB가 필요(인메모리 H2), 쉽고 안전함
// @SpringBootTest --> Integration Test : Application root부터 모든 Bean들이 다 등록되서 테스트 된다. --> 여기서는 postgres 사용할 것임
// 테스트할 때는 embeded DB로 테스트하는 게 빨라서 좋다. 게다가 테스트용 DB가 필요함
// @SpringBootTest
public class AccountRepositoryTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AccountRepository accountRepository;

    Logger log = LoggerFactory.getLogger(AccountRepositoryTest.class);

    @Test
    public void di() throws SQLException {
        try(Connection connection = dataSource.getConnection()) {
            /*DatabaseMetaData metaData = connection.getMetaData();
            log.info(metaData.getURL());
            log.info(metaData.getDriverName());
            log.info(metaData.getUserName());*/
            Account account = new Account();
            account.setUsername("seungmoo");
            account.setPassword("pass");

            Account newAccount = accountRepository.save(account);

            assertThat(newAccount).isNotNull();

            Optional<Account> existingAccount = accountRepository.findByUsername(newAccount.getUsername());
            assertThat(existingAccount).isNotEmpty();

            // jpa @Query를 통해 조회 가능
            Optional<Account> nonExistingAccount = accountRepository.findByUsernameWithQuery("seungmoomoo");
            assertThat(nonExistingAccount).isEmpty();
        }
    }
}