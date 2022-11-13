package principal;

import java.io.File;

import controller.StageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import model.Supermercado;
import model.Usuario;

public class Principal extends Application {

    public static Stage palco = new Stage();

    @Override
    public void start(Stage palco) throws Exception {
        StageController.root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        StageController.openScreen();
    }

    public static void main(String[] args) {
        Supermercado.carregarDados();
        criarAdmin();
        launch(args);
        Supermercado.gravarDados();
    }

    public static void criarAdmin() {
        int i = 0;
        for (Usuario u : Supermercado.getUsers()) {
            if (u.getNome().equals("Administrador")) {
                i++;
                break;
            }
        }
        if (i == 0) {
            new Usuario("Administrador", "admin", "admin", "admin", "admin", "admin",
                    new File("src/imagens/usuario.png"));
        }
    }
}