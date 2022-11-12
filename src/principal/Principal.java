package principal;

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Supermercado;
import model.Usuario;

public class Principal extends Application {

    public static Stage palco = new Stage();
    public static Parent root;

    @Override
    public void start(Stage palco) throws Exception {
        root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        Scene scene = new Scene(root);
        Principal.palco.setScene(scene);
        Principal.palco.initStyle(StageStyle.UNDECORATED);
        Principal.palco.show();
        Principal.palco.centerOnScreen();
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