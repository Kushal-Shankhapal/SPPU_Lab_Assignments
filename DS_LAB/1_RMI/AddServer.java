import java.rmi.Naming;

/**
 * Server program.
 * Creates the remote object and binds it to the RMI registry.
 */
public class AddServer {

    /**
     * Main entry point for the server.
     *
     * @param args command-line arguments, not used
     */
    public static void main(String[] args) {
        try {
            AddServerIntf obj = new AddServerImpl();

            // Bind the remote object with the name "AddServer"
            Naming.rebind("AddServer", obj);

            System.out.println("RMI Server is ready.");
            System.out.println("Bound object name: AddServer");
        } catch (Exception e) {
            System.out.println("Server error: " + e);
            e.printStackTrace();
        }
    }
}