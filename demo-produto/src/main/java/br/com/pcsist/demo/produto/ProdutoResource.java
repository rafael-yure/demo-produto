package br.com.pcsist.demo.produto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;
import br.com.pcsist.demo.produto.jackson.ProdutoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(tags = { "demo-produto" })
@Controller
@RequestMapping("/")
public class ProdutoResource {

  @Autowired
  private ProdutoApplication produtoApplication;
  @Autowired
  private ProdutoRepository produtoRepository;

  @ApiOperation("Lista produtos")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Produto do banco de dados")
  })
  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody List<Produto> todos() {
    return produtoRepository.todos();
  }

  @ApiOperation("Insere um produto")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Caso produto seja inserido")
  })
  @RequestMapping(method = RequestMethod.POST)
  public @ResponseBody void inserir(@RequestBody ProdutoDto dto) {
    produtoApplication.inserir(dto);
  }

  @ApiOperation("Obter um produto a partir do seu código")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Caso produto seja inserido")
  })
  @RequestMapping(value = "/{codigo}", method = RequestMethod.GET)
  public @ResponseBody Produto comCodigo(@PathVariable("codigo") int codigo) {
    return produtoRepository.comCodigo(codigo);
  }

  @ApiOperation("Deletar logicamente um produto")
  @ApiResponses({
      @ApiResponse(code = 200, message = "Caso produto seja deletado logicamente")
  })
  @RequestMapping(value = "/delete/{codigo}", method = RequestMethod.PUT)
  public @ResponseBody void deletar(@PathVariable("codigo") int codigo) {
    produtoRepository.deletar(codigo);
  }

}
