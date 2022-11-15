package model;

import java.io.Serializable;
import java.util.Date;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import principal.Principal;

public class Mensagem implements Serializable {
    // ATRIBUTOS
    private String m1;
    private User remetente;
    private User destinatario;
    private Date horario;
    private boolean visto;

    // CONSTRUTOR
    public Mensagem(String mensagem, User remetente, User destinatario) {
        this.m1 = mensagem;
        this.remetente = remetente;
        this.destinatario = destinatario;
        this.visto = false;
        horario = new Date();
        remetente.setLastMessageDate(horario);
        destinatario.setLastMessageDate(horario);
        new Notification("Mensagem de " + remetente, destinatario);
        Principal.supermarketData.addMessage(this);
    }

    // GETES E SETES
    public Label getM1() {
        Label m1 = new Label();
        m1.setPadding(new Insets(12, 17, 12, 17));
        if (this.remetente.getUsername().equals("admin")) {
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

    public User getRemetente() {
        return remetente;
    }

    public void setRemetente(User remetente) {
        this.remetente = remetente;
    }

    public User getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(User destinatario) {
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
