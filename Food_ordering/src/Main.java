import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        Person person = new Person();
        customer customerData = new customer();
        while (true) {
            System.out.println("1. Are you an Admin");
            System.out.println("2. Are you a Delivery");
            System.out.println("3. Are you a customer");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
//            scanner.nextLine();
            if (choice >= 1 && choice <= 3) {


                if (choice == 1) {
                    if (person.login() == true) {
                        System.out.println("welcome to Admin System");
                        //admin
                    } else {
                        continue;
                    }
                } else if (choice == 2) {
                    if (person.login() == true) {
                        System.out.println("welcome to Delivery System");
                        //delivery

                    } else {
                        continue;
                    }

                } else if (choice == 3) {//customer
                    while (true) {
                        if (person.login()) {
                            break;
                        } else {
                            System.out.println("Login failed! ");
                            System.out.println("1- Check your email or password ");
                            System.out.println("2- Are you AlReady have an account?Register ");
                            System.out.print("Choose an option: ");
                            int Login_choice = scanner.nextInt();
                            if (Login_choice == 1) {
                                continue;

                            } else {
                                customerData.Register();
                                break;
                            }
                        }
                    }
                    customerData.governorate();
                    //restaurant
                    int choice_Restaurant = Restaurant.afterLog_in(1);
                    String resName = Restaurant.loadRestaurantsFromFile().get(choice_Restaurant-1).name;



                    while (true) {
                        while (true) {

                            System.out.println("1 - Create another order");
                            System.out.println("2 - Update the order");
                            System.out.println("3 - Cancel this order and make another");
                            System.out.println("4 - Add a review");
                            System.out.println("5 - View reviews");
                            System.out.println("6 - Log out");
                            System.out.print("Choose from (1 - 6): ");
                            int orderChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume the newline character

                            Order order = new Order( customerData.address);

                            if (orderChoice == 1) {
                                order.Createorder(choice_Restaurant,1);
                            } else if (orderChoice == 2) {
                                order.update(choice_Restaurant);
                            } else if (orderChoice == 3) {
                                order.changeState("canceled");
                            } else if (orderChoice == 4 || orderChoice == 5) {
                                RestaurantWithReviews selectedRestaurant = new RestaurantWithReviews(resName, "Restaurant Location", "Restaurant Category");
                                selectedRestaurant.loadReviewsFromFile("reviews.dat");

                                // add a review
                                if (orderChoice == 4) {
                                    System.out.print("Enter your name: ");
                                    String reviewerName = scanner.nextLine();
                                    System.out.print("Enter rating (1-5): ");
                                    int rating = scanner.nextInt();
                                    scanner.nextLine();
                                    System.out.print("Enter your comment: ");
                                    String comment = scanner.nextLine();

                                    selectedRestaurant.addReview(reviewerName, rating, comment, "reviews.dat");
                                    System.out.println("Review added successfully!");
                                } else { // view reviews
                                    selectedRestaurant.displayReviews();
                                }


                            } else {
                                break;
                            }
                        }


                        System.out.println("====== Thank you ======");
                        break;

                    }

                }
            } else {
                System.out.println("Invalid choice ,please choose from (1-3)");
                continue;
            }
            break;
        }

    }

}

//  customerData.governorate();
//                    //restaurant
//                    int choice_Restaurant = Restaurant.afterLog_in(1);
//                    if(choice_Restaurant!=0) {
//                        Order order = new Order( customerData.address);
//                        System.out.println("========================================");
//                        System.out.println("1 -update item");//error
//                        System.out.println("2 -view order");
//                        System.out.println("3 -cancel order");
//                        System.out.println("3 - Exist");
//                        System.out.print("choose from (1 - 4) :");
//                        int Order_choice = scanner.nextInt();
//                        if (Order_choice == 1) {
//                            order.update(choice_Restaurant);
//                        } else if (Order_choice == 2) {
//                            System.out.println(order.toString());
//                        } else if (Order_choice == 3) {
//                            order.changeState("canceled");
//                        }
//                        else {
//                            break;
//                        }
//
//
//
//                    }
//                    else{
//                        System.out.println("Invalid restaurant choice");
//
//                    }
//
//
//
//
//
//                    System.out.println("====== Thank you ======");








