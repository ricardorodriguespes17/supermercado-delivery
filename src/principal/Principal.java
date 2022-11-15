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
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        StageController.openScreen();
    }

    public static void main(String[] args) {
        supermarketData.loadDatas();
        criarAdmin();
        launch(args);
        supermarketData.saveDatas();
    }

    public static void criarAdmin() {
        int i = 0;
        for (User u : supermarketData.getUsers()) {
            if (u.getType().equals("admin")) {
                i++;
                break;
            }
        }
        if (i == 0) {
            new User("Administrador", "admin", "admin", "admin");
        }
    }
}