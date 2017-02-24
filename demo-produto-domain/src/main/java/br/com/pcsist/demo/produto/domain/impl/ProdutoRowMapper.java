package br.com.pcsist.demo.produto.domain.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import org.springframework.jdbc.core.RowMapper;

import br.com.pcsist.demo.produto.domain.Produto;
import br.com.pcsist.winthor.core.servico.data.DbParser;

final class ProdutoRowMapper implements RowMapper<Produto> {

  @Override
  public Produto mapRow(ResultSet rs, int rowNum) throws SQLException {
    DbParser dbParser = DbParser.valueOf(rs);

    LocalDate dataExclusao = dbParser.date().of("dtexclusao");

    Produto produto = new Produto();
    produto.setCodigo(rs.getInt("codprod"));
    produto.setDescricao(rs.getString("descricao"));
    produto.setDataExclusao(dataExclusao);


    return produto;
  }

}
