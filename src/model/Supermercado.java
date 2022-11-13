package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Supermercado implements Serializable {

    // ATRIBUTOS
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Usuario> users = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Entregador> entregadores = new ArrayList<>();
    private static List<Pedidos> todosPedidos = new ArrayList<>();
    private static List<Mensagem> mensagens = new ArrayList<>();

    // METODOS
    public static void gravarDados() {
        try {
            // Salvando todos usuarios em um arquivo
            FileOutputStream usersBase = new FileOutputStream("src/arquivos/usersbase.dat");
            ObjectOutputStream objectUsers = new ObjectOutputStream(usersBase);
            objectUsers.writeObject(users);
            objectUsers.flush();
            objectUsers.close();
            usersBase.flush();
            usersBase.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Salvando todos produtos em um arquivo
            FileOutputStream produtosBase = new FileOutputStream("src/arquivos/produtosbase.dat");
            ObjectOutputStream objectProdutos = new ObjectOutputStream(produtosBase);
            objectProdutos.writeObject(produtos);
            objectProdutos.flush();
            objectProdutos.close();
            produtosBase.flush();
            produtosBase.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            // Salvando todos pedidos em um arquivo
            FileOutputStream pedidosBase = new FileOutputStream("src/arquivos/pedidosbase.dat");
            ObjectOutputStream objectPedidos = new ObjectOutputStream(pedidosBase);
            objectPedidos.writeObject(todosPedidos);
            objectPedidos.flush();
            objectPedidos.close();
            pedidosBase.flush();
            pedidosBase.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            // Salvando todos usuarios em um arquivo
            FileOutputStream mensagemBase = new FileOutputStream("src/arquivos/mensagembase.dat");
            ObjectOutputStream objectMensagem = new ObjectOutputStream(mensagemBase);
            objectMensagem.writeObject(mensagens);
            objectMensagem.flush();
            objectMensagem.close();
            mensagemBase.flush();
            mensagemBase.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void carregarDados() {
        try {
            FileInputStream usersBase = new FileInputStream("src/arquivos/usersbase.dat");
            ObjectInputStream objectUsers = new ObjectInputStream(usersBase);
            users = (List<Usuario>) (objectUsers.readObject());
            objectUsers.close();
            usersBase.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileInputStream produtosBase = new FileInputStream("src/arquivos/produtosbase.dat");
            ObjectInputStream objectProdutos = new ObjectInputStream(produtosBase);
            produtos = (List<Produto>) (objectProdutos.readObject());
            objectProdutos.close();
            produtosBase.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileInputStream pedidosBase = new FileInputStream("src/arquivos/pedidosbase.dat");
            ObjectInputStream objectPedidos = new ObjectInputStream(pedidosBase);
            todosPedidos = (List<Pedidos>) (objectPedidos.readObject());
            objectPedidos.close();
            pedidosBase.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            FileInputStream mensagemBase = new FileInputStream("src/arquivos/mensagembase.dat");
            ObjectInputStream objectMensagem = new ObjectInputStream(mensagemBase);
            mensagens = (List<Mensagem>) (objectMensagem.readObject());
            objectMensagem.close();
            mensagemBase.close();
        } catch (IOException ex) {
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static List<Produto> getProdutos() {
        return produtos;
    }

    public static List<Usuario> getUsers() {
        return users;
    }

    public static List<Cliente> getClientes() {
        return clientes;
    }

    public static List<Entregador> getEntregadores() {
        return entregadores;
    }

    public static void setProdutos(List<Produto> produtos) {
        Supermercado.produtos = produtos;
    }

    public static List<Pedidos> getPedidos() {
        return todosPedidos;
    }

    public void setPedidos(List<Pedidos> pedidos) {
        Supermercado.todosPedidos = pedidos;
    }

    public static List<Mensagem> getMensagens() {
        return mensagens;
    }

    public void setMensagens(List<Mensagem> mensagens) {
        Supermercado.mensagens = mensagens;
    }

}
