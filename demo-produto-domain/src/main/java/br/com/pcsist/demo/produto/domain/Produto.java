package br.com.pcsist.demo.produto.domain;

import java.util.Objects;

import org.apache.commons.lang3.Validate;

import br.com.pcsist.winthor.ferramenta.usuario.domain.Usuario;

public class Produto {

  private int codigo;
  private String descricao;
  private boolean ativo;

  public Produto() {
    super();
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    Validate.isTrue(codigo > 0, "Código do produto inválido");
    this.codigo = codigo;
  }

  public String getDescricao() {
    return descricao;
  }

  public void setDescricao(String descricao) {
    this.descricao = descricao;
  }

  public void setAtivo(boolean ativo) {
    this.ativo = ativo;
  }

  public boolean isAtivo() {
    return ativo;
  }

  public boolean inativar(Usuario usuario) {
    if (usuario.getLogin().equals("PCADMIN")) {
      ativo = false;
      return true;
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(codigo);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Produto other = (Produto) obj;
    return codigo == other.codigo;
  }

  @Override
  public String toString() {
    return String.format("Produto [codigo=%s, descricao=%s]", codigo, descricao);
  }

}
