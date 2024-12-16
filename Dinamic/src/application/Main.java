package application;
	
import javafx.animation.SequentialTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.*;
import java.util.Random;
import java.util.Scanner;

public class Main extends Application {
    private Button start;
    private FileChooser file;
    private File f;
    private HBox hbox1vs1;
    private int[] coins;
    int [] player1,player2;
    /*
     The main interface, which when you press start, you move you to the game interface
     */
    @Override
    public void start(Stage stage) throws IOException {
        start = new Button("START GAME");

        start.setLayoutX(280);
        start.setLayoutY(220);
        start.setMinSize(120,30);
        start.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 20px; -fx-font-weight: bold;");


        Pane root = new Pane();
        root.getChildren().add(start);
     // Set the background image for the scene
        root.setStyle(
                "-fx-background-image: url('Gameph.png');" +
                        "-fx-background-size: cover;"
        );

        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("Hello Application");
        stage.setScene(scene);
        stage.show();
        start.setOnAction(e->{
            Playgame(stage);
        });
    }
    
    
    public void Computer(Stage stage){
        ComputeFx comfx=new ComputeFx();
        BorderPane root = new BorderPane();

        root.setBottom(comfx.hbox1);
//        root.setCenter(comfx.vbox);
        root.setTop(comfx.back);
     // Set the background image for the scene
        root.setStyle(
                "-fx-background-image: url('computerfx.png');" +
                        "-fx-background-size: cover;"
        );
        int n = coins.length;
        int[][] dpTable = new int[n][n];
        int[][] chosenCoins = new int[n][n]; // To store choices: 1 for left, -1 for right

        // Initialize base cases
        for (int j = 0; j < n; j++) {
            dpTable[j][j] = coins[j]; // Single coin case
            chosenCoins[j][j] = 1;    // Only one option: pick the coin on the left
        }

        for (int j = 0; j < n - 1; j++) {
            if (coins[j] > coins[j + 1]) {
                dpTable[j][j + 1] = coins[j];
                chosenCoins[j][j + 1] = 1; // Choose left
            } else {
                dpTable[j][j + 1] = coins[j + 1];
                chosenCoins[j][j + 1] = -1; // Choose right
            }
        }

        // Fill DP table and track choices
        for (int i = 2; i < coins.length; i++) {
            for (int j = 0; j + i < coins.length; j++) {
                int start = coins[j] + Math.min(dpTable[j + 2][j + i],
                        dpTable[j + 1][j + i - 1]);
                int end = coins[j + i] + Math.min(dpTable[j + 1][j + i - 1],
                        dpTable[j][j + i - 2]);

                if (start > end) {
                    dpTable[j][i+j] = start;
                    chosenCoins[j][i+j] = 1; // Choose left
                }else if(start==end) {
                	if(coins[i]>coins[j]) {
                		dpTable[j][j + i] = coins[i];
                		chosenCoins[j][i+j] = 1;
                    }else {
                    	dpTable[j][j + i] = coins[j];
                    	chosenCoins[j][i+j] = -1;
                    }
                }
                	else {
                
                    dpTable[j][i+j] = end;
                    chosenCoins[j][i+j] = -1; // Choose right
                }
            }
        }
        // Maximum amount player 1 can win
        comfx.Exp1.setText("Expected Result = " + dpTable[0][n - 1]);

        
       VBox vb=new VBox(20);
       VBox vb2=new VBox(20);
       HBox hBox=new HBox(20);
      FlowPane flowplayer1=new FlowPane();
      flowplayer1.getChildren().add(comfx.select1);
      flowplayer1.setAlignment(Pos.CENTER);
      flowplayer1.setHgap(20);
      flowplayer1.setVgap(10);

        FlowPane flowplayer2=new FlowPane();
        flowplayer2.getChildren().add(comfx.select2);
        flowplayer2.setAlignment(Pos.CENTER);
        flowplayer2.setHgap(20);
        flowplayer2.setVgap(10);

        int i = 0, j = n - 1,sumplayer2=0;
        boolean isPlayer1Turn = true;

        while (i <= j) {

            if (chosenCoins[i][j] == 1) { // Pick left
                Label labeli=new Label(" , "+coins[i]);
                labeli.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
                if (isPlayer1Turn) {
                    flowplayer1.getChildren().add(labeli);
                } else {
                    flowplayer2.getChildren().add(labeli);
                    sumplayer2+=coins[i];
                }
                i++;
            } else { // Pick right
                Label labelj=new Label(" , "+coins[j]);
                labelj.setStyle("-fx-text-fill: #04f1db; -fx-font-size: 17px; -fx-font-weight: bold;");
                if (isPlayer1Turn) {

                    flowplayer1.getChildren().add(labelj);
                } else {
                    flowplayer2.getChildren().add(labelj);
                    sumplayer2+=coins[j];
                }
                j--;
            }
            isPlayer1Turn = !isPlayer1Turn; // Alternate turns
        }
        
        comfx.Exp2.setText("Expected Result = " + sumplayer2);
        
        vb.getChildren().addAll(comfx.lcom1,comfx.Exp1,flowplayer1);
        vb.setAlignment(Pos.CENTER);
        vb.setPadding(new Insets(25));
        
        vb2.getChildren().addAll(comfx.lcom2,comfx.Exp2,flowplayer2);
        vb2.setAlignment(Pos.CENTER);
        vb2.setPadding(new Insets(25));
        
        hBox.getChildren().addAll(vb,vb2);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(25));
        
        
        ScrollPane scrollPane = new ScrollPane(hBox);
        scrollPane.setPadding(new Insets(25));
    	scrollPane.setFitToWidth(true); // Allow content to stretch horizontally
    	scrollPane.setFitToHeight(true); // Allow content to stretch vertically
    	scrollPane.setPannable(true); // Enable dragging with mouse
 
