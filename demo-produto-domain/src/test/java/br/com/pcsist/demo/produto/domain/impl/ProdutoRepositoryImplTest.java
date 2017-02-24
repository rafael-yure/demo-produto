package br.com.pcsist.demo.produto.domain.impl;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.pcsist.demo.produto.AbstractPersistenceTest;
import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;
import br.com.pcsist.winthor.core.test.databuilder.ProdutoTestDataBuilder;

public class ProdutoRepositoryImplTest extends AbstractPersistenceTest {

  @Autowired
  private ProdutoRepository produtoRepository;

  private ProdutoTestDataBuilder dataBuilder;

  @Before
  public void setUp() throws Exception {
    dataBuilder = new ProdutoTestDataBuilder(dataSource());
    dataBuilder.codProd(1).add("descricao", "descricaoProduto1").insert();
  }

  @Test
  public void testTodos_ComUmProduto() throws Exception {
    List<Produto> todos = produtoRepository.todos();

    assertThat(todos).isNotNull();
    assertThat(todos).hasSize(1);
  }

  @Test
  public void testAlterar() throws Exception {
    Produto produto = produtoRepository.comCodigo(1);

    produto.setDescricao("NovaDescricao");
    produtoRepository.alterar(produto);

    assertThat(produto.getDescricao()).isEqualTo("NovaDescricao");
  }

  @Test
  public void testDeletar() throws Exception {
    produtoRepository.deletar(1);

    Produto produto = produtoRepository.comCodigo(1);

    assertThat(produto.getDataExclusao()).isNotNull();

  }

}
