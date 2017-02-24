package br.com.pcsist.demo.produto.jackson;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.winthor.core.servico.WinthorObjectMapper;

public class ProdutoObjectMapper extends WinthorObjectMapper {
  private static final long serialVersionUID = -1773673590934949404L;

  @Override
  protected void addModules() {
    addSerializer(Produto.class, ProdutoTransform.class);
  }

}
