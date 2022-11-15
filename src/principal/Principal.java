package principal;

import controller.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Supermercado;
import model.Usuario;

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
        for (Usuario u : supermarketData.getUsers()) {
            if (u.getNome().equals("Administrador")) {
                i++;
                break;
            }
        }
        if (i == 0) {
            new Usuario("Administrador", "admin", "admin", "admin");
        }
    }
}