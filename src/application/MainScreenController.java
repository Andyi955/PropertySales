package application;


import com.sun.javafx.menu.MenuItemBase;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;
import java.util.function.Predicate;

/**
 * MainScreenController runs the mainscreen fxml
 * and populates it with a table and various methods/buttons
 *
 *
 *
 * @name Andrew Ivory
 * @Version V2.0
 * @StudentNumber 20068082
 *
 *
 *
 */

public class MainScreenController implements Initializable {
    PropertyModel properties;
    ObservableList<Property> data = FXCollections.observableArrayList();


    private @FXML
    TextArea txtArea;
    @FXML
    private ComboBox<String> comboCategory;
    @FXML
    private ComboBox<String> comboSubCategory;

    @FXML
    private TableView tblView;
    @FXML
    public TableView tblView_2;

    @FXML
    private TableColumn<Property, Integer> columnId;
    @FXML
    private TableColumn<Property, String> descriptionColumn;
    @FXML
    private TableColumn<Property, String> addressColumn;
    @FXML
    private TableColumn<Property, Double> priceColumn;


    @FXML
    private TableColumn<Property, Double> ratingColumn;
@FXML private TextField search;

    ObservableList<String> categories = FXCollections.observableArrayList("All", "Attached", "Semi-Attached");
    ObservableList<String> subCategories = FXCollections.observableArrayList("All", "Waterford", "Cork", "Dublin");
    @FXML
    private MenuItemBase detailedProductViewButton;


    public void initialize(URL location, ResourceBundle resources) {
        properties = new PropertyModel();  // initialise shop to a new (empty) collection of Products

        try {
            PropertyList<Property> propertyList = new PropertyList<>();
            ArrayList<Property> prop = new ArrayList<>();
            try {



                XStream xstream = new XStream(new DomDriver());

                ObjectInputStream is = xstream.createObjectInputStream

                        (new FileReader("properties.xml"));

                propertyList = (PropertyList<Property>) is.readObject();

                is.close();

            } catch (Exception e) {

            }
            for (int i = 0; i < propertyList.size(); i++) {
                Property prop1 = (Property) propertyList.get(i);
                prop.add(prop1);
            }

            ObservableList<Property> data1 = FXCollections.observableArrayList(prop);


            tblView.getItems().setAll(data1);
            // for test purposes -- add a few products




            columnId.setCellValueFactory(new PropertyValueFactory<Property, Integer>("id"));
            addressColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("address"));
            descriptionColumn.setCellValueFactory(new PropertyValueFactory<Property, String>("description"));
            priceColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("price"));


            ratingColumn.setCellValueFactory(new PropertyValueFactory<Property, Double>("rating"));


            comboCategory.setItems(categories);
            comboSubCategory.setItems(subCategories);
            comboCategory.setValue("All");
            comboSubCategory.setValue("All");

            //allows to edit text in column


            //tableview is editable
          /*  FilteredList<Property> filteredList = new FilteredList<>(data1, e -> true);
            search.setOnKeyReleased(e ->{
                search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    filteredList.setPredicate((Predicate<? super Property>) prop1 ->{
                        if (newValue == null || newValue.isEmpty()){
                            return true;
                        }
                        String lowerCaseFilter = newValue.toLowerCase();
                        if (prop1.getDescription().contains(newValue)){
                            return true;
                        }else if(prop1.getCounty().contains(lowerCaseFilter)){
                            return true;
                        }
                        return false;
                    });
                });
                SortedList<Property> sortedList = new SortedList<>(filteredList);
                sortedList.comparatorProperty().bind(tblView.comparatorProperty());
                tblView.getItems().setAll(sortedList);
            });*/

        }
        catch (Exception e){
            txtArea.setText("error");
        }


    }
        public void changedCategoryCombo(ActionEvent e) throws Exception {
            tblView.setItems( FXCollections.observableArrayList(properties.getCatandSub(comboCategory.getValue(),comboSubCategory.getValue())));

        }

        public void changedSubCategoryCombo(ActionEvent e) throws Exception {
            tblView.setItems( FXCollections.observableArrayList(properties.getCatandSub(comboCategory.getValue(),comboSubCategory.getValue())));

        }
    public void changeSceneToDetailedView(ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("propertyView.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);

        //access the controller and call a method
        ProductViewController controller = loader.getController();
        Property productToDisplay =(Property) tblView.getSelectionModel().getSelectedItem(); //cast as Product
        if(productToDisplay == null) //sometimes no Product will have been selected
            return;

        controller.initData(productToDisplay);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void handleLoginBtn(ActionEvent e) throws Exception
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();


    }
    public void loadPropertyBtn(ActionEvent e)throws Exception {
        properties.loadProperties();


        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);
    }


    }







