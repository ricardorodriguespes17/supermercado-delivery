package model;

import principal.Principal;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
    private String username;
    private String dataTime;
    private String address;
    private boolean confirmed;
    private List<Produto> produtosPedidos = new ArrayList<>();

    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm");

    public Order(Cliente client, String address) {
        Date dateTime = new Date();
        Cart c = client.getCart();
        this.produtosPedidos = c.getProdutosSolicitados();
        this.confirmed = false;
        this.address = address;
        this.username = client.getUsername();
        this.dataTime = formatTime.format(dateTime) + " - " + formatDate.format(dateTime);
        Principal.supermarketData.addOrder(this);
    }

    public double getValue() {
        double value = 0;

        for (Produto p : produtosPedidos) {
            value += p.getValorFormatado();
        }

        return value;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDateTime() {
        return dataTime;
    }

    public void setDateTime(String dateTime) {
        this.dataTime = dateTime;
    }

    public int getProductsCount() {
        return produtosPedidos.size();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProductsName() {
        String productsName = "";

        for (Produto p : produtosPedidos) {
            productsName += p.getNome() + "\n\n";
        }

        return productsName;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public List<Produto> getProdutosPedidos() {
        return produtosPedidos;
    }

    public void setProdutosPedidos(List<Produto> produtosPedidos) {
        this.produtosPedidos = produtosPedidos;
    }

}
