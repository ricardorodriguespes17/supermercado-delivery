package model;

import java.io.Serializable;

public class Notificacao implements Serializable{
    //ATRIBUTOS
    private String info;
    private boolean visto;
    private Usuario usuario;

    //CONSTRUTOR
    public Notificacao(String info, Usuario usuario){
        this.info = info;
        this.visto = false;
        this.usuario = usuario;
        usuario.getNotificacoes().add(this);
    }
    
    //METODOS
    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
