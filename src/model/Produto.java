package model;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

public class Produto implements Serializable, Comparable<Produto>{
    //ATRIBUTOS
    private int quant = 0;
    private double valor;
    private int cod = 1;
    private String nomeProduto;
    private String nome;
    private String marca;
    private String peso;
    private String urlImagem;
    private String descricao;
    private int numeroVendido;
    
    //CONSTRUTOR  
    public Produto(String nomeProduto, String marca, String peso, double valor, int quant, String descricao, File imagemOriginal){              
        //Gerando codigo aleatorio para o produto
        cod = (int) (Math.random() * 10000);
        
        for(int i = 0; i < Supermercado.getProdutos().size(); i++){
            if(cod == Supermercado.getProdutos().get(i).getCod()){
                cod = (int) (Math.random() * 10000);
                i = 0;
            }
        }    
        
        //Verificando se tem algum produto com nome igual ao novo produto
        int cont = 0;
        for(Produto p : Supermercado.getProdutos()){
            if(p.nome.equals(nomeProduto)){
                cont++;
            }
        }
        
        //Copiando a imagem do produto para o pacote
        File salvarImagem = new File("src/imagens/produtos/" + cod + ".png");
        try {
            copia(imagemOriginal, salvarImagem);
        } catch (IOException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //adicionando as demais propriedades do novo produto
        if(cont == 0){
            if(peso != null){
                this.nome = nomeProduto + "\n" + descricao + "\n" + marca + "\n" + peso;
            }else{
                this.nome = nomeProduto + "\n" + marca;
            }
            this.marca = marca;
            this.peso = peso;
            this.nomeProduto = nomeProduto;
            this.valor = valor;
            this.quant = quant;
            this.descricao = descricao;
            this.urlImagem = salvarImagem.toString();
            Supermercado.getProdutos().add(this);
            try {
                gravarDados();
            } catch (IOException ex) {
                Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            System.out.println("Produto já existe");
        }
    } //fim do metodo construtor
    
    @Override
    public int compareTo(Produto outroProduto) {
         if(outroProduto.getNumeroVendido() > numeroVendido){
             return 1;
         }
         if(outroProduto.getNumeroVendido() < numeroVendido){
             return -1;
         }
         return 0;
    }
    
    public final void gravarDados() throws FileNotFoundException, IOException{
        //Salvando todos produtos em um arquivo
            FileOutputStream produtosBase = new FileOutputStream("src/arquivos/produtosbase.dat");
            ObjectOutputStream objectProdutos = new ObjectOutputStream(produtosBase);
            objectProdutos.writeObject(Supermercado.getProdutos());
            objectProdutos.flush();
            objectProdutos.close();
            produtosBase.flush();
            produtosBase.close();
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
    
    
    //METODOS   
    public String getValor() {
        DecimalFormat df = new DecimalFormat("R$ 0.00");
        return df.format(valor);
    }
    
    public double getValorFormatado(){
        return valor;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }
    
    public int getQuant() {
        return quant;
    }
    
    public String getMarca() {
        return marca;
    }

    public String getPeso() {
        return peso;
    }

    public ImageView getImagem() {
        File arquivo = new File(urlImagem);
        BufferedImage bufferedImage;
        Image image = null;
        try {
            if(!arquivo.exists()){
                System.out.println("Imagem não encontrada");
                return null;
            }
            bufferedImage = ImageIO.read(arquivo);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (IOException ex) {
            Logger.getLogger(Produto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ImageView i = new ImageView(image);
        i.setFitWidth(145);
        i.setFitHeight(145);
        return i;
    }
    
    public String getUrlImagem() {
        return urlImagem;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public int getNumeroVendido() {
        return numeroVendido;
    }

    public void setNumeroVendido(int numeroVendido) {
        this.numeroVendido = numeroVendido;
        Collections.sort(Supermercado.getProdutos());
    }
    
    public void setNumeroVendido(){
        numeroVendido++;
    }
    
    public void setNome(String nome, String marca, String descricao, String peso) {
        this.nome = nome + "\n" + descricao + "\n" + marca + "\n" + peso;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
    
    public void setPeso(String peso) {
            this.peso = peso;
    }
    
    public void setImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}
