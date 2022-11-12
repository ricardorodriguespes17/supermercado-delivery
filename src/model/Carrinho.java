
package model;

import controller.LoginController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable{
    //ATRIBUTOS
    private double valor;
    private int cod;
    private Usuario usuario;
    private List<Produto> produtosSolicitados = new ArrayList<>();
    
    //CONSTRUTOR
    public Carrinho(){
        this.valor = 0;
        this.usuario = LoginController.uN;
        produtosSolicitados = new ArrayList<>();
    }
    
    //METODOS
    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<Produto> getProdutosSolicitados() {
        return produtosSolicitados;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
}
