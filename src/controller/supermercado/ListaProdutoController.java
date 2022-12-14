package controller.supermercado;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.Produto;
import model.Supermercado;
import principal.Principal;
import static principal.Principal.root;

public class ListaProdutoController implements Initializable {

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
    private TextField caixaPesquisa;

    public static Produto produtoSelecionado = null;

    @FXML
    public void removerProduto(ActionEvent event) {
        Produto selectedItem = tabela.getSelectionModel().getSelectedItem();
        tabela.getItems().remove(selectedItem);

        for (Produto p : Supermercado.getProdutos()) {
            if (p.getCod() == cod.getCellData(selectedItem)) {
                File imagemProdutoRemovido = new File(p.getUrlImagem());
                imagemProdutoRemovido.delete();
                Supermercado.getProdutos().remove(p);
                break;
            }
        }

    }

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
    public void editarProduto(ActionEvent event) throws IOException {
        Produto selectedItem = tabela.getSelectionModel().getSelectedItem();
        produtoSelecionado = selectedItem;

        if (produtoSelecionado == null) {
            return;
        }

        Principal.root = FXMLLoader.load(getClass().getResource("/view/EditarProduto.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    //Pesquisa itens que tenha o mesmo nome que foi digitado no campo de texto
    @FXML
    public void pesquisar(KeyEvent event) {
        carregarTabela();
        String pesquisa = caixaPesquisa.getText();
        
        for (Produto p : Supermercado.getProdutos()) {
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
        observable = FXCollections.observableArrayList(Supermercado.getProdutos());

        nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        imagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
        cod.setCellValueFactory(new PropertyValueFactory<>("cod"));
        valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        quantidade.setCellValueFactory(new PropertyValueFactory<>("quant"));
        tabela.setItems(observable);

        //Formatando fonte da tabela
        tabela.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");
        tabela.getSelectionModel().selectFirst();
    }
}
