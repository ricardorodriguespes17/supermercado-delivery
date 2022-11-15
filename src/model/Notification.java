package model;

import java.io.Serializable;

public class Notification implements Serializable {
    private String content;
    private boolean viewed;
    private String username;

    public Notification(String content, Usuario usuario) {
        this.content = content;
        this.viewed = false;
        this.username = usuario.getUserName();
    }

    public String getInfo() {
        return content;
    }

    public void setInfo(String content) {
        this.content = content;
    }

    public boolean isVisto() {
        return viewed;
    }

    public void setVisto(boolean viewed) {
        this.viewed = viewed;
    }

    public String getUsuario() {
        return username;
    }

    public void setUsuario(String username) {
        this.username = username;
    }

}
