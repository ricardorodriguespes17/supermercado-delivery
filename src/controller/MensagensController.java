package controller;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Mensagem;
import model.User;
import principal.Principal;

public class MensagensController implements Initializable {

    @FXML
    private TableView<User> topicos = new TableView<>();
    @FXML
    private TableColumn<User, ImageView> fotoUsuario = new TableColumn<>();
    @FXML
    private TableColumn<User, String> nomeUsuario = new TableColumn<>();
    @FXML
    TextArea mensagem;
    @FXML
    private TableView<Mensagem> conversa = new TableView<>();
    @FXML
    private TableColumn<Mensagem, Label> mensagens = new TableColumn<>();

    private ObservableList<User> observableUsuario;
    private ObservableList<Mensagem> observableMensagem;
    private int j = 0;
    private User loggedUser = Principal.supermarketData.getLoggedUser();

    @FXML
    public void quebra(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            j = 0;
        }
        if (j > 40) {
            if (" ".equals(event.getText()) && mensagem.getText().length() > 40) {
                mensagem.setText(mensagem.getText() + "\n");
                mensagem.end();
                j = 0;
                return;
            }
        }
        j++;

    }

    @FXML
    public void enviarKey(KeyEvent event) {
        if (event.getCode().equals(KeyCode.ENTER)) {
            if (mensagem.getText().equals("")) {
                return;
            }

            User u = topicos.getSelectionModel().getSelectedItem();

            new Mensagem(mensagem.getText(),
                    loggedUser, u);

            mensagem.clear();
            carregarMensagens();
        }
    }

    @FXML
    public void enviar(ActionEvent event) {
        if (mensagem.getText().equals("")) {
            return;
        }

        User u = topicos.getSelectionModel().getSelectedItem();

        if (u == null) {
            return;
        }

        new Mensagem(mensagem.getText(), loggedUser, u);

        mensagem.clear();
        mensagem.setFocusTraversable(true);
        carregarMensagens();
        carregarTopicos();
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        if ("cliente".equals(loggedUser.getType())) {
            StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
            StageController.openScreen();
        } else {
            StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
            StageController.openScreen();
        }
    }

    @FXML
    public void abrirTopico(MouseEvent event) {
        User u = topicos.getSelectionModel().getSelectedItem();
        mensagens.setText(u.getName());
        for (Mensagem m : Principal.supermarketData.getMessages()) {
            if (m.getDestinatario().equals(u)) {
                m.setVisto(true);
            }
        }

        carregarMensagens();
    }

    @FXML
    public void pesquisar(ActionEvent event) {
        // ainda nao implementado
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTopicos();
        carregarMensagens();
    }

    public void carregarTopicos() {
        // carregar lista para a tabela
        observableUsuario = FXCollections.observableArrayList(Principal.supermarketData.getUsers());

        Collections.sort(observableUsuario);

        // Carregar dados da tabela
        fotoUsuario.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        nomeUsuario.setCellValueFactory(new PropertyValueFactory<>("userName"));
        topicos.setItems(observableUsuario);

        // Formatando fonte da tabela
        topicos.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");

        // Selecionar o primeiro topico de conversa
        topicos.getSelectionModel().selectFirst();
        if (loggedUser.getType().equals("admin")) {
            topicos.getSelectionModel().selectNext();
        }
        User user = topicos.getSelectionModel().getSelectedItem();
        mensagens.setText(user.getName());
        carregarMensagens();

        // Tirar as mensagens de outros usuarios da tabela de mensagens
        for (User u : Principal.supermarketData.getUsers()) {
            if (loggedUser.getUsername().equals("admin")) {
                if (u.getUsername().equals("admin") || u.getType().equals("entregador")) {
                    topicos.getItems().remove(u);
                }
            } else {
                if (u.getType().equals("cliente") || u.getType().equals("entregador")) {
                    topicos.getItems().remove(u);
                }
            }
        }

    }

    public void carregarMensagens() {
        observableMensagem = FXCollections.observableArrayList(Principal.supermarketData.getMessages());

        mensagens.setCellValueFactory(new PropertyValueFactory<>("m1"));
        conversa.setItems(observableMensagem);

        // Formatando fonte da tabela
        conversa.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");
        // tabela.getSelectionModel().selectLast();

        User u = topicos.getSelectionModel().getSelectedItem();
        User a = loggedUser;

        for (Mensagem m : Principal.supermarketData.getMessages()) {
            if (a.getUsername().equals("admin")) {
                if (m.getDestinatario().getName().equals(u.getName())
                        || m.getRemetente().getName().equals(u.getName())) {

                } else {
                    conversa.getItems().remove(m);
                }
            } else {
                if (m.getDestinatario().getName().equals(a.getName())
                        || m.getRemetente().getName().equals(a.getName())) {

                } else {
                    conversa.getItems().remove(m);
                }
            }

        }

    }

}
