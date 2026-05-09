import ReverseModule.ReversePOA;

/**
 * Server-side implementation of the CORBA interface.
 * This class contains the actual business logic.
 */
class ReverseImpl extends ReversePOA {

    /**
     * Constructor
     */
    ReverseImpl() {
        super();
        System.out.println("Reverse Object Created");
    }

    /**
     * Reverses the input string
     *
     * @param inputString String sent by the client
     * @return reversed string
     */
    public String reverse_string(String inputString) {

        System.out.println("Received string: " + inputString);

        // Reverse string using StringBuilder
        String reversed =
                new StringBuilder(inputString)
                        .reverse()
                        .toString();

        return reversed;
    }
}