package br.com.pcsist.demo.produto;

import javax.sql.DataSource;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DataSourceTestConfig.class })
public abstract class AbstractPersistenceTest {

  @Autowired
  private DataSource dataSource;

  public DataSource dataSource() {
    return dataSource;
  }

}
