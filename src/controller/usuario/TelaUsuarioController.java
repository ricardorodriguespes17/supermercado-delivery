package controller.usuario;

import controller.StageController;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;

import model.Cliente;
import model.Notification;
import principal.Principal;

public class TelaUsuarioController implements Initializable {

    @FXML
    private Label logado;
    @FXML
    private final ImageView fotoPerfil = new ImageView();
    @FXML
    private final MenuButton menu = new MenuButton();
    @FXML
    private final TableView<Notification> not = new TableView<>();
    @FXML
    private final TableColumn<Notification, String> info = new TableColumn<>();
    @FXML
    private ImageView iconeNot = new ImageView();

    private Cliente loggedUser = (Cliente) Principal.supermarketData.getLoggedUser();

    @FXML
    public void abrirMensagens() throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Mensagens.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void verCarrinho(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/VerCarrinho.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void editarPerfil(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/EditarPerfilCliente.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void verProdutos(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/VerProduto.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void sairDaConta(ActionEvent event) throws Exception {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarFotoPerfil();
        // try {
        // carregarNotificacao();
        // } catch (FileNotFoundException ex) {
        // ex.printStackTrace();
        // }

        logado.setText(loggedUser.getName());
    }

    // public void carregarNotificacao() throws FileNotFoundException {
    // obs =
    // FXCollections.observableArrayList(loggedUser.getNotificacoes());
    // info.setCellValueFactory(new PropertyValueFactory<>("info"));
    // not.setItems(obs);

    // for (Notificacao n : not.getItems()) {
    // if (n.isVisto()) {
    // FileInputStream fis = new
    // FileInputStream("src/imagens/notificacao_inativa.png");
    // Image image = new Image(fis);
    // iconeNot = new ImageView(image);
    // break;
    // } else {
    // FileInputStream fis = new
    // FileInputStream("src/imagens/notificacao_ativa.png");
    // Image image = new Image(fis);
    // iconeNot = new ImageView(image);
    // break;
    // }
    // }
    // }

    public void carregarFotoPerfil() {
        File arquivo = new File(loggedUser.getUrlImagem());
        BufferedImage bufferedImage;
        Image image = null;
        try {
            if (!arquivo.exists()) {
                // SE NAO EXISTIR IMAGEM NO DIRETORIO, USUARA IMAGEM GENERICA
                loggedUser.setUrlImagem("src/imagens/usuario.png");
                arquivo = new File(loggedUser.getUrlImagem());
            }
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
        fotoPerfil.snapshot(parameters, null);
    }

}
