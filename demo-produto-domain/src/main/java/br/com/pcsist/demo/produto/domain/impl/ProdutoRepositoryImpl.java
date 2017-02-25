package br.com.pcsist.demo.produto.domain.impl;

import java.time.LocalDateTime;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.demo.produto.domain.ProdutoRepository;
import br.com.pcsist.winthor.core.servico.data.DbLocalDateParser;

@Repository("produtoRepository")
public class ProdutoRepositoryImpl implements ProdutoRepository {

  /*
   * codprod NUMBER(6) not null,
   * descricao VARCHAR2(40) not null,
   * embalagem VARCHAR2(12) not null,
   * codepto NUMBER(6) not null,
   * codsec NUMBER(6) not null,
   * codfornec NUMBER(6) not null,
   */

  private static final String INSERT_PRODUTO = "insert into pcprodut (codprod, descricao, embalagem,"
      + " codepto, codsec, codfornec) values (?, ?, \'123\', 1, 1, 1)";
  private JdbcOperations template;
  private ProdutoRowMapper mapper;

  @Autowired
  public ProdutoRepositoryImpl(DataSource dataSource) {
    template = new JdbcTemplate(dataSource);
    mapper = new ProdutoRowMapper();
  }

  @Override
  public List<Produto> todos() {
    String sql = "select codprod, descricao, dtexclusao from pcprodut";
    return template.query(sql, mapper);
  }

  @Override
  public void inserir(Produto produto) {
    template.update(INSERT_PRODUTO, produto.getCodigo(), produto.isAtivo());
  }

  @Override
  public Produto comCodigo(int codigo) {
    try {
      String sql = "select * from pcprodut where codprod = ?";
      return template.queryForObject(sql, mapper, codigo);
    } catch (DataAccessException ex) {
      String msg = String.format("Produto de código: %s inválido", codigo);
      throw new IllegalArgumentException(msg);
    }
  }

  @Override
  public void alterar(Produto produto) {
    String sql = "update pcprodut set descricao = ? where codprod = ?";
    template.update(sql, produto.getDescricao(), produto.getCodigo());
  }

  @Override
  public void deletar(int codigoProduto) {
    String sql = "update pcprodut set dtexclusao = ? where codprod = ?";
    template.update(sql, DbLocalDateParser.to(LocalDateTime.now()), codigoProduto);

  }

}
