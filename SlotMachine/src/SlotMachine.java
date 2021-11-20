import java.awt.Font;
import static java.awt.SystemColor.text;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.RotateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import static javafx.application.Application.launch;

public class SlotMachine extends Application
{
     // Scoring System
        final int INITIAL_TOTAL_AMOUNT = 1000;
        final int INITIAL_BANK_AMOUNT = 1000;
       
        int totalAmount = INITIAL_TOTAL_AMOUNT;
        int recentAmount = 0;  
        int bankAmount = INITIAL_BANK_AMOUNT;

     // Media File
        final String SPIN_MUSIC_FILE_NAME = "spinningEffect.mp3";
        final String WIN_MUSIC_FILE_NAME = "winningEffect.mp3";

        MediaPlayer spinPlayer;
        MediaPlayer winPlayer;
   
        Label winningStatus;
       
                 
    public static void main(String[] args)
    {
        launch(args); 
    }
   
    public static int getRandomInteger(int min, int max)
    {
        int x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    private void Media()
    {
                File f = new File(SPIN_MUSIC_FILE_NAME);
                Media media = new Media(f.toURI().toString());
                spinPlayer = new MediaPlayer(media);
               
                File f2 = new File(WIN_MUSIC_FILE_NAME);
                Media media2 = new Media(f2.toURI().toString());
                winPlayer = new MediaPlayer(media2);
    }
   
     private void playMedia(MediaPlayer mediaPlayer){mediaPlayer.play();}
     
      private void stopMedia(MediaPlayer mediaPlayer ){mediaPlayer.stop();}
     
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException
    {   
       Media();

        // Quit Mode
        EventHandler<ActionEvent> buttonQuitHandler = new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent ae)
            {

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setTitle("Quit Mode");
                stage.setMinWidth(200);
                stage.setMinHeight(80);

                VBox vBox = new VBox(10);
                HBox hBox = new HBox(20);
                Label messageBox = new Label(" Do you want to quit this program ? ");
                Button yes = new Button("Yes");
                Button no = new Button ("No");
               
                yes.setOnAction(e -> {Platform.exit();});
                no.setOnAction(e -> {stage.close();});
                hBox.getChildren().addAll(no,yes);
                hBox.setAlignment(Pos.CENTER);

                vBox.getChildren().addAll(messageBox,hBox);
               
                Scene scene = new Scene(vBox);
                stage.getIcons().add(new Image("Jackpot.jpg"));
                stage.setScene(scene);
                stage.show();
            }
        };
       
