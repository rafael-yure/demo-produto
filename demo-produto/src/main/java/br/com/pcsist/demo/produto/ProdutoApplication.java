package br.com.pcsist.demo.produto;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;
import br.com.pcsist.demo.produto.jackson.ProdutoDto;

@ComponentScan
public class ProdutoApplication {

  @Autowired
  ProdutoRepository produtoRepository;

  public void inserir(ProdutoDto dto) {
    Produto produto = new Produto();
    produto.setCodigo(NumberUtils.toInt(dto.getCodigo()));
    produto.setDescricao(dto.getNome());
    produtoRepository.inserir(produto);
  }

}
