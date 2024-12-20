import java.io.*;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.ArrayList;

public class DeliveryStaff implements Serializable {

    private String firstName;
    private String lastName;
    private String location;
    private double averageReview; // Average review score
    public enum Status {   FREE, WORK; }
    private Status status;
    public ArrayList<Object> assignedOrders; // Initialized in constructor


    // Constructor
    public DeliveryStaff(String firstName, String lastName, String location, double averageReview, Status status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.averageReview = averageReview;
        this.status = status;
        this.assignedOrders = new ArrayList<>(); // Initialize assignedOrders list
    }

    // Method to initialize staff list
    public static ArrayList<DeliveryStaff> createStaffList() {
        ArrayList<DeliveryStaff> staffList = new ArrayList<>();
        staffList.add(new DeliveryStaff("Muhamed", "Walied", "Cairo, Nasr City", 4.5, Status.FREE));
        staffList.add(new DeliveryStaff("Abdullah", "Muhamed", "Giza, Dokki", 4.2, Status.FREE));
        staffList.add(new DeliveryStaff("Youseef", "Muhsen", "Cairo, Heliopolis", 4, Status.FREE));
        staffList.add(new DeliveryStaff("Amr", "Khaled", "Giza, Mohandessin", 4.7, Status.FREE));
        staffList.add(new DeliveryStaff("Ibrahim", "Salam", "Cairo, Zamalek", 4.2, Status.FREE));
        staffList.add(new DeliveryStaff("Ahmed", "Ashraf", "Cairo, New Cairo", 4.2, Status.FREE));
        staffList.add(new DeliveryStaff("Mido", "Sameh", "Alexandria, Corniche", 4.2, Status.FREE));
        return staffList;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Getters and Setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public double getAverageReview() {
        return averageReview;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setAverageReview(double averageReview) {
        if (averageReview < 0 || averageReview > 5) {
            throw new IllegalArgumentException("Review score must be between 0 and 5.");
        }
        this.averageReview = averageReview;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Assign orders based on location
    public String getDeliveryStatus(Order order, DeliveryStaff deliveryStaff) {


        if (order.getOrderLocation().equalsIgnoreCase(this.location)) {

            if (deliveryStaff.getStatus().equals("Free")) {
                return "The order assign successfully";
            } else if (deliveryStaff.getStatus().equals("Work")) {
                return "Delivery staff is currently busy";
            } else {
                return "Invalid Delivery Staff Status: " + deliveryStaff.getStatus();
            }
        }

        else {
            return "Order is not in your location";
        }
    }




    //////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void assignOrdersToStaff(List<Order> orders) {
        for (Order order : orders) {
            // Check if the staff's location matches the order's location and if the staff is free
            if (order.getOrderLocation().equalsIgnoreCase(this.location) && this.status == Status.FREE) {
                this.assignedOrders.add(order);
                System.out.println("Order assigned to " + firstName + " " + lastName);
            } else if (this.status == Status.WORK) {
                System.out.println("Staff is busy, cannot assign order.");
            }
        }
    }




    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Assign orders from a file
    public void assignOrdersFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            ArrayList<Order> orders = (ArrayList<Order>) in.readObject();

            for (Order order : orders) {
                if (order.getOrderLocation().equalsIgnoreCase(this.location) && status.equals("Free")) {
                    this.assignedOrders.add(order);
                } else if (status.equals("work")) {
                    System.out.println("Delivery cannot be assigned because the worker is currently busy.");
                }
            }
            System.out.println("Orders assigned from file.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading orders from file: " + e.getMessage());
        }
    }


    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //View assigned orders
    public void viewAssignedOrders() {
        System.out.println("Assigned Orders for " + firstName + " " + lastName + ":");
        if (assignedOrders.isEmpty()) {
            System.out.println("No orders assigned.");
        } else {
            assignedOrders.forEach(order -> {
                System.out.println(order);
                System.out.println("*==========================*");
            });
        }
    }

    // toString Method
    @Override
    public String toString() {
        return "DeliveryStaff{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", averageReview=" + averageReview +
                '}';
    }

    // Method to write DeliveryStaff object to file
    public void writeToFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this);
            System.out.println("DeliveryStaff object saved to file.");
        } catch (IOException e) {
            System.err.println("Error writing object to file: " + e.getMessage());
        }
    }

    // Method to read DeliveryStaff object from file
    public static DeliveryStaff readFromFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            DeliveryStaff deliveryStaff = (DeliveryStaff) in.readObject();
            return deliveryStaff;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading object from file: " + e.getMessage());
        }
        return null;
    }

}

