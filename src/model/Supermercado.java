package model;

import java.io.Serializable;
import java.util.List;
import data.ClientsData;
import data.DeliveryPeoplesData;
import data.MessagesData;
import data.OrdersData;
import data.ProductsData;
import data.UsersData;

public class Supermercado implements Serializable {

    private UsersData usersData = new UsersData("users");
    private ClientsData clientsData = new ClientsData("clients");
    private DeliveryPeoplesData deliveryPeopleData = new DeliveryPeoplesData("delivery-peoples");
    private ProductsData productsData = new ProductsData("products");
    private OrdersData ordersData = new OrdersData("orders");
    private MessagesData messagesData = new MessagesData("messages");

    public void saveDatas() {
        usersData.saveData();
        clientsData.saveData();
        deliveryPeopleData.saveData();
        productsData.saveData();
        ordersData.saveData();
        messagesData.saveData();
    }

    public void loadDatas() {
        usersData.loadData();
        clientsData.loadData();
        deliveryPeopleData.loadData();
        productsData.loadData();
        ordersData.loadData();
        messagesData.loadData();
    }

    public List<Usuario> getUsers() {
        return usersData.getData();
    }

    public List<Produto> getProducts() {
        return productsData.getData();
    }

    public List<Cliente> getClients() {
        return clientsData.getData();
    }

    public List<Entregador> getDeliveryPeoples() {
        return deliveryPeopleData.getData();
    }

    public List<Pedidos> getOrders() {
        return ordersData.getData();
    }

    public List<Mensagem> getMessages() {
        return messagesData.getData();
    }

    public void addUser(Usuario newUser) {
        usersData.addData(newUser);
    }

    public void addClient(Cliente newUser) {
        clientsData.addData(newUser);
    }

    public void addDeliveryPeople(Entregador newUser) {
        deliveryPeopleData.addData(newUser);
    }

    public void addProduct(Produto newUser) {
        productsData.addData(newUser);
    }

    public void addOrder(Pedidos newUser) {
        ordersData.addData(newUser);
    }

    public void addMessage(Mensagem newUser) {
        messagesData.addData(newUser);
    }

}
