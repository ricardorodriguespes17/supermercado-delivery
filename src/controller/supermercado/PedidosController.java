package controller.supermercado;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import controller.StageController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Cliente;
import model.Notification;
import model.Order;
import model.User;
import principal.Principal;

public class PedidosController implements Initializable {

    @FXML
    private TableView<Order> tabela = new TableView<>();
    @FXML
    private TableColumn<Order, String> nomeProdutos = new TableColumn<>();
    @FXML
    private TableColumn<Order, String> solicitado = new TableColumn<>();
    @FXML
    private TableColumn<Order, String> dataHora = new TableColumn<>();

    private ObservableList<Order> observable;

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void confirmar(ActionEvent event) {
        Order selectedItem = tabela.getSelectionModel().getSelectedItem();
        selectedItem.setConfirmed(true);

        for (Order p : tabela.getSelectionModel().getSelectedItems()) {
            if (p.isConfirmed() == false) {
                tabela.getItems().remove(p);
            }
        }

        new Notification("Pedido confirmado", selectedItem.getUsername());
        for (User u : Principal.supermarketData.getUsers()) {
            if ("entregador".equals(u.getType())) {
                // Notificacao notificarEntregador = new Notificacao("Um pedido para ser
                // entregue", u);
                // u.getNotificacoes().add(notificarEntregador);
            }
        }

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Pedido confirmado");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        carregarTabela();
    }

    @FXML
    public void cancelar(ActionEvent event) {
        Order selectedItem = tabela.getSelectionModel().getSelectedItem();
        for (Cliente u : Principal.supermarketData.getClients()) {
            if (u.getUsername().equals(selectedItem.getUsername())) {
                u.getCart().getProdutosSolicitados().clear();
            }
        }

        // Notificacao n = new Notificacao("Pedido cancelado", selectedItem.getUser());
        // selectedItem.getUser().getNotificacoes().add(n);

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Pedido cancelado");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        tabela.getItems().remove(selectedItem);
        Principal.supermarketData.getOrders().remove(selectedItem);
        carregarTabela();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }

    public void carregarTabela() {
        observable = FXCollections.observableArrayList(Principal.supermarketData.getOrders());

        nomeProdutos.setCellValueFactory(new PropertyValueFactory<>("productsName"));
        solicitado.setCellValueFactory(new PropertyValueFactory<>("username"));
        dataHora.setCellValueFactory(new PropertyValueFactory<>("dateTime"));

        tabela.setItems(observable);

        tabela.setStyle("-fx-font-size: 12px; -fx-font-family: Source Sans Pro Extra Light;");

        for (Order p : Principal.supermarketData.getOrders()) {
            if (p.isConfirmed()) {
                tabela.getItems().remove(p);
            }
        }
    }
}
