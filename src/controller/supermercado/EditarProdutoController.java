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
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;
import model.Produto;
import principal.Principal;
import static principal.Principal.root;

public class EditarProdutoController implements Initializable {

    @FXML
    private TextField nome, marca, peso, valor;
    @FXML
    private TextArea descricao;

    @FXML
    private Spinner<Integer> quant;

    @FXML
    private ImageView imagem = new ImageView();

    Produto p = ListaProdutoController.produtoSelecionado;
    FileChooser fc = new FileChooser();
    File arquivoImagem = new File(p.getUrlImagem());

    @FXML
    private void adicionarImagem(ActionEvent event) {
        fc = new FileChooser();
        arquivoImagem = fc.showOpenDialog(null);

        if (arquivoImagem == null) {
            arquivoImagem = new File(p.getUrlImagem());
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
        p.setNome(nome.getText(), marca.getText(), descricao.getText(), peso.getText());
        p.setNomeProduto(nome.getText());
        p.setMarca(marca.getText());
        p.setPeso(peso.getText());
        p.setValor(Double.parseDouble(valor.getText()));
        p.setQuant((int) quant.getValue());
        p.setDescricao(descricao.getText());
        if (arquivoImagem != null) {
            p.setImagem(arquivoImagem.toString());
        }

        Alert confirmacao = new Alert(Alert.AlertType.INFORMATION);
        confirmacao.setTitle("Confirmação");
        confirmacao.setHeaderText("");
        confirmacao.setContentText("Produto editado com sucesso!");
        confirmacao.setResult(ButtonType.OK);
        confirmacao.showAndWait();

        Principal.root = FXMLLoader.load(getClass().getResource("/view/ListaProduto.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        // Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void cancelar(ActionEvent event) throws IOException {
        Principal.root = FXMLLoader.load(getClass().getResource("/view/ListaProduto.fxml"));
        Scene cena = new Scene(root);
        Principal.palco.setScene(cena);
        Principal.palco.show();
        // Colocar palco no centro da tela
        Principal.palco.centerOnScreen();
    }

    @FXML
    public void valorCorreto(KeyEvent event) {
        int cont = 0;
        if ((event.getCode().ordinal() > 33 || event.getCode().ordinal() < 23)
                && event.getCode().ordinal() != 1) {
            if (event.getCode().ordinal() == 22) {
                for (int i = 0; i < valor.getText().length(); i++) {
                    if (valor.getText().charAt(i) == '.') {
                        cont++;
                        if (cont > 1) {
                            valor.deletePreviousChar();
                        }
                    }
                }
            } else {
                valor.deletePreviousChar();
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p = ListaProdutoController.produtoSelecionado;
        nome.setText(p.getNomeProduto());
        marca.setText(p.getMarca());
        peso.setText(p.getPeso());
        valor.setText(p.getValorFormatado() + "");
        imagem.setImage(p.getImagem().getImage());
        descricao.setText(p.getDescricao());

        SpinnerValueFactory<Integer> valorQuantidade = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100,
                p.getQuant());
        quant.setValueFactory(valorQuantidade);
    }

}
