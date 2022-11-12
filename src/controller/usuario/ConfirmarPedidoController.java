package controller.usuario;

import controller.LoginController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Pedidos;
import model.Usuario;
import principal.Principal;
import static principal.Principal.root;

public class ConfirmarPedidoController implements Initializable {

    //Campos de Texto da tela, para digitar os dados residenciais
    @FXML
    private TextField rua, num, bairro, ref, complemento;

    //variavel que receberá usuario que está logado
    Usuario u = LoginController.uN;

    //Confirma o pedido feito pelo cliente
    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        //string para armazenar o endereco passado pelo cliente
        String endereco = rua.getText() + " Nº" + num.getText() + "\n" + complemento.getText() + "\n" + bairro.getText() + "\n" + ref.getText();

        //criar novo pedido
        Pedidos p = new Pedidos(LoginController.uN, endereco);
        
        //Notificacao n = new Notificacao("Pedido para ser confirmado", admin);

        //cria um pop-up para confirmar a realizacao do pedido
        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Pedido feito com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        //limpa o carrinho do usuario
        LoginController.uN.getCarrinho().getProdutosSolicitados().clear();

        //Salvar os dados residenciais escolhido pelo cliente
        u.setLogradouro(rua.getText());
        u.setNumeroCasa(num.getText());
        u.setBairro(bairro.getText());
        u.setPontoReferencia(ref.getText());
        u.setComplemento(complemento.getText());

        //volta para tela principal do usuario
        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    //cancela o confirmamento do pedido
    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        //volta para tela do carrinho
        Principal.root = FXMLLoader.load(getClass().getResource("/view/VerCarrinho.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarEndereco();
    }

    //carrega os dados residenciais salvos pelo cliente
    public void carregarEndereco() {
        rua.setText(u.getLogradouro());
        num.setText(u.getNumeroCasa());
        bairro.setText(u.getBairro());
        ref.setText(u.getPontoReferencia());
        complemento.setText(u.getComplemento());
    }

}
