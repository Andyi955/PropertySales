package application;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * registered agent has agent table
 * and various methods/button that
 * are implemented for the table
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

public class RegisterAgent implements Initializable {

     PropertyModel agents;
    @FXML
    private TextField id;

    @FXML
    private TextField firstname;
    @FXML
    private TextField surname;
    @FXML
    private TextField email;
    @FXML
    private DatePicker dob;
    @FXML
    private TextField phoneNo;
    @FXML
    private TextField address;
    @FXML
    private TextField companyName;
    @FXML
    private TextField index;


    @FXML
    private TextField password;
    @FXML
    private PasswordField repeat_password;

    @FXML
    private TextArea txtAreaFeedback;

    @FXML
    private TableColumn<Property, Integer> columnId;
    @FXML
    private TableColumn<Property, String> firstnameC;
    @FXML
    private TableColumn<Property, String> Lastnamec;
    @FXML
    private TableColumn<Property, String> passwrdC;
    @FXML
    private TableColumn<Property, String> emailcolumn;
    @FXML
    private TableColumn<Property, String> phoneNoC;

    @FXML
    private TableColumn<Property, String> Company;

@FXML
    private TableView tableView;







    public void addAgentFirst(ActionEvent e) throws Exception {



        int agentId = Integer.parseInt(id.getText());



        String firstnameText = firstname.getText();
        String surnameText = surname.getText();

        String companyNameText = companyName.getText();
        String phoneNoText = phoneNo.getText();
        String passwordText = password.getText();
        String emailText = email.getText();

        Agent agent1 = agents.addAgentFirst(agentId,firstnameText,surnameText,emailText,passwordText,phoneNoText,companyNameText);


        tableView.getItems().add(agent1);
        agents.saveAgents();

    }

    public void deleteAgent(ActionEvent e)throws Exception {


        int id = Integer.parseInt(index.getText());
        if ((id >= 0) && (id < agents.getAgents().size())) {

            agents.deleteAgent(id);
            agents.saveAgents();
            agents.loadAgents();

            ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


            tableView.getItems().setAll(data1);

        } else {
            txtAreaFeedback.setText("No index");
        }
    }

    public void saveAgentBtn(ActionEvent e) throws Exception {
        agents.saveAgents();

    }

    public void loadAgentBtn(ActionEvent e)throws Exception {
        agents.loadAgents();


        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }



    public void initialize(URL location, ResourceBundle resources) {


        agents = new PropertyModel();  // initialise shop to a new (empty) collection of Products

        //agents.addAgentFirst(1, "andy", "ivory", "andy@hotmail.com", "andy", "087473","Pamler");
        // for test purposes -- add a few products

        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);


        columnId.setCellValueFactory(new PropertyValueFactory<Property, Integer>("agentId"));
        firstnameC.setCellValueFactory(new PropertyValueFactory<Property, String>("firstname"));
        Lastnamec.setCellValueFactory(new PropertyValueFactory<Property, String>("surname"));
        emailcolumn.setCellValueFactory(new PropertyValueFactory<Property, String>("email"));
        passwrdC.setCellValueFactory(new PropertyValueFactory<Property, String>("password"));
        phoneNoC.setCellValueFactory(new PropertyValueFactory<Property, String>("phoneNo"));
        Company.setCellValueFactory(new PropertyValueFactory<Property, String>("companyName"));

       firstnameC.setCellFactory(TextFieldTableCell.forTableColumn());

       Lastnamec.setCellFactory(TextFieldTableCell.forTableColumn());
        emailcolumn.setCellFactory(TextFieldTableCell.forTableColumn());

        passwrdC.setCellFactory(TextFieldTableCell.forTableColumn());
        phoneNoC.setCellFactory(TextFieldTableCell.forTableColumn());

        Company.setCellFactory(TextFieldTableCell.forTableColumn());



        columnId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        tableView.setEditable(true);


}

    public void editAgentId(TableColumn.CellEditEvent<Property, Integer> propertyIntegerCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setAgentId(propertyIntegerCellEditEvent.getNewValue());
        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }

    public void editFirstname(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setFirstname(propertyStringCellEditEvent.getNewValue());
        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }

    public void editSurname(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setSurname(propertyStringCellEditEvent.getNewValue());
        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }

    public void editEmail(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setEmail(propertyStringCellEditEvent.getNewValue());
        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }

    public void editCompany(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setCompanyName(propertyStringCellEditEvent.getNewValue());
        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }

    public void editProperty(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setPhoneNo(propertyStringCellEditEvent.getNewValue());

        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
    }

    public void editPassword(TableColumn.CellEditEvent<Property, String> propertyStringCellEditEvent) {
        Agent agent = (Agent) tableView.getSelectionModel().getSelectedItem();
        agent.setFirstname(propertyStringCellEditEvent.getNewValue());

        ObservableList<Agent> data1 = FXCollections.observableArrayList(agents.getAgents());


        tableView.getItems().setAll(data1);
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
}
