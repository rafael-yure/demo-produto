package br.com.pcsist.demo.produto.domain.impl;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;

@Repository("produtoRepository")
public class ProdutoRepositoryImpl implements ProdutoRepository {

  private static final String INSERT_PRODUTO = "insert into produto (codigo, valor, ativo) values (?, ?, ?)";
  private JdbcOperations template;
  private ProdutoRowMapper mapper;

  @Autowired
  public ProdutoRepositoryImpl(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
    mapper = new ProdutoRowMapper();
  }

  @Override
  public List<Produto> todos() {
    String sql = "select codprod, descricao from pcprodut";
    return template.query(sql, mapper);
  }

  @Override
  public void salvar(Produto produto) {
    template.update(INSERT_PRODUTO, produto.getCodigo(), produto.isAtivo());
  }

  @Override
  public void salvar(List<Produto> produtos) {
    // template.batchUpdate(INSERT_PRODUTO, produtos, 100, (ps, p) -> {
    // ps.setInt(1, p.getCodigo());
    // ps.setInt(2, p.getValor());
    // ps.setBoolean(3, p.isAtivo());
    // });
  }

  @Override
  public Produto comCodigo(int codigo) {
    try {
      String sql = "select * from produto where codigo = ?";
      return template.queryForObject(sql, mapper, codigo);
    } catch (DataAccessException ex) {
      String msg = String.format("Produto de código: %s inválido", codigo);
      throw new IllegalArgumentException(msg);
    }
  }

  @Override
  public long totalDeProdutos() {
    String sql = "select count(codigo) from produto";
    return template.queryForObject(sql, Long.class);
  }

  @Override
  public List<Long> codigos() {
    String sql = "select codigo from produto";
    return template.queryForList(sql, Long.class);
  }

  @Override
  public List<Map<String, Object>> codigosValores() {
    String sql = "select codigo, valor from produto";
    return template.queryForList(sql);
  }

  @Override
  public long valorTotal() {
    String sql = "select sum(valor) from produto";
    Long value = template.queryForObject(sql, Long.class);
    return value != null ? value : 0;
  }

  @Override
  public void alterar(Produto produto) {
    // String sql = "update produto set valor=?, ativo=? where codigo = ?";
    // template.update(sql, produto.getValor(), produto.isAtivo(), produto.getCodigo());
  }

}
