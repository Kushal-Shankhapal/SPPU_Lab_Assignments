import java.rmi.Naming;
import java.util.Scanner;

/**
 * Client program.
 * Takes user input and sends it to the RMI server.
 */
public class AddClient {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // Ask for server IP
            System.out.print("Enter server IP (localhost or 127.0.0.1): ");
            String serverHost = sc.nextLine();

            // Ask user for numbers
            System.out.print("Enter first number: ");
            double a = sc.nextDouble();

            System.out.print("Enter second number: ");
            double b = sc.nextDouble();

            // Build RMI URL
            String url = "rmi://" + serverHost + "/AddServer";

            // Get remote object reference
            AddServerIntf stub =
                    (AddServerIntf) Naming.lookup(url);

            // Call remote method
            double result = stub.add(a, b);

            // Display result
            System.out.println("\n===== RESULT =====");
            System.out.println("First number : " + a);
            System.out.println("Second number: " + b);
            System.out.println("Sum          : " + result);

        } catch (Exception e) {
            System.out.println("Client Error: " + e);
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}
