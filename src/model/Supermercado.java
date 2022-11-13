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

    private static List<Produto> produts = new ArrayList<>();
    private static List<Usuario> users = new ArrayList<>();
    private static List<Cliente> clients = new ArrayList<>();
    private static List<Entregador> deliveryPeoples = new ArrayList<>();
    private static List<Pedidos> orders = new ArrayList<>();
    private static List<Mensagem> messages = new ArrayList<>();

    private static void saveData(String fileName, Object object) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("src/arquivos/" + fileName + ".dat");
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(object);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static Object loadData(String fileName) {
        ArrayList<Object> data = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream("src/arquivos/" + fileName + ".dat");
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            data = (ArrayList<Object>) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Supermercado.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

    public static void gravarDados() {
        saveData("usersBase", users);
        saveData("clientsBase", clients);
        saveData("deliveryPeopleBase", deliveryPeoples);
        saveData("produtsBase", produts);
        saveData("ordersBase", orders);
        saveData("messagesBase", messages);
    }

    public static void carregarDados() {
        users = (List<Usuario>) loadData("usersBase");
        clients = (List<Cliente>) loadData("clientsBase");
        deliveryPeoples = (List<Entregador>) loadData("deliveryPeopleBase");
        produts = (List<Produto>) loadData("produtsBase");
        orders = (List<Pedidos>) loadData("ordersBase");
        messages = (List<Mensagem>) loadData("messagesBase");
    }

    public static List<Produto> getProdutos() {
        return produts;
    }

    public static void setProdutos(List<Produto> produts) {
        Supermercado.produts = produts;
    }

    public static List<Usuario> getUsers() {
        return users;
    }

    public static List<Cliente> getClientes() {
        return clients;
    }

    public static List<Entregador> getEntregadores() {
        return deliveryPeoples;
    }

    public static List<Pedidos> getPedidos() {
        return orders;
    }

    public void setPedidos(List<Pedidos> orders) {
        Supermercado.orders = orders;
    }

    public static List<Mensagem> getMensagens() {
        return messages;
    }

    public void setMensagens(List<Mensagem> messages) {
        Supermercado.messages = messages;
    }

}
