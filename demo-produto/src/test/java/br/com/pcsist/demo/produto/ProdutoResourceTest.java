package br.com.pcsist.demo.produto;

import static org.hamcrest.collection.IsEmptyCollection.empty;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.springframework.http.MediaType;

import br.com.pcsist.demo.produto.jackson.ProdutoDto;

public class ProdutoResourceTest extends AbstractResourceTest {

  public void testTodos_SemDados() throws Exception {
    mockMvc().perform(get("/")
      .contentType(MediaType.APPLICATION_JSON))
      .andExpect(status().isOk())
      .andExpect(jsonPath("$", empty()));
  }

  @Test
  public void testSalvar() throws Exception {
    ProdutoDto dto = new ProdutoDto();
    mockMvc().perform(post("/")
      .contentType(MediaType.APPLICATION_JSON)
      .content(json(dto)))
      .andExpect(status().isOk());
  }

}
