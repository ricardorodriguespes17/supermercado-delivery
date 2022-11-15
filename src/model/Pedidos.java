package model;

import controller.LoginController;
import principal.Principal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pedidos implements Serializable {
    // ATRIBUTOS
    private double valorPedidos = 0;
    private Usuario usuario;
    private String dataHora;
    private String numProdutos;
    private String nomeProdutos = "";
    private String endereco;
    private boolean confirmado;
    private List<Produto> produtosPedidos = new ArrayList<>();

    // CONSTRUTOR
    public Pedidos(Usuario usuario, String endereco) {
        // Receber e formatar a data e a hora do sistema
        // formato da data
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");
        // formato da hora
        SimpleDateFormat formatoHora = new SimpleDateFormat("HH:mm");
        // Criando objeto 'Date dh' para receber o data do sistem
        Date dh = new Date();

        // dados do carrinho
        Cart c = LoginController.uN.getCarrinho();
        for (Produto p : c.getProdutosSolicitados()) {
            this.nomeProdutos += p.getNome() + "\n\n";
            this.produtosPedidos.add(p);
        }
        this.confirmado = false;
        this.endereco = endereco;
        this.numProdutos = c.getProdutosSolicitados().size() + " produtos";
        this.valorPedidos = c.getValor();
        this.usuario = usuario;
        this.dataHora = formatoHora.format(dh) + " - " + formatoData.format(dh);
        Principal.supermarketData.addOrder(this);
    }

    // METODOS
    public double getValorPedidos() {
        return valorPedidos;
    }

    public void setValorPedidos(double valor) {
        valorPedidos = valor;
    }

    public String getUsuario() {
        return usuario.getNome();
    }

    public Usuario getUser() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getNumProdutos() {
        return numProdutos;
    }

    public void setNumProdutos(String numProdutos) {
        this.numProdutos = numProdutos;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNomeProdutos() {
        return nomeProdutos;
    }

    public void setNomeProdutos(String nomeProdutos) {
        this.nomeProdutos = nomeProdutos;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public List<Produto> getProdutosPedidos() {
        return produtosPedidos;
    }

    public void setProdutosPedidos(List<Produto> produtosPedidos) {
        this.produtosPedidos = produtosPedidos;
    }

}
