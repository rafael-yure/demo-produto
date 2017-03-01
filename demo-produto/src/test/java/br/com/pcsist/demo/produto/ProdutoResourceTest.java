package br.com.pcsist.demo.produto;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;
import br.com.pcsist.demo.produto.jackson.ProdutoDto;
import br.com.pcsist.winthor.core.test.databuilder.ProdutoTestDataBuilder;

public class ProdutoResourceTest extends AbstractResourceTest {

  private ProdutoTestDataBuilder dataBuilder;

  @Autowired
  private ProdutoRepository produtoRepository;

  @Before
  public void setUp() throws Exception {
    dataBuilder = new ProdutoTestDataBuilder(dataSource());
  }

  @Test
  public void testTodos_SemDados() throws Exception {
    mockMvc().perform(get("/")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", empty()));
  }

  @Test
  public void testTodos_ComDados() throws Exception {
    dataBuilder.codProd(1).add("descricao", "descricaoProduto1").insert();
    dataBuilder.codProd(2).add("descricao", "descricaoProduto2").insert();
    mockMvc().perform(get("/")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andDo(print())
      .andExpect(jsonPath("$.[0].codigo", is(1)))
      .andExpect(jsonPath("$.[0].descricao", is("descricaoProduto1")))
      .andExpect(jsonPath("$.[1].codigo", is(2)))
      .andExpect(jsonPath("$.[1].descricao", is("descricaoProduto2")));
  }

  @Test
  public void testInserir() throws Exception {
    ProdutoDto dto = new ProdutoDto();
    dto.setCodigo("1");
    dto.setNome("Produto 1");

    mockMvc().perform(post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json(dto)))
      .andDo(print())
      .andExpect(status().isOk());

    Produto produto = produtoRepository.comCodigo(1);

    assertThat(produto.getCodigo()).isEqualTo(1);
    assertThat(produto.getDescricao()).isEqualTo("Produto 1");
  }

  @Test
  public void testComCodigo() throws Exception {
    dataBuilder.codProd(1).add("descricao", "descricaoProduto1").insert();

    mockMvc().perform(get("/{codigo}", 1)
      .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(jsonPath("$.codigo", is(1)));
  }

  @Test
  public void testDeletar() throws Exception {
    dataBuilder.codProd(1).add("descricao", "descricaoProduto1").insert();

    mockMvc().perform(put("/delete/{codigo}", 1)
      .contentType(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk());
  }

}