    	root.setCenter(scrollPane);
    	



        Scene scene = new Scene(root, 1000, 550);
        stage.setTitle("Computer Mode");
        stage.setScene(scene);
        stage.show();

        comfx.back.setOnAction(e->{
            Playgame(stage);
        });
        comfx.Dp.setOnAction(e->{
            Dptable(stage);
        });
        comfx.rest.setOnAction(e->{
        	Computer(stage);
        });
    }
    public void Dptable(Stage stage){
    	ComputeFx comfx = new ComputeFx();
    	BorderPane root = new BorderPane();

    	
    	root.setTop(comfx.back);
    	// Set the background image for the scene
    	root.setStyle(
    	        "-fx-background-image: url('dptable.png');" +
    	        "-fx-background-size: cover;"
    	);

    	// Create VBox to hold all the content
    	VBox vdp = new VBox(20);

    	// First row of labels
    	HBox h1 = new HBox(20);
    	Label l0 = new Label("~_~");
    	l0.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #04f1db; -fx-font-size: 30px; -fx-font-weight: bold;");
    	l0.setMinWidth(60);
    	h1.getChildren().add(l0);

    	// Add column headers
    	for (int i = 0; i < coins.length; i++) {
    	    Label label = new Label("" + coins[i]);
    	    label.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #04f1db; -fx-font-size: 30px; -fx-font-weight: bold;");
    	    label.setMinWidth(60);
    	    h1.getChildren().add(label);
    	}
    	h1.setAlignment(Pos.CENTER);
    	vdp.getChildren().add(h1);

    	// Compute the DP table
    	int[][] dptable = Dynamic(coins);

    	// Add the DP table rows
    	for (int i = 0; i < dptable.length; i++) {
    	    Label l = new Label("" + coins[i]);
    	    l.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #04f1db; -fx-font-size: 30px; -fx-font-weight: bold;");
    	    l.setMinWidth(60);
    	    
    	    HBox hBox = new HBox(20);
    	    hBox.getChildren().add(l);

    	    for (int j = 0; j < dptable.length; j++) {
    	        Label label = new Label("" + dptable[i][j]);
    	        label.setStyle("-fx-background-color: #ffffff; -fx-text-fill: #04f1db; -fx-font-size: 30px; -fx-font-weight: bold;");
    	        label.setMinWidth(60);
    	        hBox.getChildren().add(label);
    	    }
    	    hBox.setAlignment(Pos.CENTER);
    	    vdp.getChildren().add(hBox);
    	}

    	vdp.setAlignment(Pos.TOP_CENTER);
    	vdp.setPadding(new Insets(30));

    	// Wrap the VBox in a ScrollPane
    	ScrollPane scrollPane = new ScrollPane(vdp);
    	scrollPane.setFitToWidth(true); // Allow content to stretch horizontally
    	scrollPane.setFitToHeight(true); // Allow content to stretch vertically
    	scrollPane.setPannable(true); // Enable dragging with mouse

    	// Set ScrollPane to the center of the BorderPane
    	root.setCenter(scrollPane);

    	// Create and show the scene
    	Scene scene = new Scene(root, 1000, 550);
    	stage.setTitle("DP table");
    	stage.setScene(scene);
    	stage.show();

    	// Handle "back" button action
    	comfx.back.setOnAction(e -> {
    	    Computer(stage);
    	});

    }
    
    /*
     The interface in which we add coins and choose the process from which it will be added
     And then choose the gameplay: computer vs computer // or player vs player
     */

    public  void Playgame(Stage stage) {
        Gamefx gamefx=new Gamefx();
        BorderPane root = new BorderPane();

        root.setCenter(gamefx.vbox);
     // Set the background image for the scene
        root.setStyle(
                "-fx-background-image: url('ph2.png');" +
                        "-fx-background-size: cover;"
        );

        Scene scene = new Scene(root, 1000, 550);
        stage.setTitle("Coins Game");
        stage.setScene(scene);
        stage.show();
        
        /*
        Adding coins from textfield by adding values and between them (,) 
        and storing them inside the array
        must be coins are even
        */
        gamefx.coins.setOnAction(e->{
        try {
            String strCoin = gamefx.TXcoins.getText().trim();
            String[] str = strCoin.split(",");
            if(str.length%2==0) {
                coins = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                    coins[i] = Integer.parseInt(str[i].trim());
                }
                gamefx.TXcoins.setText("");
            }else{
                ResAlert("Error !","Error in input \n The number of coins must be an even number...");
            }

        }catch(Exception ex){
            ResAlert("Error !","Error in input \n input example: 1,2,3...");
        }
        });
        
        /*
         Adding coins through the file 
         so that the file in the first line contains the size of the coins and the second line,
          the values between them (,) and use in this process filechooser
         */
        file = new FileChooser();
        gamefx.file.setOnAction(e->{
            file.setTitle("Open File ");
            file.setInitialDirectory(new File("C:\\"));
            f = file.showOpenDialog(stage);
            if (f!=null){
                try {
                    Scanner lines=new Scanner(f);
                    String l1=lines.nextLine().trim();
                    int length=Integer.parseInt(l1);
                    if(length%2==0) {
                        String line2 = lines.nextLine();
                        String[] l2 = line2.trim().split(",");
                        coins = new int[length];
                        for (int i = 0; i < length; i++) {
                            coins[i] = Integer.parseInt(l2[i]);
                        }
                        gamefx.tarea.setText(l1 + "\n" + line2);
                    }else{
                        ResAlert("Error !","Error in input \n The number of coins must be an even number...");
                    }


                }catch (Exception ex){
                    ResAlert("Error !","Error in input File ...");
                }
            }
        });

        gamefx.players.setOnAction(e->{
            if(coins!=null) {
                players(stage);
            }else{
                ResAlert("Error !","Error the arraycoins is null ...");
            }
        });

        gamefx.random.setOnAction(e->{
            ranomCoin(stage,gamefx);

        });
        gamefx.showDp.setOnAction(e->{
            if(coins!=null) {
                Computer(stage);
            }else{
                ResAlert("Error !","Error the arraycoins is null ...");
            }

        });

    }
    
    /*
     Adding coins by random number
     The number of values, the limit and extent of values that can be reduced are determined
     */
    public void ranomCoin(Stage stage,Gamefx gamefx){
        BorderPane root = new BorderPane();

        root.setCenter(gamefx.vbox2);
        root.setTop(gamefx.back);
     // Set the background image for the scene
        root.setStyle(
                "-fx-background-image: url('ran.png');" +
                        "-fx-background-size: cover;"
        );

        Scene scene = new Scene(root, 500, 270);
        stage.setTitle("Hello Application");
        stage.setScene(scene);
        stage.show();

        gamefx.add.setOnAction(e->{
            try {
                if (!gamefx.tmax.getText().isEmpty() && !gamefx.tlength.getText().isEmpty()) {
                    Random random = new Random();
                    int len=Integer.parseInt(gamefx.tlength.getText().trim());
                    if(len%2==0) {
                        int max = Integer.parseInt(gamefx.tmax.getText().trim());
                        coins = new int[len];
                        for (int i = 0; i < len; i++) {
                            coins[i] = random.nextInt(max) + 1;
                        }
                    }else{
                        ResAlert("Error !","Error in input \n The number of coins must be an even number...");
                    }

                    Playgame(stage);
                } else {
                    ResAlert("Error !", "Error AN TextField is Empty...");
                }
            }catch (Exception ex){
                ResAlert("Error !","Error in the TextField not a number ...");
            }
        });
        gamefx.back.setOnAction(e->{
            Playgame(stage);
        });
    }
    
    /*
     Interface for choosing the names of the players that will have some
     */
    public void players(Stage stage){
        PlayersFx plyfx=new PlayersFx();


        BorderPane root = new BorderPane();
        root.setBottom(plyfx.vbox);
        root.setTop(plyfx.back);
     // Set the background image for the scene
        root.setStyle(
                "-fx-background-image: url('1vs1.png');" +
                        "-fx-background-size: cover;"
        );

        Scene scene = new Scene(root, 650, 350);
        stage.setTitle("Hello Application");
        stage.setScene(scene);
        stage.show();
        plyfx.play.setOnAction(e->{
            String user1=plyfx.tname1.getText();
            String user2=plyfx.tname2.getText();
            if(!user1.isEmpty() && !user2.isEmpty()){
                oneVSone(user1,user2,stage);
            }else{
                ResAlert("Error !","One of the TextFeild is Empty");
            }
        });
        plyfx.back.setOnAction(e->{
            Playgame(stage);
        });
    }
    
    
    /*
     The interface of the game in which the players challenge each other 
     so that each of them can choose a coin from the first or the other
     */
    public void oneVSone(String user1, String user2, Stage stage) {
        OnevsoneFx oneFX = new OnevsoneFx(user1, user2);
        hbox1vs1=new HBox();
        hbox1vs1.getChildren().addAll(oneFX.back,oneFX.hbox);



        BorderPane root = new BorderPane();
        root.setTop(hbox1vs1);
        oneFX.tsum1.setEditable(false);
        oneFX.tsum2.setEditable(false);
        
        root.setBottom(oneFX.reset);

        FlowPane coinbox = new FlowPane();
        Button[] buttons = new Button[coins.length]; // Array to keep track of buttons
        boolean[] pressed = new boolean[coins.length]; // Track if a button was pressed

        // Use single-element arrays for mutable variables
        int[] sum1 = {0};
        int[] sum2 = {0};
        boolean[] isPlayer1Turn = {true}; // Track player turn with a single-element array

        // Create buttons based on the array
        for (int i = 0; i < coins.length; i++) {
            Button btn = new Button(String.valueOf(coins[i]));
            btn.setStyle("-fx-background-color: #ff6600; -fx-text-fill: black; -fx-font-size: 14px; -fx-font-weight: bold;");

            // Disable all buttons except the first and last initially
            if (i > 0 && i < coins.length - 1) {
                btn.setDisable(true);
            }

            // Store button in the array for later reference
            buttons[i] = btn;

            // Add event handler to the button
            final int index = i;
            btn.setOnAction(e -> {
                btn.setDisable(true); // Disable the clicked button
                pressed[index] = true; // Mark this button as pressed

                // Update the sum for the current player
                int value = Integer.parseInt(btn.getText().trim());

                if (isPlayer1Turn[0]) {
                    sum1[0] += value;
                    oneFX.tsum1.setText(String.valueOf(sum1[0]));
                } else {
                    sum2[0] += value;
                    oneFX.tsum2.setText(String.valueOf(sum2[0]));
                }

                // Alternate the player's turn
                isPlayer1Turn[0] = !isPlayer1Turn[0];

                // Enable the next button if it exists and hasn't been pressed before
                if (index + 1 < coins.length && !pressed[index + 1]) {
                    buttons[index + 1].setDisable(false);
                }

                // Enable the previous button if it exists and hasn't been pressed before
                if (index - 1 >= 0 && !pressed[index - 1]) {
                    buttons[index - 1].setDisable(false);
                }
            });

            // Add the button to the FlowPane
            coinbox.getChildren().add(btn);
            if (i==(coins.length-1)) {
            	if(sum1[0]>sum2[0]) {
                	oneFX.win1.setText("The winner is "+user1);
                	SequentialTransition sequentialTransition = new SequentialTransition();
                    addZoomEffect(oneFX.win1, 0.1, 1.0, 850, sequentialTransition); // Zoom effect as part of a sequence 
                }
            }
        }
        

        coinbox.setAlignment(Pos.TOP_CENTER);
        coinbox.setVgap(10);
        coinbox.setHgap(10);
        coinbox.setPadding(new Insets(25));
        root.setCenter(coinbox);
//        vbox1vs1.getChildren().addAll(oneFX.hbox,coinbox);
//        vbox1vs1.setAlignment(Pos.TOP_CENTER);
//        vbox1vs1.setPadding(new Insets(25));
//        root.setCenter(vbox1vs1);

        // Set the background image for the scene
        root.setStyle(
                "-fx-background-image: url('2players.png');" +
                        "-fx-background-size: cover;"
        );
        
        oneFX.reset.setOnAction(e->{
        	oneVSone(user1, user2, stage);
        });
        
        oneFX.back.setOnAction(e->{
        	Playgame(stage);
        });

        Scene scene = new Scene(root, 700, 400);
        stage.setTitle("One vs One Game");
        stage.setScene(scene);
        stage.show();
    }



    private void addZoomEffect(Label win1, double d, double e, int i, SequentialTransition sequentialTransition) {
		// TODO Auto-generated method stub
		
	}


	private void ResAlert(String st, String discribtion) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(st);
        alert.setContentText(discribtion);
        alert.setHeaderText(null);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setPrefSize(350, 110);
        dialogPane.setStyle("-fx-border-color: red; " + "-fx-border-width: 3; " + "-fx-padding: 13; "
                + "-fx-font-size: 12px; " + "-fx-font-family: 'Arial';");

        alert.showAndWait();
    }
	


    

    private int[][] Dynamic(int[] c) {
        // Initialize the dpTable and backtrack arrays
        int [][] dp = new int[c.length][c.length];

        for (int i = 0; i < c.length; i++) {
            dp[i][i] = c[i]; //select that one coin
        }

        for (int j = 0; j < c.length - 1; j++) {
        	//select the max between two coins
            dp[j][j + 1] = Math.max(c[j], c[j + 1]);
        }

        for (int i = 2; i < c.length; i++) {
            for (int j = 0; j + i < c.length; j++) {
                int start = c[j] + Math.min(dp[j + 2][j + i],
                        dp[j + 1][j + i - 1]);
                int end = c[j + i] + Math.min(dp[j + 1][j + i - 1],
                        dp[j][j + i - 2]);

                // Store the index of the coin that was chosen
                if (start > end) {
                    dp[j][j + i] = start;
                } else if(start<end){
                    dp[j][j + i] = end;
                }else if(start==end) {
                	if(c[i]>c[j]) {
                	dp[j][j + i] = c[i];
                	}else {
                	dp[j][j + i] = c[j];
                	}
                }
            }
        }
        //return dptable
        return dp;
    }





    public static void main(String[] args) {
        launch();
    }
}
