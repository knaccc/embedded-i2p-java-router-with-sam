package org.getmonero.i2p.zero.gui;

import java.awt.Taskbar;
import java.awt.image.BufferedImage;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.getmonero.i2p.zero.TunnelControl;

import javax.imageio.ImageIO;

public class Gui extends Application {


  public static Gui instance;
  private Controller controller;
  private boolean isStopping = false;

  @Override
  public void start(Stage primaryStage) throws Exception{

    if(TunnelControl.isPortInUse()) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("Error");
      alert.setHeaderText(null);
      alert.setContentText("I2P-zero is already running");
      alert.showAndWait();
      System.exit(1);
    }

    instance = this;
    String osName = System.getProperty("os.name");
    if(osName.startsWith("Mac")) {
      Taskbar taskbar = Taskbar.getTaskbar();
      BufferedImage image = ImageIO.read(getClass().getResource("icon.png"));
      taskbar.setIconImage(image);
    }
    else primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("icon.png")));

    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
    Parent root = loader.load();
    controller = loader.getController();

    primaryStage.setTitle("I2P-zero");
    primaryStage.setWidth(360);
    primaryStage.setHeight(340);
    primaryStage.setMinWidth(360);
    primaryStage.setMinHeight(370);
    Scene scene = new Scene(root);
    scene.getStylesheets().add("org/getmonero/i2p/zero/gui/gui.css");
    primaryStage.setScene(scene);
    primaryStage.show();

  }

  @Override
  public void stop() throws Exception {
    isStopping = true;
    if(controller.getRouterWrapper().isStarted()) controller.getRouterWrapper().stop(true);
  }

  public boolean isStopping() {
    return isStopping;
  }

  public Controller getController() {
    return controller;
  }

  public static void main(String[] args) {
    launch(args);
  }
}
