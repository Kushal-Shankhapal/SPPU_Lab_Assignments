import ReverseModule.Reverse;
import ReverseModule.ReverseHelper;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;

/**
 * CORBA Server Program
 */
public class ReverseServer {

    public static void main(String[] args) {

        try {

            // Initialize ORB
            ORB orb = ORB.init(args, null);

            // Get reference to Root POA
            POA rootPOA = POAHelper.narrow(
                    orb.resolve_initial_references("RootPOA")
            );

            // Activate POA Manager
            rootPOA.the_POAManager().activate();

            // Create servant object
            ReverseImpl reverseObj = new ReverseImpl();

            // Convert servant object to CORBA reference
            org.omg.CORBA.Object ref =
                    rootPOA.servant_to_reference(reverseObj);

            Reverse href =
                    ReverseHelper.narrow(ref);

            // Get naming context reference
            org.omg.CORBA.Object objRef =
                    orb.resolve_initial_references("NameService");

            NamingContextExt ncRef =
                    NamingContextExtHelper.narrow(objRef);

            // Register object with name "Reverse"
            String name = "Reverse";

            NameComponent path[] =
                    ncRef.to_name(name);

            ncRef.rebind(path, href);

            System.out.println("CORBA Server is ready...");
            System.out.println("Waiting for client requests...");

            // Keep server running
            orb.run();

        } catch (Exception e) {
            System.out.println("Server Error: " + e);
            e.printStackTrace();
        }
    }
}