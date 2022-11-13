package model;

import java.io.Serializable;

public class Endereco implements Serializable {
    // ATRIBUTOS
    private String logradouro;
    private String numeroCasa;
    private String bairro;
    private String complemento;
    private String pontoReferencia;

    public Endereco() {
        this.logradouro = "";
        this.numeroCasa = "";
        this.bairro = "";
        this.complemento = "";
        this.pontoReferencia = "";
    }

    public Endereco(String logradouro, String numeroCasa, String bairro, String complemento, String pontoReferencia) {
        this.logradouro = logradouro;
        this.numeroCasa = numeroCasa;
        this.bairro = bairro;
        this.complemento = complemento;
        this.pontoReferencia = pontoReferencia;
    }

    // GETERES E SETERES
    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumeroCasa() {
        return numeroCasa;
    }

    public void setNumeroCasa(String numeroCasa) {
        this.numeroCasa = numeroCasa;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getPontoReferencia() {
        return pontoReferencia;
    }

    public void setPontoReferencia(String pontoReferencia) {
        this.pontoReferencia = pontoReferencia;
    }

}
