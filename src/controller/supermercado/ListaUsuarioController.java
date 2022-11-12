
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import model.Supermercado;
import model.Usuario;
import principal.Principal;
import static principal.Principal.root;

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
    public void voltar(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
       Principal.palco.centerOnScreen();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarTabela();
    }
    
    //Carrega a TableView com os dados dos usuários
    public void carregarTabela(){
        observable = FXCollections.observableArrayList(Supermercado.getUsers());
       
       nome.setCellValueFactory(new PropertyValueFactory<>("nome"));
       imagem.setCellValueFactory(new PropertyValueFactory<>("imagem"));
       cpf.setCellValueFactory(new PropertyValueFactory<>("cpf"));
       email.setCellValueFactory(new PropertyValueFactory<>("email"));
       tabela.setItems(observable);;

       
       //Formatando fonte da tabela
       tabela.setStyle("-fx-font-size: 15px; -fx-font-family: Source Sans Pro Extra Light;");
       //Selecionando primeiro item da tabela ao iniciar
       tabela.getSelectionModel().selectFirst();
    }
    
}
