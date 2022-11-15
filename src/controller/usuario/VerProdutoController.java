package controller.usuario;

import controller.LoginController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import model.Cliente;
import model.Produto;
import principal.Principal;

public class VerProdutoController implements Initializable {

    @FXML
    private TableView<Produto> tabela = new TableView<>();
    @FXML
    private TableColumn<Produto, ImageView> imagem = new TableColumn<>();
    @FXML
    private TableColumn<Produto, String> nome = new TableColumn<>();
    @FXML
    private TableColumn<Produto, Integer> cod = new TableColumn<>();
    @FXML
    private TableColumn<Produto, String> valor = new TableColumn<>();
    @FXML
    private TableColumn<Produto, Integer> quantidade = new TableColumn<>();

    private ObservableList<Produto> observable;

    @FXML
    private Spinner<Integer> quant;

    @FXML
    private TextField caixaPesquisa;

    @FXML
    public void adicionarAoCarrinho(ActionEvent event) {
        Produto selectedItem = tabela.getSelectionModel().getSelectedItem();
        for (int i = 0; i < quant.getValue(); i++) {
            ((Cliente) LoginController.uN).getCart().getProdutosSolicitados().add(selectedItem);
        }

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Produto adicionado ao carrinho: \n" + selectedItem.getNome());
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();
    }

    @FXML
    public void verCarrinho(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/VerCarrinho.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void voltar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));
        StageController.openScreen();
    }

    // Pesquisa itens que tenha o mesmo nome que foi digitado no campo de texto
    @FXML
    public void pesquisar(KeyEvent event) {
        carregarTabela();
        String pesquisa = caixaPesquisa.getText();

        for (Produto p : Principal.supermarketData.getProducts()) {
            for (int i = 0; i < pesquisa.length(); i++) {
                if (p.getNomeProduto().charAt(i) != pesquisa.charAt(i)) {
                    tabela.getItems().remove(p);
                }
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }

    public void carregarTabela() {
        observable = FXCollections.observableArrayList(Principal.supermarketData.getProducts());

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        imagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quant"));

        tabela.setItems(observable);

        // Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");
        // Selecionando o primeiro item ao iniciar
        tabela.getSelectionModel().selectFirst();

        // Remover itens que tiverem quantidade igual a 0
        for (Produto p : Principal.supermarketData.getProducts()) {
            if (p.getQuant() == 0) {
                tabela.getItems().remove(p);
            }
        }

        // inicialização do Campo que seleciona o numero de quantidade do produto
        SpinnerValueFactory<Integer> valorQuantidade = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        quant.setValueFactory(valorQuantidade);
    }
}
