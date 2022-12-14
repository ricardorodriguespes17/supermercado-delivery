package model;

import java.io.Serializable;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class Mensagem implements Serializable {
    // ATRIBUTOS
    private String m1;
    private Usuario remetente;
    private Usuario destinatario;
    private Date horario;
    private boolean visto;

    // CONSTRUTOR
    public Mensagem(String mensagem, Usuario remetente, Usuario destinatario) {
        this.m1 = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.visto = false;
        horario = new Date();
        remetente.setUltimaMensagem(horario);
        destinatario.setUltimaMensagem(horario);
        new Notificacao("Mensagem de " + remetente, destinatario);
        Supermercado.getMensagens().add(this);
    }

    // GETES E SETES
    public Label getM1() {
        Label m1 = new Label();
        m1.setPadding(new Insets(12, 17, 12, 17));
        if (this.remetente.getUserName().equals("admin")) {
            m1.setStyle("-fx-background-color: #F5F5F5;"
                    + "-fx-text-fill: black;"
                    + "-fx-border-radius: 50;"
                    + "-fx-background-radius: 50;");
        } else {
            m1.setStyle(m1.getStyle()
                    + "-fx-background-color: #00FF9A;"
                    + "-fx-text-fill: black;"
                    + "-fx-border-radius: 50;"
                    + "-fx-background-radius: 50;");
        }
        m1.setText(this.m1);
        return m1;
    }

    public void setM1(String m1) {
        this.m1 = m1;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
        this.destinatario = destinatario;
    }

    public boolean isVisto() {
        return visto;
    }

    public void setVisto(boolean visto) {
        this.visto = visto;
    }

    public long getHora() {
        return horario.getTime();
    }

    public String getData() {
        return horario.toString();
    }

}
