package application;


import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Comparator;

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


public class Property  {

    // private static int propertyId=2056781;

    private int id;

    private String description;

    private String address;

//   Image image = new Image("C:\\Users\\Andyi\\OneDrive - Waterford Institute of Technology\\PropertySales\\src\\img\\home.jpg");



    private String category;

    private String county;



    private double rating;



    private double price;







    private String phoneNo;
    private String imageName;


    public Property(int id,String description, String address, String cat,
                    String count,  double rating , double price,
                     String phoneNo,String imageName)  {

        super();
        this.id = id;
        this.description = description;
        this.address = address;
        category = cat;
        county = count;

        this.rating = rating;

        this.price = price;

        this.phoneNo = phoneNo;
        this.imageName = imageName;
    }
    public Property(int id,String description, String address, String cat,
                    String count, double price,
                    double rating)  {

        super();
        this.id = id;
        this.description = description;
        this.address = address;
        category = cat;
        county = count;
        this.rating = rating;
        this.price = price;
        //this.imageName = null;


      //  this.phoneNo = phoneNo;
    }

    public Property() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    @Override
    public String toString() {
        return " Property: " +
                " ID: " + id + '\n' +
                " Description: " + description + '\n' +
                " Type of property: " + category + '\n' +
                " Address: " + address + '\n' +
                " County: " + county + '\n' +


                " Rating: " + rating + '\n'+

                " Max Price: " + price + '\n'+
                 "Image name: " + imageName +


                " PhoneNo: " + phoneNo;
    }
/* public static Comparator<Property> propertyComparator = new Comparator<Property>() {
     @Override
     public int compare(Property o1, Property o2) {

        return (int) (o1.getMinprice()-o2.getPrice());

     }
 };*/




}
