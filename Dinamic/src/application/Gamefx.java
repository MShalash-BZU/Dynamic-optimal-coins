package application;


import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;


public class Gamefx {
        // Declare UI components
        Label l1, l2; // Labels for input fields
        Button coins, file, random, players, showDp, add, back,reset; // Buttons for various actions
        TextField TXcoins, tlength, tmax; // Text fields for user input
        TextArea tarea; // Text area for displaying output or messages
        HBox hbox; // Horizontal box layout for arranging elements in a row
        VBox vbox, vbox2; // Vertical box layouts for arranging elements in a column
        GridPane gpane, gpane2; // Grid panes for flexible layout management
        Font font; // Font for styling text

        // Constructor to initialize and configure the UI components
        public Gamefx() {
            // Initialize labels with descriptive text
            l1 = new Label("Length of array :");
            l2 = new Label("Max number Random :");

            // Set font for labels
            font = Font.font("Arial", FontWeight.BOLD, FontPosture.ITALIC, 10);
            l1.setFont(font);
            l2.setFont(font);

            // Set text color for labels
            l1.setTextFill(Color.LIGHTBLUE);
            l2.setTextFill(Color.LIGHTBLUE);

            // Initialize text fields for user inputs
            tlength = new TextField();
            tmax = new TextField();

            // Initialize "Add" button with a custom style
            add = new Button("  ADD  ");
            add.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 13px; -fx-font-weight: bold;");
            

            // Initialize "Back" button with an image and transparent background
            back = new Button(null, new ImageView(new Image("file:///C:/Users/Asus/IdeaProjects/ProjectD/src/back.png")));
            back.setStyle("-fx-background-color: transparent;");
            back.setAlignment(Pos.CENTER_LEFT);
            back.setPadding(new Insets(25));

            // Configure second grid pane (gpane2) for input fields
            gpane2 = new GridPane();
            gpane2.add(l1, 0, 0); // Add label l1 at row 0, column 0
            gpane2.add(l2, 0, 1); // Add label l2 at row 1, column 0
            gpane2.add(tlength, 1, 0); // Add tlength field at row 0, column 1
            gpane2.add(tmax, 1, 1); // Add tmax field at row 1, column 1
            gpane2.setVgap(5); // Vertical gap between rows
            gpane2.setHgap(5); // Horizontal gap between columns
            gpane2.setAlignment(Pos.CENTER); // Center alignment for the grid

            // Create a VBox for the grid and "Add" button
            vbox2 = new VBox(5);
            vbox2.getChildren().addAll(gpane2, add); // Add grid pane and button to VBox
            vbox2.setAlignment(Pos.TOP_CENTER); // Align to the top-center
            vbox2.setPadding(new Insets(25)); // Set padding around VBox

            // Initialize text area for output display
            tarea = new TextArea();
            tarea.setMaxHeight(79); // Limit the height of the text area
            tarea.setMaxWidth(340); // Limit the width of the text area

            // Initialize buttons for various actions with custom styles
            coins = new Button(" Add Coins ");
            file = new Button(" Open File ");
            random = new Button(" Add Random Coins ");
            players = new Button("ONE VS ONE");
            showDp = new Button("Computer Mode");
            String buttonStyle = "-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;";
            coins.setStyle(buttonStyle);
            file.setStyle(buttonStyle);
            random.setStyle(buttonStyle);
            players.setStyle(buttonStyle);
            showDp.setStyle(buttonStyle);

            // Set minimum size for buttons
            coins.setMinSize(100, 20);
            file.setMinSize(230, 20);
            random.setMinSize(230, 20);
            players.setMinSize(230, 20);
            showDp.setMinSize(230, 20);

            // Initialize text field for coin input with placeholder text
            TXcoins = new TextField();
            TXcoins.setPromptText("Example 1,2,3,4"); // Placeholder text
            TXcoins.setMinSize(230, 40); // Minimum size for the text field

            // Create an HBox for arranging the "Add Coins" button and its text field
            hbox = new HBox(10);
            hbox.getChildren().addAll(coins, TXcoins); // Add components to HBox
            hbox.setAlignment(Pos.CENTER); // Center align the HBox
            hbox.setPadding(new Insets(20)); // Add padding around HBox

            // Initialize grid pane (gpane) for action buttons
            gpane = new GridPane();
            gpane.add(file, 0, 0); // Add "Open File" button at row 0, column 0
            gpane.add(random, 1, 0); // Add "Add Random Coins" button at row 0, column 1
            gpane.add(players, 0, 1); // Add "ONE VS ONE" button at row 1, column 0
            gpane.add(showDp, 1, 1); // Add "Computer Mode" button at row 1, column 1
            gpane.setVgap(20); // Vertical gap between rows
            gpane.setHgap(20); // Horizontal gap between columns
            gpane.setAlignment(Pos.CENTER); // Center align the grid pane

            // Create a VBox to hold the HBox, grid pane, and text area
            vbox = new VBox(30);
            vbox.getChildren().addAll(hbox, gpane, tarea); // Add components to VBox
            vbox.setAlignment(Pos.CENTER); // Center align the VBox
            vbox.setPadding(new Insets(25)); // Add padding around VBox
        }
    }

