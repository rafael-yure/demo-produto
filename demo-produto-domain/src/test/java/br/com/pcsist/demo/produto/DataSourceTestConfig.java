package br.com.pcsist.demo.produto;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import br.com.pcsist.winthor.core.test.TestPersistenceConfig;

@Configuration
@Import(TestPersistenceConfig.class)
@ComponentScan("br.com.pcsist.demo.produto.domain")
public class DataSourceTestConfig {

}
