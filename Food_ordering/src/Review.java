
import java.io.*;
import java.util.ArrayList;

public class Review implements Serializable {
    private String reviewerName;
    private int rating; // Rating out of 5
    private String comment;
    private String restaurantName;


    public Review(String reviewerName, int rating, String comment, String restaurantName) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5.");
        }
        this.reviewerName = reviewerName;
        this.rating = rating;
        this.comment = comment;
        this.restaurantName = restaurantName;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public int getRating() {
        return rating;
    }

    public String getComment() {
        return comment;
    }

    public String getRestaurantName() {
        return restaurantName;
    }


    @Override
    public String toString() {
        return "Reviewer: " + reviewerName + "\n" +
                "Rating: " + rating + "/5\n" +
                "Comment: " + comment + "\n" +
                "Restaurant: " + restaurantName;
    }


    public static void saveReviewsToFile(ArrayList<Review> reviews, String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(reviews);
            System.out.println("Reviews saved to file: " + filename);
        } catch (IOException e) {
            System.err.println("Error saving reviews: " + e.getMessage());
        }
    }


    public static ArrayList<Review> loadReviewsFromFile(String filename) {
        ArrayList<Review> reviews = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            reviews = (ArrayList<Review>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("File not found, starting with an empty review list.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading reviews: " + e.getMessage());
        }
        return reviews;
    }
}

class RestaurantWithReviews extends Restaurant {
    private ArrayList<Review> reviews;

    public RestaurantWithReviews(String name, String location, String category) {
        super(name, location, category);
        this.reviews = new ArrayList<>();
    }

    // add a review and save to file
    public void addReview( String reviewerName, int rating, String comment, String filename) {
        Review review = new Review(reviewerName, rating, comment, this.name);
        this.reviews.add(review);
        saveReviewsToFile(filename); // Save reviews to file
    }

    // display all reviews
    public void displayReviews() {
        System.out.println("Reviews for " + this.name + ":");
        if (reviews.isEmpty()) {
            System.out.println("No reviews yet.");
        } else {
            for (Review review : reviews) {
                System.out.println(review);
                System.out.println("*=======================*");
            }
        }
    }

    // write all reviews to a file
    public void saveReviewsToFile(String filename) {
        Review.saveReviewsToFile(this.reviews, filename);
    }

    // read reviews for this restaurant from a file
    public void loadReviewsFromFile(String filename) {
        ArrayList<Review> loadedReviews = Review.loadReviewsFromFile(filename);
        this.reviews.clear(); // Clear existing reviews
        for (Review review : loadedReviews) {
            if (review.getRestaurantName().equals(this.name)) {
                this.reviews.add(review);
            }
        }
    }
}
