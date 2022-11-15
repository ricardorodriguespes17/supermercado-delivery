package model;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class Cliente extends User {
  private String cpf;
  private String email;
  private String urlImagem;
  private Endereco endereco;
  private Cart cart;

  public Cliente(String name, String username, String password, String cpf, String email, File image) {
    super(name, username, password, "cliente");

    File salvarImage = new File("src/imagens/usuarios/" + username + ".png");
    try {
      copia(image, salvarImage);
    } catch (IOException ex) {
      Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
    }

    this.cpf = cpf;
    this.email = email;
    this.endereco = new Endereco();
    this.urlImagem = salvarImage.toString();
    this.cart = new Cart(username);
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

  public void setLogradouro(String logradouro) {
    endereco.setLogradouro(logradouro);
  }

  public void setNumeroCasa(String numeroCasa) {
    endereco.setNumeroCasa(numeroCasa);
  }

  public void setBairro(String bairro) {
    endereco.setBairro(bairro);
  }

  public void setPontoReferencia(String pontoReferencia) {
    endereco.setPontoReferencia(pontoReferencia);
  }

  public void setComplemento(String complemento) {
    endereco.setComplemento(complemento);
  }

  public String getBairro() {
    return endereco.getBairro();
  }

  public String getNumeroCasa() {
    return endereco.getNumeroCasa();
  }

  public String getLogradouro() {
    return endereco.getLogradouro();
  }

  public String getPontoReferencia() {
    return endereco.getPontoReferencia();
  }

  public String getComplemento() {
    return endereco.getComplemento();
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

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }

}
