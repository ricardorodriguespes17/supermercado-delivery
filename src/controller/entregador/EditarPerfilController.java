package controller.entregador;

import controller.LoginController;
import controller.StageController;
import controller.supermercado.CadastroController;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Supermercado;
import model.Usuario;

public class EditarPerfilController implements Initializable {

    @FXML
    private TextField nome, cpf, email, nomeUsuario;
    @FXML
    private PasswordField senha;

    @FXML
    private ImageView imagem = new ImageView();

    @FXML
    private Label erroNome, erroNomeUsuario, erroSenha, erroEmail, erroCpf;

    Usuario entregador;

    // Declaração do selecionador de arquivo
    FileChooser fc = new FileChooser();
    // Declaração e inicialização do arquivo onde irá receber o arquivo selecionado
    File arquivoImagem = new File("src/imagens/usuario.png");

    // Metodo que seleciona uma imagem em algum diretório do computador
    @FXML
    private void adicionarImagem(ActionEvent event) {
        fc = new FileChooser();
        arquivoImagem = fc.showOpenDialog(null);

        if (arquivoImagem == null) {
            arquivoImagem = new File(entregador.getUrlImagem());
            return;
        }

        try {
            BufferedImage bi = ImageIO.read(arquivoImagem);
            Image image = SwingFXUtils.toFXImage(bi, null);
            imagem.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void confirmar(ActionEvent event) throws IOException {
        entregador.setNome(nome.getText());
        entregador.setUserName(nomeUsuario.getText());
        entregador.setCpf(cpf.getText());
        entregador.setEmail(email.getText());
        entregador.setSenha(senha.getText());
        if (arquivoImagem != null) {
            entregador.setImagem(arquivoImagem);
        }

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Perfil editado com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
        StageController.openScreen();
    }

    @FXML
    public void verificaNome(KeyEvent event) {
        if (nome.getText().equals("")) {
            erroNome.setVisible(true);
            nome.setStyle("-fx-border-col");
        } else {
            erroNome.setVisible(false);
            nome.setStyle("-fx-border-color: ");
        }
    }

    @FXML
    public void verificaNomeUsuario(KeyEvent event) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getUserName().equals(nomeUsuario.getText())) {
                erroNomeUsuario.setVisible(true);
                erroNomeUsuario.setText("Nome de Usuário já existe");
                nomeUsuario.setStyle("-fx-border-color: red;");
                return;
            } else if (nomeUsuario.getText().equals("")) {
                erroNomeUsuario.setVisible(true);
                erroNomeUsuario.setText("Campo obrigatório");
                nomeUsuario.setStyle("-fx-border-color: red;");
                return;
            } else {
                erroNomeUsuario.setVisible(false);
                nomeUsuario.setStyle("-fx-border-color: #00FF9A;");
            }
        }
    }

    @FXML
    public void verificaCpf(KeyEvent event) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getCpf() == null) {
                return;
            }
            if (u.getCpf().equals(cpf.getText())) {
                erroCpf.setVisible(true);
                erroCpf.setText("CPF já existe");
                cpf.setStyle("-fx-border-color: red;");
                return;
            } else {
                erroCpf.setVisible(false);
                cpf.setStyle("-fx-border-color: #00FF9A;");
            }
        }

        if (cpf.getLength() > 11 || cpf.getLength() < 11) {
            erroCpf.setVisible(true);
            erroCpf.setText("CPF inválido");
            cpf.setStyle("-fx-border-col");
        } else {
            erroCpf.setVisible(false);
            cpf.setStyle("-fx-border-color: ");
        }
    }

    @FXML
    public void verificaEmail(KeyEvent event) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getEmail() == null) {
                return;
            }
            if (u.getEmail().equals(email.getText())) {
                erroEmail.setVisible(true);
                erroEmail.setText("Email já cadastrado");
                email.setStyle("-fx-border-color: red;");
                return;
            } else {
                erroNomeUsuario.setVisible(false);
                email.setStyle("-fx-border-color: #00FF9A;");
            }
        }

    }

    @FXML
    public void verificaSenha(KeyEvent event) {
        if (senha.getText().equals("")) {
            senha.setStyle("-fx-border-color: red;");
            return;
        } else {
            erroSenha.setVisible(false);
            senha.setStyle("-fx-border-color: #00FF9A;");
        }
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
        StageController.openScreen();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getNome().equals(LoginController.uN.getNome())) {
                entregador = u;
                break;
            }
        }
        nome.setText(entregador.getNome());
        nomeUsuario.setText(entregador.getUserName());
        cpf.setText(entregador.getCpf());
        email.setText(entregador.getEmail());
        imagem.setImage(entregador.getImagem().getImage());
    }
}
