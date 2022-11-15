package principal;

import controller.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Supermercado;
import model.User;

public class Principal extends Application {

    public static Stage palco = new Stage();
    public static Supermercado supermarketData = new Supermercado();

    @Override
    public void start(Stage palco) throws Exception {
        User loggedUser = Principal.supermarketData.getLoggedUser();

        if (loggedUser != null) {
            if (loggedUser.getType().equals(User.TYPE_ADMIN))
                StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaAdmin.fxml"));

            if (loggedUser.getType().equals(User.TYPE_CLIENT))
                StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaUsuario.fxml"));

            if (loggedUser.getType().equals(User.TYPE_DELIVERY_PEOPLE))
                StageController.root = FXMLLoader.load(getClass().getResource("/view/TelaEntregador.fxml"));
        } else {
            StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        }

        StageController.openScreen();

    }

    public static void main(String[] args) {
        launch(args);
        supermarketData.saveDatas();
    }

}