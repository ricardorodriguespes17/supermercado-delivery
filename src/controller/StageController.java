package controller;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StageController {
  public static Stage stage = new Stage();
  public static Parent root;

  public static void openScreen() {
    Scene scene = new Scene(root);
    stage.setScene(scene);
    stage.show();
    stage.centerOnScreen();
  }
}
