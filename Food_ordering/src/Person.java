import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Person {
    private file handler;
    private String email;
    private String password;
    Scanner input=new Scanner(System.in);
    public Person(){
        handler=new file("output.txt");
    }
    public boolean login() throws IOException {
        System.out.println("=== Login ===");
        System.out.print("Enter Email: ");

        email= input.nextLine();
        System.out.print("Enter Password: ");
        password = input.nextLine();
        List<String> data = file.read();
        for (String record : data) {
            String[] fields = record.split("/");
            try {
                if (fields[2].equals(email) && fields[3].equals(password)) {
                    System.out.println("Login successful! Welcome, " + fields[0] + " " + fields[1]);
                    return true;
                }
            }
            catch(IndexOutOfBoundsException e){
                e.getMessage();
            }
        }
        return false;
    }
}