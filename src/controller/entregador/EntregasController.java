package controller.entregador;

import java.io.IOException;
import java.net.URL;
import java.util.Collections;
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
import model.Order;
import model.Produto;
import principal.Principal;

public class EntregasController implements Initializable {

    @FXML
    private TableView<Order> tabela = new TableView<>();
    @FXML
    private TableColumn<Order, String> endereco = new TableColumn<>();
    @FXML
    private TableColumn<Order, String> solicitado = new TableColumn<>();
    @FXML
    private TableColumn<Order, String> dataHora = new TableColumn<>();

    private ObservableList<Order> observable;

    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        Order selectedItem = tabela.getSelectionModel().getSelectedItem();

        for (Produto p : selectedItem.getProdutosPedidos()) {
            // retira o produto do estoque
            p.setQuant(p.getQuant() - 1);
            // aumenta a quantidade de vezes comprada desse produto
            p.setNumeroVendido();
        }

        // ordena a lista de produtos
        Collections.sort(Principal.supermarketData.getProducts());

        selectedItem.getProdutosPedidos().clear();
        Principal.supermarketData.getOrders().remove(selectedItem);

        // Atualizar os itens da tabela
        carregarTabela();

        // Notificacao n = new Notificacao("Pedido está a caminho",
        // selectedItem.getUser());
        // selectedItem.getUser().getNotificacoes().add(n);

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Entrega confirmada");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
        StageController.openScreen();
        Principal.supermarketData.getOrders().remove(selectedItem);
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }

    // Carrega os dados da tabela de entregas
    public void carregarTabela() {
        observable = FXCollections.observableArrayList(Principal.supermarketData.getOrders());

        endereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        solicitado.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        dataHora.setCellValueFactory(new PropertyValueFactory<>("dataHora"));

        tabela.setItems(observable);

        // Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 12px; -fx-font-family: Source Sans Pro Extra Light;");

        for (Order p : Principal.supermarketData.getOrders()) {
            if (!p.isConfirmed()) {
                tabela.getItems().remove(p);
            }
        }
    }
}
