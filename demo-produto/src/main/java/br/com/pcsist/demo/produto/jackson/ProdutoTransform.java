package br.com.pcsist.demo.produto.jackson;

import org.springframework.stereotype.Component;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.winthor.core.servico.Transform;

@Component
public class ProdutoTransform implements Transform<Produto, ProdutoDto> {

  @Override
  public ProdutoDto parse(Produto produto) {
    ProdutoDto dto = new ProdutoDto();
    dto.setCodigo(String.valueOf(produto.getCodigo()));
    dto.setNome(produto.getDescricao());
    return dto;
  }

}
