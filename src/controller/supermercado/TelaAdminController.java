package controller.supermercado;

import controller.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import principal.Principal;
import static principal.Principal.root;

public class TelaAdminController implements Initializable {
    
    //Campo de texto que titula a tela
    @FXML
    private Label logado;
    
    @FXML
    public void abrirMensagens() throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Mensagens.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void cadastrarProduto(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/CadastroProduto.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void listarProdutos(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/ListaProduto.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void pedidos(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Pedidos.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void cadastrarEntregador(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/CadastrarEntregador.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void removerEntregador(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/RemoverEntregador.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void listarUsuario(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/ListaUsuario.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void sairDaConta(ActionEvent event) throws Exception {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        //Adicionando nome do usuario ao titulo
        logado.setText("Você está logado como " + LoginController.uN.getNome());
    }

}
