package controller.supermercado;

import controller.LoginController;
import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class TelaAdminController implements Initializable {

    @FXML
    private Label logado;

    @FXML
    public void abrirMensagens() throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Mensagens.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void cadastrarProduto(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/CadastroProduto.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void listarProdutos(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/ListaProduto.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void pedidos(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Pedidos.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void cadastrarEntregador(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/CadastrarEntregador.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void removerEntregador(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/RemoverEntregador.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void listarUsuario(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/ListaUsuario.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void sairDaConta(ActionEvent event) throws Exception {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logado.setText("Você está logado como " + LoginController.uN.getName());
    }

}
