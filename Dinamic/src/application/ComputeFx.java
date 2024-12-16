package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ComputeFx {
    // Declare UI components
    Button rest, Dp, back; // Buttons for resetting, showing the DP table, and going back
    Label lcom1, lcom2,select1,select2,Exp1,Exp2; // Labels to display player names or statuses
    HBox hbox1; // Horizontal box layout for arranging buttons in a row
    VBox vbox; // Vertical box layout for arranging labels in a column

    // Constructor to initialize and configure the UI components
    public ComputeFx() {
        // Initialize the "Rest" button with custom styling
        rest = new Button(" Rest ");
        rest.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold;");

        // Initialize the "DP Table" button with custom styling
        Dp = new Button(" DP Taple ");
        Dp.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 15px; -fx-font-weight: bold;");

        // Initialize the "Back" button with an image and transparent background
        back = new Button(null, new ImageView(new Image("file:///C:/Users/Asus/IdeaProjects/ProjectD/src/back.png")));
        back.setStyle("-fx-background-color: transparent;"); // Transparent background for the button
        back.setAlignment(Pos.CENTER_LEFT); // Align the button to the left
        back.setPadding(new Insets(25)); // Add padding for better spacing

        // Initialize player labels
        lcom2 = new Label("Player(~2~)"); // Label for Player 1
        lcom1 = new Label("Player(~1~)"); // Label for Player 2
        select1=new Label("Coins Select:");
        select2=new Label("Coins Select:");
        Exp1=new Label("Expected Value :");
        Exp2=new Label("Expected Value :");

        // Set custom styles for player labels
        lcom1.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
        lcom2.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
        select1.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
        select2.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
        Exp1.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
        Exp2.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");

        // Create a VBox to arrange labels vertically
        vbox = new VBox(10); // Vertical gap of 10 units between elements
        vbox.getChildren().addAll(lcom1, lcom2); // Add labels to the VBox
        vbox.setAlignment(Pos.CENTER); // Center align the VBox
        vbox.setPadding(new Insets(40)); // Add padding around VBox

        // Create an HBox to arrange buttons horizontally
        hbox1 = new HBox(10); // Horizontal gap of 10 units between buttons
        hbox1.getChildren().addAll(rest, Dp); // Add buttons to the HBox
        hbox1.setAlignment(Pos.CENTER); // Center align the HBox
        hbox1.setPadding(new Insets(25)); // Add padding around HBox
    }
}
