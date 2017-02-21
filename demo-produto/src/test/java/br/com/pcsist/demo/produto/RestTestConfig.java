package br.com.pcsist.demo.produto;

import org.apache.shiro.web.mgt.WebSecurityManager;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import br.com.pcsist.winthor.core.autenticacao.LoggedInService;
import br.com.pcsist.winthor.core.test.TestPersistenceConfig;

@EnableWebMvc
@Configuration
@Import(TestPersistenceConfig.class)
@ComponentScan("br.com.pcsist.demo.produto")
public class RestTestConfig {
  
  @Bean
  public LoggedInService loggedInService() {
    return Mockito.mock(LoggedInService.class);
  }
  
  @Bean
  public WebSecurityManager webSecurityManager(){
    return Mockito.mock(WebSecurityManager.class);
  }

}
