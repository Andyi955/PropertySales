package application;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Property Model
 * Has methods for both Agents and Properties
 *
 *
 * @name Andrew Ivory
 * @Version V2.0
 * @StudentNumber 20068082
 *
 *
 *
 */

public class PropertyModel {



    public LinkedList<Property> propertyList = new LinkedList<>();
    public LinkedList<Agent> agents = new LinkedList<>();

    public LinkedList<Property> getPropertyList() {
        return propertyList;
    }

    public LinkedList<Agent> getAgents() {
        return agents;
    }






    public Property addProp(int id, String desc,String address,String cat,String count,double price,double rating) {


        Property property = new Property(id, desc,address,cat,count,price,rating);

        propertyList.add(property);


        return property;
    }

    public Agent addAgentFirst(int id, String firstname, String surname, String email, String password,String phoneNo, String company) {


        Agent agent = new Agent(id,firstname,surname,email,password,phoneNo,company);

        agents.addFirst(agent);

        return agent;
    }

    public void deleteAgent(int index){
        agents.remove(index);
    }

    public void deleteProperty(int index) {
        propertyList.remove(index);

    }

    public LinkedList<Property> getCatandSub(String cat, String subCat){

        if (cat.equals("All") && subCat.equals("All")){
            return propertyList;
        }

        LinkedList<Property> searchResults = new LinkedList<>();

        for (Property item: propertyList){

            if (cat.equals("All") && item.getCounty().equals(subCat)){
                searchResults.add(item);
            }
            else if (subCat.equals("All") && item.getCategory().equals(cat)){
                searchResults.add(item);
            }
            else if (item.getCategory().equals(cat) && item.getCounty().equals(subCat)){
                searchResults.add(item);
            }

        }
        return searchResults;
    }

    public void saveProperties() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream

                (new FileWriter("Properties.xml"));

        out.writeObject(propertyList);

        out.close();

    }
    public void saveAgents() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectOutputStream out = xstream.createObjectOutputStream

                (new FileWriter("Agents.xml"));

        out.writeObject(agents);

        out.close();

    }
    public void loadAgents() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream

                (new FileReader("Agents.xml"));

        agents = (LinkedList<Agent>) is.readObject();

        is.close();

    }



    public void loadProperties() throws Exception {

        XStream xstream = new XStream(new DomDriver());

        ObjectInputStream is = xstream.createObjectInputStream

                (new FileReader("Properties.xml"));

        propertyList = (LinkedList<Property>) is.readObject();

        is.close();

    }




}

