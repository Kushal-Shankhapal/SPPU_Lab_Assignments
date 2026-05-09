import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Concrete implementation of the remote interface.
 * This class is exported as a remote object.
 */
public class AddServerImpl extends UnicastRemoteObject implements AddServerIntf {

    /**
     * Constructor required by RMI.
     * It exports the object so that remote clients can call it.
     *
     * @throws RemoteException if export fails
     */
    public AddServerImpl() throws RemoteException {
        super();
    }

    /**
     * Adds two numbers and returns the result.
     *
     * @param a first number
     * @param b second number
     * @return sum of the two numbers
     * @throws RemoteException required by the remote method contract
     */
    @Override
    public double add(double a, double b) throws RemoteException {
        System.out.println("Received request: add(" + a + ", " + b + ")");
        return a + b;
    }
}