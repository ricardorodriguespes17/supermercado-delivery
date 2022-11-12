package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javax.imageio.ImageIO;

public class Usuario implements Serializable, Comparable<Usuario>{
    //ATRIBUTOS
    private String nome;
    private String cpf;
    private String userName;
    private String email;
    private String senha;
    private String tipo;
    private String urlImagem;
    private Endereco endereco;
    private Carrinho carrinho = new Carrinho();
    private List<Notificacao> notificacoes;
    private Date ultimaMensagem;
    
    //Metodo Construtor
    public Usuario(String nome, String userName, String senha, String cpf, String email, String tipo, File imagem){
        //Copiando a imagem do produto para o pacote
        File salvarImagem = new File("src/imagens/usuarios/" + userName + ".png");
        try {
            copia(imagem, salvarImagem);
        } catch (IOException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //dados pessoais
        this.nome = nome;
        this.cpf = cpf;
        this.userName = userName;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
        this.urlImagem = salvarImagem.toString();
        //dados residenciais
        this.endereco = new Endereco("", "", "", "", "");
        this.notificacoes = new ArrayList<>();
        //dados da ultima mensagem enviada ou recebida para fins de ordenacao no topico de mensagens do admin
        ultimaMensagem = new Date();
        Supermercado.getUsers().add(this);
    }    

    //Faz a copias de um arquivo para outro diretorio
    public final void copia(File fonte, File destino) throws IOException {
        OutputStream out;
        try (InputStream in = new FileInputStream(fonte)) {
            out = new FileOutputStream(destino);
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
        }
	out.close();
    }
    
    //Encontra a localizacao da imagem do usuario e retorna ela em uma ImageView
    public ImageView getImagem() {
        File arquivo = new File(urlImagem);
        BufferedImage bufferedImage;
        Image image = null;
        try {
            if(!arquivo.exists()){
                //Se nao existir imagem no local indicado, usuara uma imagem generica
                //System.out.println("Imagem não encontrada");
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
    
    //retorna o nome de usuario
    public String getUserName() {
        return userName;
    }

    public String getSenha() {
        return senha;
    }

    public String getTipo() {
        return tipo;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }
    
    public String getBairro() {
        return endereco.getBairro();
    }
    
     public Carrinho getCarrinho() {
        return carrinho;
    }
    
    public String getUrlImagem() {
        return urlImagem;
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
    
    public String getComplemento(){
        return endereco.getComplemento();
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }  

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setImagem(File imagem){
        this.urlImagem = imagem.toString();
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public void setCarrinho(Carrinho pedidos) {
        this.carrinho = pedidos;
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
    
    public void setComplemento(String complemento){
        endereco.setComplemento(complemento);
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    public void setNotificacoes(List<Notificacao> notificacoes) {
        this.notificacoes = notificacoes;
    }

     public Date getUltimaMensagem() {
        return ultimaMensagem;
    }

    public void setUltimaMensagem(Date ultimaMensagem) {
        this.ultimaMensagem = ultimaMensagem;
    }
    
    @Override
    public int compareTo(Usuario o) {
        if(ultimaMensagem.before(o.ultimaMensagem)){
            return 1;
        }
        if(ultimaMensagem.after(o.ultimaMensagem)){
            return -1;
        }
        return 0;
    }
    
}
