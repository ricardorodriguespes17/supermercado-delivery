
package model;

import controller.LoginController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable {
    private int cod;
    private String username;
    private List<Produto> items = new ArrayList<>();

    public Cart() {
        username = LoginController.uN.getUserName();
        items = new ArrayList<>();
    }

    public double getValor() {
        int value = 0;

        for (Produto product : items) {
            value += product.getValorFormatado();
        }

        return value;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<Produto> getProdutosSolicitados() {
        return items;
    }

    public String getUsuario() {
        return username;
    }

}
