package controller.usuario;

import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import model.Cliente;
import model.Pedidos;
import principal.Principal;

public class ConfirmarPedidoController implements Initializable {

    // Campos de Texto da tela, para digitar os dados residenciais
    @FXML
    private TextField rua, num, bairro, ref, complemento;

    // variavel que receberá usuario que está logado
    private Cliente loggedUser = (Cliente) Principal.supermarketData.getLoggedUser();

    // Confirma o pedido feito pelo cliente
    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        // string para armazenar o endereco passado pelo cliente
        String endereco = rua.getText() + " Nº" + num.getText() + "\n" + complemento.getText() + "\n" + bairro.getText()
                + "\n" + ref.getText();

        // criar novo pedido
        new Pedidos(loggedUser, endereco);

        // Notificacao n = new Notificacao("Pedido para ser confirmado", admin);

        // cria um pop-up para confirmar a realizacao do pedido
        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Pedido feito com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        // limpa o carrinho do usuario
        (loggedUser).getCart().getProdutosSolicitados().clear();

        // Salvar os dados residenciais escolhido pelo cliente

        loggedUser.setLogradouro(rua.getText());
        loggedUser.setNumeroCasa(num.getText());
        loggedUser.setBairro(bairro.getText());
        loggedUser.setPontoReferencia(ref.getText());
        loggedUser.setComplemento(complemento.getText());

        // volta para tela principal do usuario
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
        StageController.openScreen();
    }

    // cancela o confirmamento do pedido
    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        // volta para tela do carrinho
        StageController.root = FXMLLoader.load(getClass().getResource("/view/VerCarrinho.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarEndereco();
    }

    // carrega os dados residenciais salvos pelo cliente
    public void carregarEndereco() {
        rua.setText(loggedUser.getLogradouro());
        num.setText(loggedUser.getNumeroCasa());
        bairro.setText(loggedUser.getBairro());
        ref.setText(loggedUser.getPontoReferencia());
        complemento.setText(loggedUser.getComplemento());
    }

}
