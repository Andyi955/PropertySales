package application;


import com.sun.javafx.menu.MenuItemBase;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.converter.DoubleStringConverter;
import javafx.util.converter.IntegerStringConverter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.ResourceBundle;



public class TableController extends Component implements Initializable {
    PropertyModel properties;
    ObservableList<Property> data = FXCollections.observableArrayList();

    DefaultTableModel tbl;

private @FXML
    TextArea txtArea;
    @FXML
    private ComboBox<String> comboCategory;
    @FXML
    private ComboBox<String> comboSubCategory;

    @FXML
    private TableView tblView;

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
    @FXML
    private TextField txtId;
    @FXML
    private TextField txtdesc;

    @FXML
    private TextField txtaddress;
    @FXML
    private TextField txtPrice;
    @FXML
    private TextField txtRating;
    @FXML
    private TextField searchField;
    @FXML
    private TextField txtindex;
    @FXML
   private ImageView imageName;

@FXML
        private TextField index;


    ObservableList<String> categories = FXCollections.observableArrayList("All", "Attached", "Semi-Attached");
    ObservableList<String> subCategories = FXCollections.observableArrayList("All", "Waterford", "Cork", "Dublin");
    @FXML
    private MenuItemBase detailedProductViewButton;



    public void initialize(URL location, ResourceBundle resources) {


        properties = new PropertyModel();  // initialise shop to a new (empty) collection of Products

      //  properties.addProp(30, "5 bedrooms", "11 street", "Attached", "Dublin", 20000, 75);
        // for test purposes -- add a few products

        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);


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
        descriptionColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        addressColumn.setCellFactory(TextFieldTableCell.forTableColumn());

        columnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter())); //https://stackoverflow.com/questions/31918342/javafx-tableview-edit-integer-cell

        priceColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        ratingColumn.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));


        //tableview is editable


        tblView.setEditable(true);


    }


  public void changedCategoryCombo(ActionEvent e) throws Exception {

      tblView.setItems( FXCollections.observableArrayList(properties.getCatandSub(comboCategory.getValue(),comboSubCategory.getValue())));




  }

    public void changedSubCategoryCombo(ActionEvent e) throws Exception {
        tblView.setItems( FXCollections.observableArrayList(properties.getCatandSub(comboCategory.getValue(),comboSubCategory.getValue())));


    }

//    public void listProperties(ActionEvent e)throws Exception{
//
//          tblView.setItems(FXCollections.observableArrayList(properties.listAllProperties()));
//    }



    public void addPropertyBtn(ActionEvent e) throws Exception {



       int i = Integer.parseInt(txtId.getText());

           String desc = txtdesc.getText();
           String add = txtaddress.getText();
           String cat = comboCategory.getValue();
           String count = comboSubCategory.getValue();
                double price = Double.valueOf(txtPrice.getText());
                double rating = Double.valueOf(txtRating.getText());

              Property property = properties.addProp(i,desc,add,cat,count,price,rating);


 tblView.getItems().add(property);

    }

    public void deleteProperty(ActionEvent e)throws Exception {


        int id = Integer.parseInt(index.getText());
        if ((id >= 0) && (id < properties.getPropertyList().size())) {

            properties.deleteProperty(id);
            properties.saveProperties();
            properties.loadProperties();

            ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


            tblView.getItems().setAll(data1);

        } else {
            txtArea.setText("No index");
        }
    }

        public void deleteSelectedRow (ActionEvent e) throws Exception {

            int dialogBtn = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog(this, "Delete Property", "Delete", dialogBtn);
            if (dialogResult == 0) {
                tblView.getItems().removeAll(tblView.getSelectionModel().getSelectedItem());



               for (int i = 0; i < data.size(); i++) {
                    Object[] objs = {data.get(i).getId(), data.get(i).getDescription(),
                            data.get(i).getAddress(), data.get(i).getCategory(),
                            data.get(i).getCounty(), data.get(i).getPrice(),
                            data.get(i).getRating()};
                    tblView.getItems().add(objs);
                }}


            }





    public void onEditChange(TableColumn.CellEditEvent<Property, Integer> propertyIntegerCellEditEvent) {

        Property property = (Property) tblView.getSelectionModel().getSelectedItem();
        property.setId(propertyIntegerCellEditEvent.getNewValue());
        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);


    }

    public void editDescription(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Property property = (Property) tblView.getSelectionModel().getSelectedItem();
        property.setDescription(propertyStringCellEditEvent.getNewValue());

        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);


    }

    public void onEditAddress(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Property property = (Property) tblView.getSelectionModel().getSelectedItem();
        property.setAddress(propertyStringCellEditEvent.getNewValue());
        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);
    }


    public void savePropertyBtn(ActionEvent e) throws Exception {
        properties.saveProperties();

    }

    public void loadPropertyBtn(ActionEvent e)throws Exception {
        properties.loadProperties();


        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);
    }

    public void editPrice(TableColumn.CellEditEvent<Property, Double> propertyDoubleCellEditEvent) {
        Property property = (Property) tblView.getSelectionModel().getSelectedItem();
        property.setPrice(propertyDoubleCellEditEvent.getNewValue());
        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);
    }

    public void editRating(TableColumn.CellEditEvent<Property, Double> propertyDoubleCellEditEvent) {
        Property property = (Property) tblView.getSelectionModel().getSelectedItem();
        property.setRating(propertyDoubleCellEditEvent.getNewValue());
        ObservableList<Property> data1 = FXCollections.observableArrayList(properties.getPropertyList());


        tblView.getItems().setAll(data1);
    }

    public void handleHomeBtn(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }



 /*   public void selectImageButtonPressed(ActionEvent event) {
        final FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Upload Product Pictures");

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        File source = fileChooser.showOpenDialog(stage);

        File destination = new File(""+source.getName());
        //imageSelectedLabel.setText(source.getName());

        if (source != null) {
            FileChannel sourceChannel = null;
            FileChannel destChannel = null;
            try {
                sourceChannel = new FileInputStream(source).getChannel();
                destChannel = new FileOutputStream(destination).getChannel();
                destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());
                imageSelected=source.getName();
                imageIsSelected=true;

            }
            catch(IOException e){
                System.out.println("IOException Image Selection");
            }
            catch (Exception e){
                System.out.println("Exception Image Selection");

            }
            finally{
                try {
                    sourceChannel.close();
                    destChannel.close();
                    System.out.println("Channels closed");
                }
                catch(IOException e){
                    System.out.println("IOException Close Channel");
                }
                catch (Exception e){
                    System.out.println("Exception Close Channel");
                }
            }
            imageName =destination.getName();
            System.out.println(destination.getAbsolutePath());
            Image image = new Image(destination.getAbsolutePath());
        }
    }

    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        //new FileChooser.ExtensionFilter("All Images", "*.*"),

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
    }*/


    }


