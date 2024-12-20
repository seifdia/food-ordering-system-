import java.io.*;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Order {
    private static Order order;
    Scanner input=new Scanner(System.in);
    int orderId;
    private LocalDate orderDate;
    private String orderLocation;
    private String orderState;
    private Map<Food, Integer> items;
    private double totalPrice;
    file ordersFile = new file("orders.txt");

    // Method to return current date
    public static LocalDate getCurrentDate() {
        return LocalDate.now(); // Returns the current date
    }
    public Order(String orderLocation) {
        try {
            orderId = loadLastOrderId();
            orderId++; // Increment the order ID
        } catch (IOException e) {
            System.out.println("Error reading or writing order ID: " + e.getMessage());
            orderId = 1;
        }
        this.orderDate = getCurrentDate();
        this.orderLocation = orderLocation;
        this.orderState = "Pending";
        this.items = new LinkedHashMap<>();
        this.totalPrice = 0.0;
    }
    private int loadLastOrderId() throws IOException {
        List<String> orders = ordersFile.read();
        if (orders.isEmpty()) {
            System.out.println("No orders found, returning 0.");
            return 0; // No orders yet
        }
        orders = orders.stream()
                .map(String::trim)
                .filter(order -> !order.isEmpty())
                .collect(Collectors.toList());
        StringBuilder lastOrderBuilder = new StringBuilder();
        for (int i = orders.size() - 1; i >= 0; i--) {
            String line = orders.get(i);
            if (line.startsWith("Order ID:")) {
                lastOrderBuilder.insert(0, line + "\n"); // Add the line for Order ID
                break;
            }
            lastOrderBuilder.insert(0, line + "\n"); // Add each line to the builder
        }
        String lastOrder = lastOrderBuilder.toString().trim(); // Get the full last order as a string
        String[] orderDetails = lastOrder.split("\n");
        for (String line : orderDetails) {
            if (line.startsWith("Order ID: ")) {
                return Integer.parseInt(line.substring(9).trim());  // Return the order ID as an integer
            }
        }
        return 0; // If no valid order ID is found, return 0
    }

    public void addItem(Food food, int quantity) {
        items.put(food, items.getOrDefault(food, 0) + quantity);
        totalPrice += food.getPrice() * quantity;
    }
    public void removeItem(Food food, int quantity) {
        // Print debugging information for clarity
        System.out.println("Trying to remove: " + food);
        System.out.println("Current items in the order: " + items);

        // Iterate over the items in the order to find the matching food item
        boolean found = false; // Flag to track if the item is found
        for (Map.Entry<Food, Integer> entry : items.entrySet()) {
            Food currentFood = entry.getKey();

            // Check if food names match (or other properties like type if necessary)
            if (currentFood.getName().equals(food.getName())) {
                found = true; // Item found, proceed with removal
                int currentQuantity = entry.getValue();
                int newQuantity = Math.max(0, currentQuantity - quantity);

                // If quantity reaches zero, remove the item from the order list
                if (newQuantity == 0) {
                    items.remove(currentFood);
                    System.out.println(food.getName() + " has been removed from your order.");
                } else {
                    items.put(currentFood, newQuantity);
                    System.out.println("Updated quantity for " + food.getName() + ": " + newQuantity);
                }

                // Update the total price accordingly
                totalPrice -= currentFood.getPrice() * quantity;
                break;
            }
        }

        if (!found) {
            System.out.println("Item not found in the order.");
        }
    }


    public String getOrderLocation() {
        return orderLocation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }
    public void changeState(String newState) {
        this.orderState = newState;

    }
    public void Createorder(int restaurantChoice,int control) {
        Restaurant selectedRestaurant = Restaurant.loadRestaurantsFromFile().get(restaurantChoice - 1);
        selectedRestaurant.displayMenu();
        // Check if order is already created

        if (order == null) {
            System.out.println(order);
            order = new Order(selectedRestaurant.location);
        }
        // Create Order
        boolean ordering = true;
        if (control == 1) {
            while (ordering) {
                System.out.print("Enter the number of the menu item to add (or 0 to finish): ");
                int itemChoice = input.nextInt();

                if (itemChoice == 0) {
                    ordering = false;

                } else if (itemChoice > 0 && itemChoice <= selectedRestaurant.menu.size()) {
                    System.out.print("Enter the quantity: ");
                    int quantity = input.nextInt();

                    if (quantity > 0) {
                        Food selectedFood = selectedRestaurant.menu.get(itemChoice - 1);
                        order.addItem(selectedFood, quantity);
                        System.out.println(quantity + " x " + selectedFood.name + " added to your order.");
                    } else {
                        System.out.println("Invalid quantity! Please enter a positive number.");
                    }
                } else {
                    System.out.println("Invalid item choice! Please select a valid menu item.");
                }
            }
            System.out.println("=========================");
            System.out.println("Order Summary:");
            System.out.println(order);
        } else if (control == 2) {
            while (ordering) {
                System.out.print("Enter the number of the menu item to add (or 0 to finish): ");
                int itemChoice = input.nextInt();

                if (itemChoice == 0) {
                    ordering = false;

                } else if (itemChoice > 0 && itemChoice <= selectedRestaurant.menu.size()) {
                    System.out.print("Enter the quantity: ");
                    int quantity = input.nextInt();

                    if (quantity > 0) {
                        Food selectedFood = selectedRestaurant.menu.get(itemChoice - 1);
                        order.addItem(selectedFood, quantity);
                        System.out.println(quantity + " x " + selectedFood.name + " added to your order.");
                    } else {
                        System.out.println("Invalid quantity! Please enter a positive number.");
                    }
                } else {
                    System.out.println("Invalid item choice! Please select a valid menu item.");
                }
            }
            System.out.println("=========================");
            System.out.println("Order Summary:");
            System.out.println(order);
        } else if (control == 3) {
            while (ordering) {
                System.out.print("Enter the number of the menu item to add (or 0 to finish): ");
                int itemChoice = input.nextInt();

                if (itemChoice == 0) {
                    ordering = false;

                } else if (itemChoice > 0 && itemChoice <= selectedRestaurant.menu.size()) {
                    System.out.print("Enter the quantity: ");
                    int quantity = input.nextInt();

                    if (quantity > 0) {
                        Food selectedFood = selectedRestaurant.menu.get(itemChoice - 1);
                        order.removeItem(selectedFood, quantity);
                        System.out.println(quantity + " x " + selectedFood.name + " Removed from your order.");
                    } else {
                        System.out.println("Invalid quantity! Please enter a positive number.");
                    }
                } else {
                    System.out.println("Invalid item choice! Please select a valid menu item.");
                }
            }
            System.out.println("=========================");
            System.out.println("Order Summary:");
            System.out.println(order);
        }
        else if(control == 4){
            order = null;
            System.out.println("Order has been canceled");
        }
    }
    public void update(int restaurantChoice){
        System.out.println("2- Add new item");
        System.out.println("3- delete item");
        int control=input.nextInt();
        if(control==2){
            Createorder(restaurantChoice,2);
        }
        else if (control==3){
            Restaurant selectedRestaurant = Restaurant.loadRestaurantsFromFile().get(restaurantChoice - 1);
            selectedRestaurant.displayMenu();
            Createorder(restaurantChoice,3);
        }
    }

    public String toString() {
        StringBuilder orderDetails= new StringBuilder();
        orderDetails.append("Order ID: ").append(orderId)
                .append("\nDate: ").append(orderDate)
                .append("\nLocation: ").append(orderLocation)
                .append("\nState: ").append(orderState)
                .append("\nTotal Price: ").append(totalPrice).append(" EGP")
                .append("\nItems:\n");

        for (Map.Entry<Food, Integer> entry : items.entrySet()) {
            Food food = entry.getKey();
            int quantity = entry.getValue();
            orderDetails.append("- ").append(food.name)
                    .append(" (").append(food.type).append(") - ")
                    .append(food.price).append(" EGP x")
                    .append(quantity);
        }
        saveToFile(orderDetails.toString());
        return orderDetails.toString();
    }
    public void saveToFile(String order) {
        try {
            // Ensure file exists
            ensureFileExists();
            BufferedWriter writer = new BufferedWriter(new FileWriter("orders.txt", true)); // 'true' to append
            writer.write(order);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving order to file: " + e.getMessage());
        }
    }


    private void ensureFileExists() throws IOException {
        java.io.File file = new java.io.File("orders.txt");
        if (!file.exists()) {
            file.createNewFile(); // Create the file if it doesn't exist
        }
    }



    // Load and display all orders from the file
    public static void loadOrdersFromFile() {
        try {
            List<String> orders = file.read();
            for (String order : orders) {
                System.out.println(order);
                System.out.println("====================================");
            }
        } catch (IOException e) {
            System.out.println("Error loading orders from file: " + e.getMessage());
        }
    }
}
