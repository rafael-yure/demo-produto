package br.com.pcsist.demo.produto.jackson;

public class ProdutoDto {

  private String codigo;
  private String nome;

  public String getCodigo() {
    return codigo;
  }

  public void setCodigo(String codigo) {
    this.codigo = codigo;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

}
