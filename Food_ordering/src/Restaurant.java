import java.io.Serializable;
import java.util.ArrayList;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant implements Serializable {
    String name;
    String location;
    String category;
    ArrayList<Food> menu;
    public Restaurant(String name, String location, String category)
    {
        this.name = name;
        this.location = location;
        this.category = category;
        this.menu = new ArrayList<>();
    }

    void addMenuItem(String itemName, String itemType , double itemPrice)
    {
        menu.add(new Food(itemName, itemType, itemPrice));
    }

    void displayMenu()
    {
        System.out.println("Menu for " + name + ":");
        for(Food item : menu)
        {
            System.out.println(item.name + "(" + item.type + ")" + ": " + item.price + " EGP");
        }
    }

    // Restaurants data
    private static  ArrayList<Restaurant> initializeRestaurants()
    {

        ArrayList<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant1 = new Restaurant("Kansas", "Cairo, Nasr City", "Fried Chicken");
        restaurant1.addMenuItem("Fried Chicken Bucket (12 pcs)", "Main Meal", 280.0);
        restaurant1.addMenuItem("Crispy Chicken Sandwich", "Main Meal", 100.0);
        restaurant1.addMenuItem("Chicken Wrap Meal", "Combo", 150.0);
        restaurant1.addMenuItem("Spicy Chicken Wings", "Appetizer", 120.0);
        restaurants.add(restaurant1);

        Restaurant restaurant2 = new Restaurant("Heart Attack","Giza, Dokki" ,"Fried Chicken");
        restaurant2.addMenuItem("Cheesy Fried Chicken", "Main Meal", 180.0);
        restaurant2.addMenuItem("Family Meal (20 pcs)", "Combo", 450.0);
        restaurant2.addMenuItem("Chicken Wrap with Fries", "Combo", 160.0);
        restaurant2.addMenuItem("Crispy Tenders", "Appetizer", 140.0);
        restaurants.add(restaurant2);

        Restaurant restaurant3 = new Restaurant("Pronto Pizza","Cairo, Heliopolis" ,"Pizza");
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

        Restaurant restaurant8 = new Restaurant("Wild Burger", "Cairo, Nasr City", "Burgers");
        restaurant8.addMenuItem("Double Cheeseburger", "Burger", 120.0);
        restaurant8.addMenuItem("Spicy Chicken Strips", "Fried Chicken", 90.0);
        restaurant8.addMenuItem("Cheesy Fries", "Side Dish", 50.0);
        restaurant8.addMenuItem("Nutella Milkshake", "Dessert", 60.0);
        restaurants.add(restaurant8);

        Restaurant restaurant9 = new Restaurant("Latino Café & Restaurant", "Alexandria, Corniche", "Café");
        restaurant9.addMenuItem("Margherita Pizza", "Pizza", 110.0);
        restaurant9.addMenuItem("Grilled Shrimp Platter", "Seafood", 250.0);
        restaurant9.addMenuItem("Iced Mocha", "Beverage", 60.0);
        restaurant9.addMenuItem("Cheesecake", "Dessert", 80.0);
        restaurants.add(restaurant9);

        Restaurant restaurant10 = new Restaurant("Ovio", "Cairo, New Cairo", "Dessert");
        restaurant10.addMenuItem("Lava Cake with Ice Cream", "Dessert", 100.0);
        restaurant10.addMenuItem("Iced Cappuccino", "Beverage", 60.0);
        restaurants.add(restaurant10);


        return restaurants;
    }

    private static  ArrayList<String> categoriesList()
    {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Pizza");
        categories.add("Fried Chicken");
        categories.add("Sea Food");
        categories.add("Dessert");
        categories.add("Burgers");
        categories.add("Café");

        return categories;
    }

    private static void saveRestaurantsToFile(ArrayList<Restaurant> restaurants)
    {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Restaurants.txt"))) {
            oos.writeObject(restaurants);
            System.out.println("Restaurants saved to file successfully.");
        }
        catch (IOException e) {
            System.out.println("Error saving restaurants to file: " + e.getMessage());
        }
    }

    public static ArrayList<Restaurant> loadRestaurantsFromFile()
    {

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Restaurants.txt"))) {
            return (ArrayList<Restaurant>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading restaurants from file: " + e.getMessage());
            return null;
        }
    }

    public static int afterLog_in(int verify_log_in) {
        Scanner input = new Scanner(System.in);
        ArrayList<Restaurant> restaurants = initializeRestaurants();
        ArrayList<String> categories = categoriesList();
        saveRestaurantsToFile(restaurants);
        ArrayList<Restaurant> loadedRestaurants = loadRestaurantsFromFile();
        if (loadedRestaurants == null || loadedRestaurants.isEmpty()) {
            System.out.println("No restaurants found in the file!");
            return 0;
        }

        System.out.println("Available Categories: ");
        for (int i = 0; i < categories.size(); i++) {
            System.out.println((i + 1) + ". " + categories.get(i));
        }

        System.out.print("Select a Category by Number: ");
        int categoryChoice = input.nextInt();

        if (categoryChoice > 0 && categoryChoice <= categories.size()) {
            String selectedCategory = categories.get(categoryChoice - 1);
            // Filter and display restaurants with the selected category
            System.out.println("Restaurants in the '" + selectedCategory + "' category:");
            ArrayList<Restaurant> filteredRestaurants = new ArrayList<>();
            for (Restaurant restaurant : loadedRestaurants) {
                if (restaurant.category.equals(selectedCategory)) {
                    filteredRestaurants.add(restaurant);
                }
            }

            if (filteredRestaurants.isEmpty()) {
                System.out.println("No restaurants found in this category.");
                return 0;
            }

            // Display filtered restaurants
            for (int i = 0; i < filteredRestaurants.size(); i++) {
                System.out.println((i + 1) + ". " + filteredRestaurants.get(i).name);
            }

            System.out.print("Select a Restaurant by Number: ");
            int restaurantChoice = input.nextInt();

            if (restaurantChoice > 0 && restaurantChoice <= filteredRestaurants.size()) {
                Restaurant selectedRestaurant = filteredRestaurants.get(restaurantChoice - 1);
                System.out.println("Menu for " + selectedRestaurant.name + ":");
                selectedRestaurant.displayMenu();

                //Pizza
                //Pronto Pizza
                if (selectedCategory.toString() == "Pizza" && restaurantChoice == 1)
                    return 3;
                    //Pizza Station
                else if (selectedCategory.toString() == "Pizza" && restaurantChoice == 2)
                    return 4;

                //Fried Chicken
                //Kansas
                if (selectedCategory.toString() == "Fried Chicken" && restaurantChoice == 1)
                    return 1;
                    //Heart Attack
                else if (selectedCategory.toString() == "Fried Chicken" && restaurantChoice == 2)
                    return 2;

                //Sea Food
                //Skyroof
                if (selectedCategory.toString() == "Sea Food" && restaurantChoice == 1)
                    return 5;

                //Dessert
                //Mandarine Koueider
                if (selectedCategory.toString() == "Dessert" && restaurantChoice == 1)
                    return 6;
                    //Ovio
                else if (selectedCategory.toString() == "Dessert" && restaurantChoice == 2)
                    return 10;

                //Burgers
                //Wild Burger
                if (selectedCategory.toString() == "Burgers" && restaurantChoice == 1)
                    return 8;

                //Café
                //Cilantro Café
                if (selectedCategory.toString() == "Café" && restaurantChoice == 1)
                    return 7;
                    //Latino Café & Restaurant
                else if (selectedCategory.toString() == "Café" && restaurantChoice == 2)
                    return 9;

            } else {
                System.out.println("Invalid restaurant choice.");
            }
        } else {
            System.out.println("Invalid category choice.");
        }
        return 0;
    }
}