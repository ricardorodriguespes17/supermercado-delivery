package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Supermercado implements Serializable {

    private Data<User> loggedUserData;
    private Data<List<User>> usersData;
    private Data<List<Cliente>> clientsData;
    private Data<List<Entregador>> deliveryPeopleData;
    private Data<List<Produto>> productsData;
    private Data<List<Order>> ordersData;
    private Data<List<Mensagem>> messagesData;

    public Supermercado() {
        loggedUserData = new Data<>("logged-user", null);
        usersData = new Data<>("users", new ArrayList<>());
        clientsData = new Data<>("clients", new ArrayList<>());
        deliveryPeopleData = new Data<>("delivery-peoples", new ArrayList<>());
        productsData = new Data<>("products", new ArrayList<>());
        ordersData = new Data<>("orders", new ArrayList<>());
        messagesData = new Data<>("messages", new ArrayList<>());

        loadDatas();
    }

    public User getLoggedUser() {
        return loggedUserData.getData();
    }

    public void logUser(User loggedUser, boolean saveData) {
        loggedUserData.setData(loggedUser);
        if (saveData) {
            loggedUserData.saveData();
        }
    }

    public void saveDatas() {
        usersData.saveData();
        clientsData.saveData();
        deliveryPeopleData.saveData();
        productsData.saveData();
        ordersData.saveData();
        messagesData.saveData();
    }

    public void loadDatas() {
        loggedUserData.loadData();
        usersData.loadData();
        clientsData.loadData();
        deliveryPeopleData.loadData();
        productsData.loadData();
        ordersData.loadData();
        messagesData.loadData();

        createAdmin();
    }

    public void createAdmin() {
        if (getUsers().size() == 0) {
            new User("Administrador", "admin", "admin", User.TYPE_ADMIN);
        }
    }

    public List<User> getUsers() {
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

    public List<Order> getOrders() {
        return ordersData.getData();
    }

    public List<Mensagem> getMessages() {
        return messagesData.getData();
    }

    public void addUser(User data) {
        usersData.getData().add(data);
    }

    public void addClient(Cliente data) {
        clientsData.getData().add(data);
    }

    public void addDeliveryPeople(Entregador data) {
        deliveryPeopleData.getData().add(data);
    }

    public void addProduct(Produto data) {
        productsData.getData().add(data);
    }

    public void addOrder(Order data) {
        ordersData.getData().add(data);
    }

    public void addMessage(Mensagem data) {
        messagesData.getData().add(data);
    }

}