         // Spin Mode
        EventHandler<ActionEvent> buttonSpinHandler = new EventHandler<ActionEvent>()
        {
            @Override
                public void handle(ActionEvent ae)
                {
                   
                    Stage stage = new Stage();
                    stage.getIcons().add(new Image("Jackpot.jpg"));
                    stage.setMinWidth(500);
                    stage.setMinHeight(500);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.setTitle(" Spin Result");
                  
                    playMedia(spinPlayer);


                   int rand1= getRandomInteger(1,3);
                   int rand2= getRandomInteger(1,3);
                   int rand3= getRandomInteger(1,3);
                    
                   Image pic1 = new Image("Cherry.jpg");
                    Image pic2 = new Image("Orange.jpg");
                    Image pic3 = new Image("Jackpot.jpg");
                   
                    ImageView view1 = null;
                    ImageView view2 = null;
                    ImageView view3 = null;
                    //first Picture
                   if(rand1==1)
                       view1 = new ImageView(pic1);
                   else if (rand1==2)
                       view1 = new ImageView(pic2);
                   else if (rand1==3)
                       view1 = new ImageView(pic3);
                   //second Picture
                   if(rand2==1)
                       view2 = new ImageView(pic1);
                   else if (rand2==2)
                       view2 = new ImageView(pic2);
                   else if (rand2==3)
                       view2 = new ImageView(pic3);
                   //third picture
                   if(rand3==1)
                       view3 = new ImageView(pic1);
                   else if (rand3==2)
                       view3 = new ImageView(pic2);
                   else if (rand3==3)
                       view3 = new ImageView(pic3);
                       
                    VBox picturesBox = new VBox(100);
                    picturesBox.setSpacing(10);
                    picturesBox.setAlignment(Pos.CENTER);

                    //Setting the preserve ratio of the image view
                    int sideLength = 150;
                    int space = 10;
                    int firstPicXPos = 20;
                    int secondPicXPos = firstPicXPos + sideLength + space;
                    int thirdPicXPos = secondPicXPos + sideLength + space;
                   
                    view1.setPreserveRatio(true);
                    view2.setPreserveRatio(true);
                    view3.setPreserveRatio(true);
                   
                    view1.setX(firstPicXPos);
                    view1.setY(50);
                    view1.setFitHeight(sideLength);
                    view1.setFitWidth(sideLength);
                   
                    view2.setX(secondPicXPos);
                    view2.setY(50);
                    view2.setFitHeight(sideLength);
                    view2.setFitWidth(sideLength);
                   
                    view3.setX(thirdPicXPos);
                    view3.setY(50);
                    view3.setFitHeight(sideLength);
                    view3.setFitWidth(sideLength);
                   
                    Group getAllPic = new Group(view1,view2,view3);
                   
                    picturesBox.getChildren().addAll(getAllPic);
                   
                    ArrayList<RotateTransition> rotateTransitions = new ArrayList<RotateTransition>();
                    for ( int i = 0; i < 3; i++)
                    {
                        //Creating a rotate transition
                        RotateTransition rotateTransition = new RotateTransition();
                       
                        rotateTransitions.add(rotateTransition);
                       
                        ImageView targetView = (ImageView) getAllPic.getChildren().get(i);
                       
                        // Setting node
                        rotateTransition.setNode(targetView);  
                       
                        //Setting the duration for the transition
                        rotateTransition.setDuration(Duration.millis(150));
                        rotateTransition.setOnFinished(e -> stopMedia(spinPlayer));
                                             
                        //Setting the angle of the rotation
                        rotateTransition.setByAngle(360);
                       
                        //Setting the cycle count for the transition
                        rotateTransition.setCycleCount(6);
                       
                        //Setting auto reverse value to false
                        rotateTransition.setAutoReverse(false);
                       
                        //Playing the animation
                        rotateTransition.play();
                    }  
                                       
                    winningStatus = new Label("");
                   
                    // Compare the picture values
                     if (rand1 == rand2 && rand1 == rand3)
                   {
                       recentAmount = 2;
                       
                       // plus one into player account
                       totalAmount += recentAmount;
                       bankAmount -=recentAmount;
                       playMedia(winPlayer);
                       winningStatus = new Label("*** You win $2 ***");
                       winningStatus.setTextFill(Color.web("#C71585"));
                       
                   }
                   else
                   {
                       recentAmount = -1;
                       
                         // minus one into player account and plus one for the bank account
                       totalAmount += recentAmount;
                       bankAmount -=recentAmount;
                       
                       if (totalAmount == 0)
                       {
                           // Lost the game
                            Stage window = new Stage();
                            window.initModality(Modality.APPLICATION_MODAL);
                            window.setTitle("Knock Out");
                            window.setMinWidth(400);
                            window.setMinHeight(150);
                           
                            Label resultStatus = new Label(" You have lost the game!! Your lost $" + (INITIAL_TOTAL_AMOUNT - totalAmount));    
                            Button BtnPlay = new Button("Start New Game");  
                            BtnPlay.setOnAction(e ->
                            {
                                totalAmount = INITIAL_TOTAL_AMOUNT;
                                bankAmount = INITIAL_BANK_AMOUNT;
                                window.close();
                            }); // End Button Play

                            VBox hbButtons = new VBox();
                            hbButtons.setSpacing(10);
                            hbButtons.setAlignment(Pos.CENTER);
                            hbButtons.getChildren().addAll(resultStatus,BtnPlay);
                           
                            StackPane root = new StackPane();
                            root.getChildren().addAll(picturesBox,hbButtons);
                           
                            //Creating a scene object
                            Scene scene = new Scene(root,Color.BEIGE);
                            window.setScene(scene);
                            window.show();


                       }
                   }
                     
                    Label scoreLable = new Label("Your Current Amount:$" + totalAmount);    
                    Label bankLable = new Label("- Bank Current Gain:$" + bankAmount);    
                    Button BtnExit = new Button("Play Again");
                    scoreLable.setTextFill(Color.web("#0076a3"));
                    bankLable.setTextFill(Color.web("#0076a3"));

                    HBox hbExit = new HBox();
                    hbExit.setPadding(new Insets(30, 10, 40, 0));
                    hbExit.setAlignment(Pos.CENTER);
                    hbExit.getChildren().addAll(BtnExit);
                   
                    HBox hbButtons = new HBox();
                    hbButtons.setSpacing(20);
                    hbButtons.setPadding(new Insets(30, 10, 40, 0));
                    hbButtons.setAlignment(Pos.CENTER);
                    hbButtons.getChildren().addAll(scoreLable, bankLable, winningStatus );

                    BtnExit.setOnAction(e ->
                    {
                        stage.close();
                        stopMedia(winPlayer);
                       
                    });

                    BorderPane border = new BorderPane();
                    border.setTop(hbButtons);
                    border.setCenter(picturesBox);
                    border.setBottom(hbExit);
                   
                    //Creating a scene object
                    Scene scene = new Scene(border,Color.BEIGE);
                    stage.setScene(scene);
                    stage.show();

                                 
            }
        };
       
        // Main Page
        primaryStage.setTitle("X-lot Machine!");
       
        // Button
        Button BtnQuit = new Button("Quit");  
        Button BtnSpin = new Button("Spin");
        BtnQuit.setOnAction(buttonQuitHandler);
        BtnSpin.setOnAction(buttonSpinHandler) ;
       

             
        // Image

        Image backgroundImage = new Image("Jackpot.jpg");
        ImageView imageView = new ImageView(backgroundImage);
        Group backGroundPic = new Group(imageView);  
       
         // Label for Game Rule
        Label scoreLable = new Label("Each time you get all matching piece, you will win $1 score. If you don't, you will lose $1. Good luck !!");
        scoreLable.setTextFill(Color.web("#0076a3"));


        /// Open new window
        StackPane root = new StackPane();
       
        // Set Button Size and Alignement  
        root.setPrefWidth(100);
        BtnQuit.setMinWidth(root.getPrefWidth());
        BtnSpin.setMinWidth(root.getPrefWidth());  
       
        // Set Image Size and Alignement    
        imageView.setX(50);
        imageView.setY(25);
        imageView.setFitHeight(455);
        imageView.setFitWidth(500);
        imageView.setPreserveRatio(true);  

        // Group Pic, Text, Button all together
        VBox hBoxBt = new VBox(20);
        hBoxBt.getChildren().addAll(BtnQuit,BtnSpin, scoreLable,backGroundPic);
        hBoxBt.setAlignment(Pos.CENTER);
        hBoxBt.setSpacing(10);
        hBoxBt.setPadding(new Insets(20, 10, 20, 0));
        root.getChildren().addAll(hBoxBt);
        primaryStage.setScene(new Scene(root, 700, 800));
        primaryStage.getIcons().add(new Image("Jackpot.jpg"));
        primaryStage.show();
    }
}
