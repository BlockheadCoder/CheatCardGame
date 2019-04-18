package cheatgame;

import java.util.ArrayList;
import java.util.Optional;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class CheatGame extends Application {

    private Label whosTurnIsIt;
    private Label lastPlay;
    private Pane rootPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        rootPane = new AnchorPane();

        rootPane.setStyle("-fx-background-color: #6f9779;");

        primaryStage.setScene(new Scene(rootPane));

        primaryStage.setMinWidth(50);
        primaryStage.setMinHeight(50);
        primaryStage.setWidth(800);
        primaryStage.setHeight(800);
        rootPane.prefWidthProperty().bind(primaryStage.widthProperty());
        rootPane.prefHeightProperty().bind(primaryStage.heightProperty());

        primaryStage.show();

        whosTurnIsIt = new Label();
        lastPlay = new Label("");
        
        
        
        whosTurnIsIt.setText("Setting up game, please follow instructions on popups");
        whosTurnIsIt.setFont(Font.font(25));

        rootPane.getChildren().add(whosTurnIsIt);

        whosTurnIsIt.setLayoutX(0);
        whosTurnIsIt.setLayoutY(
                (rootPane.heightProperty().get() / 2) + 150);

        TextInputDialog getNumberOfPlayers = new TextInputDialog("4");

        getNumberOfPlayers.setTitle("How many players are there?");
        getNumberOfPlayers.setContentText("Enter number of players (2-10)");

        int numberOfPlayers = -1;

        while (numberOfPlayers == -1) {
            Optional<String> result = getNumberOfPlayers.showAndWait();
            if (result.isPresent()) {
                try {
                    numberOfPlayers = Integer.parseInt(result.get());
                    if (numberOfPlayers < 2 || numberOfPlayers > 10) {
                        throw new NumberFormatException();
                    }
                } catch (NumberFormatException e) {
                    getNumberOfPlayers.setContentText("That was an incorrect value\nEnter number of players (2-10)");
                    numberOfPlayers = -1;
                }
            } else {
                System.out.println("Exiting");
                System.exit(1);
            }
        }

        ArrayList<CheatPlayer> pl = new ArrayList();

        for (int i = 0; i < numberOfPlayers; i++) {
            TextInputDialog getPlayerName = new TextInputDialog();
            getPlayerName.setHeaderText("Enter your name player: " + (i + 1));
            getPlayerName.setContentText("Enter your name player: " + (i + 1));

            Optional<String> result = getPlayerName.showAndWait();
            String playerName = "Player" + (i + 1);
            if (result.isPresent()) {
                if (result.get().trim().length() > 0) {
                    playerName = result.get();
                }
            }

            pl.add(new CheatPlayer(playerName));
        }

        Button nextTurn = new Button("Play");
        Button cheat = new Button("CHEAT!");

        lastPlay.setLayoutX(150);
        lastPlay.setLayoutY(200);
        lastPlay.setFont(Font.font(20));

        nextTurn.setLayoutX(0);
        nextTurn.setLayoutY(550);
        nextTurn.setFont(Font.font(30));

        cheat.setLayoutX(350);
        cheat.setLayoutY(475);
        cheat.setFont(Font.font(20));

        rootPane.getChildren().addAll(nextTurn, cheat, lastPlay);
        Logic l = new Logic(this, rootPane, pl);
        l.start();

        nextTurn.setOnAction(ev -> {
            l.nextTurn();
        });
        
        cheat.setOnAction(ev -> {
            l.cheatCalled();
        });
    }

    public void changeWhosTurnItIs(String name, Value playing,
            String lastPlayerName, int numPlayed, Value lastPlayed) {

        if (numPlayed != 0) {
            lastPlay.setText(String.format("%s claims to have played %d %sS",lastPlayerName,numPlayed,lastPlayed));
        }
        whosTurnIsIt.setText("It is " + name + "'s turn. Playing :" + playing);
        whosTurnIsIt.setLayoutX((rootPane.widthProperty().doubleValue() / 2) - (whosTurnIsIt.getWidth() / 2));
    }

}
