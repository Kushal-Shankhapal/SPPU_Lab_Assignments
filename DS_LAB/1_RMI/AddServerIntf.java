import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Remote interface for the RMI server.
 * Any method declared here can be called remotely by an RMI client.
 */
public interface AddServerIntf extends Remote {

    /**
     * Adds two numbers on the server side and returns the result.
     *
     * @param a first number
     * @param b second number
     * @return sum of a and b
     * @throws RemoteException if a network or RMI communication error occurs
     */
    double add(double a, double b) throws RemoteException;
}