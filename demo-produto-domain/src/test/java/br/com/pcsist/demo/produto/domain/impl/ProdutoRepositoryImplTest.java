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
  }

  @Test
  public void testTodos_SemDados() throws Exception {
    List<Produto> todos = produtoRepository.todos();

    assertThat(todos).isNotNull();
    assertThat(todos).hasSize(0);
  }

  @Test
  public void testTodos_ComUmProduto() throws Exception {
    dataBuilder.codProd(1).add("descricao", "P1").insert();

    List<Produto> todos = produtoRepository.todos();

    assertThat(todos).isNotNull();
    assertThat(todos).hasSize(1);
  }

}
