package controller.supermercado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Notificacao;
import model.Pedidos;
import model.Produto;
import model.Supermercado;
import model.Usuario;
import principal.Principal;
import static principal.Principal.root;

public class PedidosController implements Initializable {

    @FXML
    private TableView<Pedidos> tabela = new TableView<>();
    @FXML
    private TableColumn<Pedidos, String> nomeProdutos = new TableColumn<>();
    @FXML
    private TableColumn<Pedidos, String> solicitado = new TableColumn<>();
    @FXML
    private TableColumn<Pedidos, String> dataHora = new TableColumn<>();

    private ObservableList<Pedidos> observable;

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void confirmar(ActionEvent event) {
        Pedidos selectedItem = tabela.getSelectionModel().getSelectedItem();
        selectedItem.setConfirmado(true);

        for (Pedidos p : tabela.getSelectionModel().getSelectedItems()) {
            if (p.isConfirmado() == false) {
                tabela.getItems().remove(p);
            }
        }

        //Mandar notificação para o usuario
        Notificacao notificarCliente = new Notificacao("Pedido confirmado", selectedItem.getUser());
        for (Usuario u : Supermercado.getUsers()) {
            if ("entregador".equals(u.getTipo())) {
                Notificacao notificarEntregador = new Notificacao("Um pedido para ser entregue", u);
                u.getNotificacoes().add(notificarEntregador);
            }
        }

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Pedido confirmado");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        //Atualizar tabela
        carregarTabela();
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Pedidos selectedItem = tabela.getSelectionModel().getSelectedItem();
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getNome().equals(selectedItem.getUsuario())) {
                for (Produto p : u.getCarrinho().getProdutosSolicitados()) {
                    u.getCarrinho().getProdutosSolicitados().clear();
                    break;
                }
            }
        }

        //Mandar notificação para o usuario
        Notificacao n = new Notificacao("Pedido cancelado", selectedItem.getUser());
        selectedItem.getUser().getNotificacoes().add(n);

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Pedido cancelado");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        tabela.getItems().remove(selectedItem);
        Supermercado.getPedidos().remove(selectedItem);
        carregarTabela();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }

    public void carregarTabela() {
        observable = FXCollections.observableArrayList(Supermercado.getPedidos());

        nomeProdutos.setCellValueFactory(new PropertyValueFactory<>("nomeProdutos"));
        solicitado.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        dataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));

        tabela.setItems(observable);

        //Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 12px; -fx-font-family: Source Sans Pro Extra Light;");

        for (Pedidos p : Supermercado.getPedidos()) {
            if (p.isConfirmado()) {
                tabela.getItems().remove(p);
            }
        }
    }
}
