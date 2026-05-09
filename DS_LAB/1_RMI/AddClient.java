import java.rmi.Naming;

/**
 * Client program.
 * Looks up the remote object and calls the remote add method.
 */
public class AddClient {

    /**
     * Main entry point for the client.
     *
     * Expected arguments:
     * args[0] -> server host/IP
     * args[1] -> first number
     * args[2] -> second number
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        if (args.length != 3) {
            System.out.println("Usage: java AddClient <server-ip> <num1> <num2>");
            return;
        }

        try {
            String serverHost = args[0];
            double a = Double.parseDouble(args[1]);
            double b = Double.parseDouble(args[2]);

            // Build the RMI URL
            String url = "rmi://" + serverHost + "/AddServer";

            // Look up the remote object
            AddServerIntf stub = (AddServerIntf) Naming.lookup(url);

            // Make the remote call
            double result = stub.add(a, b);

            System.out.println("Server: " + serverHost);
            System.out.println("First number: " + a);
            System.out.println("Second number: " + b);
            System.out.println("Sum: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Error: numbers must be valid numeric values.");
        } catch (Exception e) {
            System.out.println("Client error: " + e);
            e.printStackTrace();
        }
    }
}