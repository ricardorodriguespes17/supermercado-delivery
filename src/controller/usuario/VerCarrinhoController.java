package controller.usuario;

import controller.StageController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Cliente;
import model.Produto;
import principal.Principal;

public class VerCarrinhoController implements Initializable {

    @FXML
    private TableView<Produto> tabela = new TableView<>();
    @FXML
    private TableColumn<Produto, ImageView> imagem = new TableColumn<>();
    @FXML
    private TableColumn<Produto, String> nome = new TableColumn<>();
    @FXML
    private TableColumn<Produto, Integer> cod = new TableColumn<>();
    @FXML
    private TableColumn<Produto, Double> valor = new TableColumn<>();

    private ObservableList<Produto> observable;
    private Cliente loggedUser = (Cliente) Principal.supermarketData.getLoggedUser();

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/ConfirmarPedido.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void remover(ActionEvent event) {
        Produto selectedItem = tabela.getSelectionModel().getSelectedItem();

        for (Produto p : (loggedUser).getCart().getProdutosSolicitados()) {
            if (selectedItem.equals(p)) {
                (loggedUser).getCart().getProdutosSolicitados().remove(p);
                break;
            }
        }
        tabela.refresh();
    }

    @FXML
    public void limpar(ActionEvent event) {
        tabela.refresh();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }

    public void carregarTabela() {
        observable = FXCollections
                .observableArrayList((loggedUser).getCart().getProdutosSolicitados());

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        imagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));

        tabela.setItems(observable);

        // Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 12px; -fx-font-family: Source Sans Pro Extra Light;");
    }
}
