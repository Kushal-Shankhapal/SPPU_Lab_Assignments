import ReverseModule.Reverse;
import ReverseModule.ReverseHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import java.util.Scanner;

/**
 * CORBA Client Program
 */
public class ReverseClient {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        try {

            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get naming context
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            // Find registered object
            String name = "Reverse";

            Reverse reverseObj =
                    ReverseHelper.narrow(
                            ncRef.resolve_str(name)
                    );

            // User input
            System.out.print("Enter String: ");
            String input = sc.nextLine();

            // Remote call
            String reversed =
                    reverseObj.reverse_string(input);

            // Display result
            System.out.println("\n===== RESULT =====");
            System.out.println("Original String : " + input);
            System.out.println("Reversed String : " + reversed);

        } catch (Exception e) {
            System.out.println("Client Error: " + e);
            e.printStackTrace();
        } finally {
            sc.close();
        }
    }
}