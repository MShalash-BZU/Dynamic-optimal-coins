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

public class OnevsoneFx {
    // Labels to display player names and their respective sums
    Label user1, user2, sum1, sum2,win1,win2;

    // TextFields to display or input sums of the players
    TextField tsum1, tsum2;

    // HBox layouts to organize labels and text fields in horizontal rows
    HBox hBox1, hBox2, hbox,hbotum;

    // VBox layouts to organize player-related components in vertical columns
    VBox vBox1, vBox2;

    // Font to style text in the labels
    Font font;

    // Button to navigate back to the previous screen
    Button back,reset;

    // Constructor that initializes the layout and components
    public OnevsoneFx(String us1, String us2) {
        // Initialize labels for player names
        user1 = new Label(us1); // Label for Player 1
        user2 = new Label(us2); // Label for Player 2
        win1 = new Label();
        win2 = new Label();

        // Initialize labels for sum descriptions
        sum1 = new Label("Sum of Player (1):");
        sum2 = new Label("Sum of Player (2):");

        // Initialize text fields for sum values
        tsum1 = new TextField(); 
        tsum2 = new TextField(); 
        tsum1.setMaxWidth(50); // Set a maximum width for Player 1's sum input field
        tsum2.setMaxWidth(50); // Set a maximum width for Player 2's sum input field

        // Initialize the "Back" button with an image
        back = new Button(null, new ImageView(new Image("file:///C:/Users/Asus/IdeaProjects/ProjectD/src/back.png")));
        back.setStyle("-fx-background-color: transparent;"); // Transparent background
        back.setAlignment(Pos.CENTER_LEFT); // Align the button to the left
        back.setPadding(new Insets(25)); // Add padding for spacing
        
        // Initialize "reset" button with a custom style
        reset=new Button("  Reset  ");
        reset.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 13px; -fx-font-weight: bold;");
        reset.setAlignment(Pos.CENTER);

        // Set font style for labels
        font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 15);
        user2.setFont(font);
        user1.setFont(font);
        sum1.setFont(font);
        sum2.setFont(font);
        win1.setFont(font);
        win2.setFont(font);

        // Set minimum dimensions for player name labels
        user1.setMinWidth(100);
        user2.setMinWidth(100);
        user1.setMinHeight(40);
        user2.setMinHeight(40);

        // Set text colors for the labels
        sum2.setTextFill(Color.LIGHTBLUE);
        sum1.setTextFill(Color.LIGHTBLUE);
        user1.setTextFill(Color.LIGHTBLUE);
        user2.setTextFill(Color.LIGHTBLUE);

        // Create HBox for Player 1's sum label and text field
        hBox1 = new HBox(5); // Horizontal spacing of 5 units
        hBox1.getChildren().addAll(sum1, tsum1); // Add label and text field to the HBox

        // Create HBox for Player 2's sum label and text field
        hBox2 = new HBox(5); // Horizontal spacing of 5 units
        hBox2.getChildren().addAll(sum2, tsum2); // Add label and text field to the HBox

        // Create VBox for Player 1, containing their name label and sum HBox
        vBox1 = new VBox(5); // Vertical spacing of 5 units
        vBox1.getChildren().addAll(user1, hBox1,win1); // Add components to the VBox
        vBox1.setAlignment(Pos.CENTER); // Center align the VBox

        // Create VBox for Player 2, containing their name label and sum HBox
        vBox2 = new VBox(5); // Vertical spacing of 5 units
        vBox2.getChildren().addAll(user2, hBox2,win2); // Add components to the VBox
        vBox2.setAlignment(Pos.CENTER); // Center align the VBox

        // Create a main HBox to arrange both player VBoxes horizontally
        hbox = new HBox(40); // Horizontal spacing of 40 units
        hbox.getChildren().addAll(vBox1, vBox2); // Add player VBoxes to the main HBox
        hbox.setAlignment(Pos.TOP_CENTER); // Align at the top center
        hbox.setPadding(new Insets(30)); // Add padding for spacing
        
        hbotum=new HBox(10);
        
    }
}
