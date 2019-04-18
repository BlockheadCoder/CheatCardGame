package CheatGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class PlayerUI extends Application {

   private Stage primaryStage;

   public static void main(String zeeArguments) {
      launch(zeeArguments);
   }

   @Override
   public void start(Stage primaryStage) throws Exception {
      this.primaryStage = primaryStage;

      primaryStage.setScene(new Scene(setupGUI()));

      primaryStage.setX(3300); //DEV for development

      primaryStage.show();
      Logic l = new Logic(this);
      l.start();
   }

   public Pane setupGUI() {
      BorderPane root = new BorderPane();

      root.setStyle("-fx-background-color: #6f9779;");
      
      
      
      return root;
   }

}
