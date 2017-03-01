package br.com.pcsist.demo.produto;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import br.com.pcsist.winthor.core.servico.ShiroConfig;

@Configuration
@Import({ ShiroConfig.class })
public class ServiceConfig {

  @Bean
  public ProdutoApplication produtoApplication() {
    return new ProdutoApplication();
  }

}
