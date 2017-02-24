package br.com.pcsist.demo.produto.domain.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;
import br.com.pcsist.demo.produto.domain.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService {

  private ProdutoRepository produtoRepository;

  @Autowired
  public ProdutoServiceImpl(ProdutoRepository produtoRepository) {
    this.produtoRepository = produtoRepository;
  }

  @Override
  public void alterar(int codigo, Produto produto) {
    Produto produtoExistente = produtoRepository.comCodigo(codigo);
    if (produtoExistente != null) {
      produtoExistente.setAtivo(produto.isAtivo());
      produtoRepository.alterar(produtoExistente);
    }
  }

}
