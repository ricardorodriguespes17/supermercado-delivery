package controller.supermercado;

import java.io.File;
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
import javafx.scene.image.ImageView;
import model.Entregador;
import model.User;
import principal.Principal;

public class RemoverEntregadorController implements Initializable {

    @FXML
    private final TableView<Entregador> tabela = new TableView<>();
    @FXML
    private final TableColumn<Entregador, ImageView> imagem = new TableColumn<>();
    @FXML
    private final TableColumn<Entregador, String> nome;
    @FXML
    private final TableColumn<Entregador, String> cpf = new TableColumn<>();
    @FXML
    private final TableColumn<Entregador, String> email = new TableColumn<>();

    private ObservableList<Entregador> observable;

    public RemoverEntregadorController() {
        this.nome = new TableColumn<>();
    }

    @FXML
    public void remover(ActionEvent event) throws IOException {
        Entregador selectedItem = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectedItem);

        for (Entregador e : Principal.supermarketData.getDeliveryPeoples()) {
            if (e.getCpf().equals(selectedItem.getCpf())) {
                File imagemProdutoRemovido = new File(e.getUrlImagem());
                imagemProdutoRemovido.delete();
                Principal.supermarketData.getDeliveryPeoples().remove(e);

                Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
                confirmacao.setTitle("Remoção");
                confirmacao.setHeaderText("");
                confirmacao.setContentText("Conta removida com sucesso!");
                confirmacao.setResult(ButtonType.OK);
                confirmacao.showAndWait();
                break;
            }
        }
        tabela.refresh();
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }

    // Carrega a TableView com os dados dos usuários
    public void carregarTabela() {
        observable = FXCollections.observableArrayList(Principal.supermarketData.getDeliveryPeoples());

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        imagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabela.setItems(observable);

        // Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");
        // Selecionando primeiro item da tabela ao iniciar
        tabela.getSelectionModel().selectFirst();

        for (User u : Principal.supermarketData.getUsers()) {
            if (!"entregador".equals(u.getType())) {
                tabela.getItems().remove(u);
            }
        }
    }

}
