
package controller.entregador;

import controller.LoginController;
import controller.usuario.TelaUsuarioController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;
import model.Notificacao;
import model.Supermercado;
import model.Usuario;
import principal.Principal;
import static principal.Principal.root;

public class TelaEntregadorController implements Initializable {
    
    @FXML
    private Label logado;
    @FXML
    private ImageView fotoPerfil = new ImageView();
    
    @FXML
    private MenuButton menu = new MenuButton();

    @FXML
    private TableView<Notificacao> not = new TableView<>();
    @FXML
    private TableColumn<Notificacao, String> info = new TableColumn<>();

    @FXML
    private ImageView iconeNot = new ImageView();

    private ObservableList<Notificacao> obs;
    
    @FXML
    public void notificacaoPedido(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Entregas.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    @FXML
    public void notificacaoPedido(MouseEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Entregas.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void pedidos(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Entregas.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void editarPerfil(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/EditarPerfil.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }
    
    @FXML
    public void removerEntregador(ActionEvent event) throws IOException{
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
        for(Usuario u : Supermercado.getUsers()){
            if(u.getNome().equals(LoginController.uN)){
                Supermercado.getUsers().remove(u);
                break;
            }
        }
    }
    
    @FXML
    public void sairDaConta(ActionEvent event) throws Exception {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logado.setText(LoginController.uN.getNome());
        carregarFotoPerfil();
        try {
            carregarNotificacao();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaEntregadorController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void carregarNotificacao() throws FileNotFoundException {
        obs = FXCollections.observableArrayList(LoginController.uN.getNotificacoes());
        info.setCellValueFactory(new PropertyValueFactory<>("info"));
        not.setItems(obs);
        
        for(Notificacao n : not.getItems()){
            if(n.isVisto()){
                FileInputStream fis = new FileInputStream("src/imagens/notificacao_inativa.png");
                Image image = new Image(fis);
                iconeNot = new ImageView(image);
                break;
            }else{
                FileInputStream fis = new FileInputStream("src/imagens/notificacao_ativa.png");
                Image image = new Image(fis);
                iconeNot = new ImageView(image);
                break;
            }
        }
    }
    
    public void carregarFotoPerfil() {
        File arquivo = new File(LoginController.uN.getUrlImagem());
        BufferedImage bufferedImage;
        Image image = null;
        try {
            bufferedImage = ImageIO.read(arquivo);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException ex) {
            Logger.getLogger(TelaUsuarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
        fotoPerfil.setImage(image);
        fotoPerfil.setFitHeight(60);
        fotoPerfil.setFitWidth(60);

        Rectangle clip = new Rectangle(60, 60);
        clip.setArcWidth(100);
        clip.setArcHeight(100);
        fotoPerfil.setClip(clip);

        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage wi = fotoPerfil.snapshot(parameters, null);
    }
    
}
