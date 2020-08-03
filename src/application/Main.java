package application;



import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main extends Application {

    static AnchorPane root;
    static List<AnchorPane> anchor = new ArrayList<AnchorPane>();
    private static int sceneIndex = 0;
    //  private static Agent agent = null;

   PropertyList<String> propertyList = new PropertyList<>();




    @Override
    public void start(Stage primaryStage) throws Exception{
        root = (AnchorPane)FXMLLoader.load(getClass().getResource("Anchor.fxml"));

        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("MainScreen.fxml"))); //index 0
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("LoginScreen.fxml"))); //index 1
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("TableScreen.fxml"))); //index 2
        anchor.add((AnchorPane)FXMLLoader.load(getClass().getResource("AdminScreen.fxml"))); //index 3




        root.getChildren().add(anchor.get(0));

        primaryStage.setTitle("Properties for Sale");
        primaryStage.setScene(new Scene(root, 675, 400));
        primaryStage.show();

    }
    public static void set_pane(int index){
        //TODO check that the index is valid ie. >0 and <size of ArrayList
        root.getChildren().remove(anchor.get(sceneIndex));  //remove the existing pane from the root
        root.getChildren().add(anchor.get(index));          //add the selected pane to the root
        sceneIndex=index;
//update the stored sceneIndex
    }
    public static void main(String[] args) {


        launch(args);
    }
}
