package principal;

import controller.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Supermercado;

public class Principal extends Application {

    public static Stage palco = new Stage();
    public static Supermercado supermarketData = new Supermercado();

    @Override
    public void start(Stage palco) throws Exception {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        StageController.openScreen();
    }

    public static void main(String[] args) {
        launch(args);
        supermarketData.saveDatas();
    }

}