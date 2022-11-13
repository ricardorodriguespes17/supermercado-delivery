package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Entregador extends Usuario {
  private String cpf;
  private String email;
  private String urlImagem;

  public Entregador(String name, String username, String password, String cpf, String email, File image) {
    super(name, username, password, "entregador");

    File salvarImage = new File("src/imagens/usuarios/" + username + ".png");
    try {
      copia(image, salvarImage);
    } catch (IOException ex) {
      Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
    }

    this.cpf = cpf;
    this.email = email;
    this.urlImagem = salvarImage.toString();
  }

  public String getCpf() {
    return cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public ImageView getImagem() {
    File arquivo = new File(urlImagem);
    BufferedImage bufferedImage;
    Image image = null;
    try {
      if (!arquivo.exists()) {
        // Se nao existir imagem no local indicado, usuara uma imagem generica
        // System.out.println("Imagem não encontrada");
        urlImagem = "src/imagens/usuario.png";
        arquivo = new File(urlImagem);
      }
      bufferedImage = ImageIO.read(arquivo);
      image = SwingFXUtils.toFXImage(bufferedImage, null);
    } catch (IOException ex) {
      Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
    }

    ImageView i = new ImageView(image);
    i.setFitWidth(79);
    i.setFitHeight(80);

    Rectangle clip = new Rectangle(79, 80);
    clip.setArcWidth(79);
    clip.setArcHeight(79);
    i.setClip(clip);

    return i;
  }

  public void setImagem(File imagem) {
    this.urlImagem = imagem.toString();
  }

  public String getUrlImagem() {
    return urlImagem;
  }

  public void setUrlImagem(String urlImagem) {
    this.urlImagem = urlImagem;
  }
}
