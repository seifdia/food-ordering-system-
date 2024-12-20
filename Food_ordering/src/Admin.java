
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Admin {

    public String First_name;
    public String Last_name;
    private String email;
    private String password;
    private ArrayList<Restaurant> restaurants;

    public Admin() {
        this.restaurants = initializeRestaurants();
    }

    private static ArrayList<Restaurant> initializeRestaurants() {
        ArrayList<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant1 = new Restaurant("Kansas", "Cairo, Nasr City", "Fried Chicken");
        restaurant1.addMenuItem("Fried Chicken Bucket (12 pcs)", "Main Meal", 280.0);
        restaurant1.addMenuItem("Crispy Chicken Sandwich", "Main Meal", 100.0);
        restaurant1.addMenuItem("Chicken Wrap Meal", "Combo", 150.0);
        restaurant1.addMenuItem("Spicy Chicken Wings", "Appetizer", 120.0);
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant("Heart Attack", "Giza, Dokki", "Fried Chicken");
        restaurant2.addMenuItem("Cheesy Fried Chicken", "Main Meal", 180.0);
        restaurant2.addMenuItem("Family Meal (20 pcs)", "Combo", 450.0);
        restaurant2.addMenuItem("Chicken Wrap with Fries", "Combo", 160.0);
        restaurant2.addMenuItem("Crispy Tenders", "Appetizer", 140.0);
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant("Pronto Pizza", "Cairo, Heliopolis", "Pizza");
        restaurant3.addMenuItem("Margherita Pizza", "Main Meal", 100.0);
        restaurant3.addMenuItem("Pepperoni Pizza", "Main Meal", 150.0);
        restaurant3.addMenuItem("BBQ Chicken Pizza", "Main Meal", 160.0);
        restaurant3.addMenuItem("Vegetarian Pizza", "Main Meal", 130.0);
        restaurants.add(restaurant3);

        Restaurant restaurant4 = new Restaurant("Pizza Station", "Giza, Mohandessin", "Pizza");
        restaurant4.addMenuItem("Four-Cheese Pizza", "Main Meal", 180.0);
        restaurant4.addMenuItem("Seafood Pizza", "Main Meal", 200.0);
        restaurant4.addMenuItem("Truffle Mushroom Pizza", "Main Meal", 220.0);
        restaurant4.addMenuItem("Classic Pepperoni", "Main Meal", 160.0);
        restaurants.add(restaurant4);

        Restaurant restaurant5 = new Restaurant("Skyroof", "Alexandria, Corniche", "Sea Food");
        restaurant5.addMenuItem("Mixed Seafood Platter", "Main Meal", 450.0);
        restaurant5.addMenuItem("Shrimp Pasta", "Main Meal", 250.0);
        restaurant5.addMenuItem("Grilled Calamari", "Appetizer", 180.0);
        restaurant5.addMenuItem("Lemonade", "Drink", 25.0);
        restaurants.add(restaurant5);

        Restaurant restaurant6 = new Restaurant("Mandarine Koueider", "Cairo, Zamalek", "Dessert");
        restaurant6.addMenuItem("Kunafa with Cream", "Dessert", 90.0);
        restaurant6.addMenuItem("Chocolate Truffles", "Dessert", 150.0);
        restaurant6.addMenuItem("Ice Cream (per scoop)", "Dessert", 25.0);
        restaurants.add(restaurant6);

        Restaurant restaurant7 = new Restaurant("Cilantro Café", "Cairo, New Cairo", "Café");
        restaurant7.addMenuItem("Caramel Latte", "Beverage", 55.0);
        restaurant7.addMenuItem("Turkey & Cheese Sandwich", "Snack", 75.0);
        restaurant7.addMenuItem("Classic Cappuccino", "Beverage", 50.0);
        restaurant7.addMenuItem("Chocolate Croissant", "Pastry", 45.0);
        restaurants.add(restaurant7);

        return restaurants;
    }


    public void AddResturant() {
        Restaurant res = new Restaurant("", "", "");
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Restaurant information you want to add :");
        System.out.println("Enter Restaurant name :");
        res.name = input.nextLine();
        System.out.println("Enter Restaurant location  :");
        res.location = input.nextLine();
        System.out.println("Enter Restaurant category  :");
        res.category = input.nextLine();
        restaurants.add(res);
        System.out.println("Restaurant added Successfully");
    }

    public void Remove_Resturant() {
        System.out.print("Enter Restaurant name you want to remove: ");
        Scanner P = new Scanner(System.in);
        String res = P.nextLine().trim();
        boolean found = false;
        for (int i = 0; i < restaurants.size(); i++) {
            if (res.equals((restaurants.get(i).name))) {
                found = true;
                restaurants.remove(restaurants.get(i));
                System.out.println("Restaurant removed Successfully");
                break;
            }
        }
        if (!found)
            System.out.println("Restaurant you tried to remove not found");
    }

    public void Update_restaurants() {
        Scanner P = new Scanner(System.in);
        System.out.println("Enter Restaurant name you want to update:");
        String restaurantName = P.nextLine().trim();

        boolean found = false;
        for (int i = 0; i < restaurants.size(); i++) {
            if (restaurants.get(i).name.equals(restaurantName)) {
                found = true;

                System.out.println("Enter new Restaurant name: ");
                String newName = P.nextLine().trim();

                System.out.println("Enter new Restaurant location: ");
                String newLocation = P.nextLine().trim();

                System.out.println("Enter new Restaurant category: ");
                String newCategory = P.nextLine().trim();

                restaurants.set(i, new Restaurant(newName, newLocation, newCategory));

                System.out.println("Restaurant updated successfully.");
                break;
            }
        }

        if (!found) {
            System.out.println("Restaurant you tried to update was not found.");
        }
    }

    public void displayRestaurants() {
        if (restaurants == null || restaurants.isEmpty()) {
            System.out.println("No restaurants available.");
        } else {
            for (Restaurant res : restaurants) {
                System.out.println("=============================================");
                System.out.println("Restaurant Name: " + res.name);
                System.out.println("Restaurant Location: " + res.location);
                System.out.println("Restaurant Category: " + res.category);
            }
        }
    }

    public void saveRestaurantsToFile() {
        File file = new File("Test.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Name\t\t\tAddress\t\t\tCategory\n");
            for (Restaurant restaurant : restaurants) {
                writer.write(restaurant.name + "\t" + restaurant.location + "\t" + restaurant.category + "\n");
            }
            System.out.println("Restaurants saved to Test.txt successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the restaurants.");
            e.printStackTrace();
        }
    }

    public void clearDataFromFile() {
        Scanner input = new Scanner(System.in);
        System.out.println("Are you sure you want to clear all data from the file? This action cannot be undone. (Y/N)");
        String confirmation = input.nextLine().trim();

        if (confirmation.equalsIgnoreCase("Y")) {
            File file = new File("Test.txt");
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write("");
                System.out.println("All data has been cleared from the file.");
            } catch (IOException e) {
                System.out.println("An error occurred while clearing the data.");
                e.printStackTrace();
            }
        } else {
            System.out.println("Operation cancelled. Data has not been cleared.");
        }
    }


}