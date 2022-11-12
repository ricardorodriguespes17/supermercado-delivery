package controller.supermercado;

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
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Produto;
import principal.Principal;
import static principal.Principal.root;

public class CadastroController implements Initializable {

    //Campos de textos da tela
    @FXML
    private TextField nome, marca, peso, valor;
    //Area de texto para escrever a descricao do produto
    @FXML
    private TextArea descricao;

    //Campo de imagem da tela
    @FXML
    private ImageView imagem = new ImageView();

    //Campo que seleciona o numero da quantidade de produto
    @FXML
    private Spinner quant;

    //Strings usadas no metodo 'confirmar()' para receber o valor dos Campos de texto e passar para os produto
    private String n, m, pe, d;
    private double v;
    private int q;

    //Declaração do selecionador de arquivo
    FileChooser fc = new FileChooser();
    //Declaração e inicialização do arquivo onde irá receber o arquivo selecionado
    File arquivoImagem = new File("src/imagens/semImagem.png");

    //Metodo que seleciona uma imagem em algum diretório do computador
    @FXML
    private void adicionarImagem(ActionEvent event) {
        fc = new FileChooser();
        arquivoImagem = fc.showOpenDialog(null);

        if (arquivoImagem == null) {
            arquivoImagem = new File("src/imagens/semImagem.png");
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

    //Metodo para voltar a tela inicial do administrador
    @FXML
    public void voltar(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        //Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    //Metodo para confirmar o cadastro do produto
    @FXML
    public void confirmar(ActionEvent event) throws InterruptedException {
        n = nome.getText();
        d = descricao.getText();
        m = marca.getText();

        if (n.equals("") || d.equals("") || m.equals("")) {
            return;
        }

        pe = peso.getText();
        q = (int) quant.getValue();
        v = Double.parseDouble(valor.getText());
        Produto p = new Produto(n, m, pe, v, q, d, arquivoImagem);

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Produto cadastrado com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        try {
            voltar(event);
        } catch (IOException ex) {
            Logger.getLogger(CadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    public void valorCorreto(KeyEvent event) {
        int cont = 0;
        if((event.getCode().ordinal() > 33 || event.getCode().ordinal() < 23) 
                && event.getCode().ordinal() != 1){
            if(event.getCode().ordinal() == 22){
                for(int i = 0; i < valor.getText().length(); i++){
                    if(valor.getText().charAt(i) == '.'){
                        cont++;
                        if(cont > 1){
                            valor.deletePreviousChar();
                        }
                    }
                }
            }else{
                valor.deletePreviousChar();
            }
            
        }
    }

    //Metodo para Limpar os campos de texto da tela
    @FXML
    public void limpar(ActionEvent event) {
        nome.setText("");
        valor.setText("");
    }

    //Metodo de inicialização da tela
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //inicialização do Campo que seleciona o numero de quantidade do produto
        SpinnerValueFactory<Integer> valorQuantidade
                = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 1);
        quant.setValueFactory(valorQuantidade);
    }

}
