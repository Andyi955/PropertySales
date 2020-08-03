package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Property view controller
 * Allows User to view certain property;
 *
 *
 * @name Andrew Ivory
 * @Version V2.0
 * @StudentNumber 20068082
 *
 *
 *
 */

public class ProductViewController {
    PropertyModel propertyView;
    private @FXML
    TextArea txtArea;
    @FXML
    private Label comboCategory;
    @FXML
    private Label comboSubCategory;

    @FXML
    private Label tblView;

    @FXML
    private Label columnId;
    @FXML
    private Label descriptionColumn;
    @FXML
    private Label addressColumn;
    @FXML
    private Label priceColumn;


    @FXML
    private Label ratingColumn;
    @FXML
    private Button returnToTableViewButton;

    public void initData(Property property) {
        columnId.setText("" + property.getId()); //int to String
         addressColumn.setText(property.getAddress());
        descriptionColumn.setText(property.getDescription());
        priceColumn.setText("" + property.getPrice());
        ratingColumn.setText("" + property.getRating());
        comboCategory.setText(property.getCategory());
        comboSubCategory.setText(property.getCounty());

    }
    public void changeScreenButtonPushed(ActionEvent event) throws IOException {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
