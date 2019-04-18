package cheatgame;

//package CheatGame;
//
//import java.util.Optional;
//import javafx.application.Application;
//import javafx.event.EventType;
//import javafx.scene.Scene;
//import javafx.scene.control.TextInputDialog;
//import javafx.scene.layout.AnchorPane;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;
//import javafx.scene.shape.Line;
//import javafx.stage.Stage;
//
//public class PlayerUI extends Application {
//
//   public static void main(String zeeArguments) {
//      launch(zeeArguments);
//   }
//
//   @Override
//   public void start(Stage primaryStage) throws Exception {
//
//      AnchorPane rootPane = setupGUI();
//
//      primaryStage.setScene(new Scene(rootPane));
//
//      primaryStage.setX(3300); //DEV for development
//
//      primaryStage.setMinWidth(50);
//      primaryStage.setMinHeight(50);
//      primaryStage.setWidth(800);
//      primaryStage.setHeight(800);
//      rootPane.prefWidthProperty().bind(primaryStage.widthProperty());
//      rootPane.prefHeightProperty().bind(primaryStage.heightProperty());
//      primaryStage.show();
//
//      TextInputDialog getNumberOfPlayers = new TextInputDialog("4");
//      
//      getNumberOfPlayers.setTitle("How many players are there?");
//      getNumberOfPlayers.setContentText("Enter number of players (2-10)");
//      
//      int numberOfPlayers = -1;
//      
//      while(numberOfPlayers == -1){
//         Optional<String> result = getNumberOfPlayers.showAndWait();
//         if(result.isPresent()){
//            try{
//               numberOfPlayers = Integer.parseInt(result.get());
//            }catch(NumberFormatException e){
//               getNumberOfPlayers.setContentText("That was an incorrect value\nEnter number of players (2-10)");
//               numberOfPlayers = -1;
//            }
//         }
//      }
//      
//      
//
//      Logic l = new Logic(this, rootPane, numberOfPlayers);
//      l.start();
//   }
//
//   public AnchorPane setupGUI() {
//      AnchorPane root = new AnchorPane();
//
//      root.setStyle("-fx-background-color: #6f9779;");
//      ;
//
//      return root;
//   }
//
//}
