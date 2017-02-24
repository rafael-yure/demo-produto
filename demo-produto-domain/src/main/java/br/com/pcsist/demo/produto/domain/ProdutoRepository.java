package br.com.pcsist.demo.produto.domain;

import java.util.List;

public interface ProdutoRepository {

  List<Produto> todos();

  Produto comCodigo(int codigo);

  void inserir(Produto produto);

  void alterar(Produto produto);

  void deletar(int codigoProduto);

}
