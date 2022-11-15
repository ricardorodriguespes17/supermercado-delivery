package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Date;

public class Usuario implements Serializable, Comparable<Usuario> {
    private String userName;
    private String nome;
    private String senha;
    private String tipo;
    private Cart cart;
    private Date ultimaMensagem;

    public Usuario(String nome, String userName, String senha, String tipo) {
        this.nome = nome;
        this.userName = userName;
        this.senha = senha;
        this.tipo = tipo;
        this.cart = new Cart();
        ultimaMensagem = new Date();
        Supermercado.getUsers().add(this);
    }

    public final void copia(File fonte, File destino) throws IOException {
        OutputStream out;
        try (InputStream in = new FileInputStream(fonte)) {
            out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
        out.close();
    }

    public String getUserName() {
        return userName;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public Cart getCarrinho() {
        return cart;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCarrinho(Cart pedidos) {
        this.cart = pedidos;
    }

    public Date getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(Date ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }

    @Override
    public int compareTo(Usuario o) {
        if (ultimaMensagem.before(o.ultimaMensagem)) {
            return 1;
        }
        if (ultimaMensagem.after(o.ultimaMensagem)) {
            return -1;
        }
        return 0;
    }

}
