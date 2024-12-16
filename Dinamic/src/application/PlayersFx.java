package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PlayersFx {
    // Labels for displaying prompts to enter player names
    Label name1, name2;

    // TextFields for inputting player names
    TextField tname1, tname2;

    // HBoxes for organizing each player's label and input field horizontally
    HBox hbox1, hbox2;

    // Buttons for playing the game and navigating back
    Button play, back;

    // VBox for organizing all components vertically
    VBox vbox;

    // Font for styling labels
    Font font;

    // Constructor to initialize the layout and components
    public PlayersFx() {
        // Set the font style for labels
        font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10);

        // Initialize the "Back" button with an image
        back = new Button(null, new ImageView(new Image("file:///C:/Users/Asus/IdeaProjects/ProjectD/src/back.png")));
        back.setStyle("-fx-background-color: transparent;"); // Transparent background
        back.setAlignment(Pos.CENTER_LEFT); // Align the button to the left
        back.setPadding(new Insets(25)); // Add padding for spacing

        // Initialize labels with text prompts
        name1 = new Label("Enter Name player(1):"); // Label for Player 1
        name2 = new Label("Enter Name player(2):"); // Label for Player 2
        name1.setFont(font); // Apply font to Player 1 label
        name2.setFont(font); // Apply font to Player 2 label
        name1.setTextFill(Color.LIGHTBLUE); // Set text color for Player 1 label
        name2.setTextFill(Color.LIGHTBLUE); // Set text color for Player 2 label

        // Initialize text fields for player name input
        tname1 = new TextField(); // TextField for Player 1's name
        tname2 = new TextField(); // TextField for Player 2's name

        // Create HBox for Player 1's label and input field
        hbox1 = new HBox(5); // Horizontal spacing of 5 units
        hbox1.getChildren().addAll(name1, tname1); // Add label and text field to HBox
        hbox1.setAlignment(Pos.CENTER); // Center align the HBox
        hbox1.setPadding(new Insets(10)); // Add padding for spacing

        // Create HBox for Player 2's label and input field
        hbox2 = new HBox(5); // Horizontal spacing of 5 units
        hbox2.getChildren().addAll(name2, tname2); // Add label and text field to HBox
        hbox2.setAlignment(Pos.CENTER); // Center align the HBox
        hbox2.setPadding(new Insets(10)); // Add padding for spacing

        // Initialize the "Play" button
        play = new Button("PLAY"); // Button to start the game
        play.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold;");

        // Create a VBox to organize all components vertically
        vbox = new VBox(3); // Vertical spacing of 3 units
        vbox.getChildren().addAll(hbox1, hbox2, play); // Add HBoxes and button to VBox
        vbox.setPadding(new Insets(25)); // Add padding for spacing
        vbox.setAlignment(Pos.CENTER); // Center align the VBox
    }
}
