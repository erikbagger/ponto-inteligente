package br.com.udemy.erikbagger.pontointeligente.api;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@Sql(scripts = "/db/teardown.sql", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
public class PontoInteligenteApiApplicationTests {

}
