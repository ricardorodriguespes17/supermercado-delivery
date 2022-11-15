
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Usuario;
import principal.Principal;

public class ListaUsuarioController implements Initializable {

    @FXML
    private TableView<Usuario> tabela = new TableView<>();
    @FXML
    private TableColumn<Usuario, ImageView> imagem = new TableColumn<>();
    @FXML
    private TableColumn<Usuario, String> nome = new TableColumn<>();
    @FXML
    private TableColumn<Usuario, String> cpf = new TableColumn<>();
    @FXML
    private TableColumn<Usuario, String> email = new TableColumn<>();

    private ObservableList<Usuario> observable;

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
        observable = FXCollections.observableArrayList(Principal.supermarketData.getUsers());

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        imagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        tabela.setItems(observable);
        ;

        // Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");
        // Selecionando primeiro item da tabela ao iniciar
        tabela.getSelectionModel().selectFirst();
    }

}
