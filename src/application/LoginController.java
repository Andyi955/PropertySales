package application;

import application.Agent;
import application.PropertyList;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.LinkedList;

public class LoginController {

    @FXML
    private TextField email;
    @FXML private PasswordField password;
    @FXML private TextArea txtAreaFeedback;
    @FXML
    private Image image;

    public void handleLoginBtn(ActionEvent e) throws Exception {

        if (email.getText().matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) { //https://stackoverflow.com/questions/46155/how-to-validate-an-email-address-in-javascript
            txtAreaFeedback.setText("Error with email");
        } else if (login(email.getText(), password.getText())) {
            txtAreaFeedback.setText("Successful Login");


            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("TableScreen.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);
            Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }

    }



    private boolean login(String email, String password) {
        LinkedList<Agent> agents = null;
        XStream xstream = new XStream(new DomDriver());
        try {
            ObjectInputStream is = xstream.createObjectInputStream(new FileReader("Agents.xml"));
            agents = (LinkedList<Agent>) is.readObject();
            is.close();
        }
        catch(FileNotFoundException e) {
            agents =  new LinkedList<Agent>();
            txtAreaFeedback.setText("Password File not located");
            return false;

        }
        catch (Exception e) {
            txtAreaFeedback.setText("Error accessing Password File");
            return false;
        }

        for (Agent agent: agents) {
            if(agent.getEmail().equals(email) && agent.getPassword().equals(password))
                return true;
        }
        return false;
    }
    public void handleHomeBtn(ActionEvent e) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("LoginScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void handleAdminLoginBtn(ActionEvent e) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminScreen.fxml"));
        Parent tableViewParent = loader.load();

        Scene tableViewScene = new Scene(tableViewParent);
        Stage window = (Stage)((Node)e.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }
}
