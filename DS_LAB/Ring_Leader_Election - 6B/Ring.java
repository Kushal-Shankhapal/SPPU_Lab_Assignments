import java.util.*;

public class Ring {

    static int[] id;
    static boolean[] alive;
    static int n;
    static int coordinator;

    // Display ring with status indicators
    static void showRing() {
        System.out.println("\nCurrent Ring Status:");
        for (int i = 0; i < n; i++) {
            String status = alive[i] ? "[ALIVE]" : "[DEAD] ";
            String isCoord = (id[i] == coordinator) ? " <-- Coordinator" : "";
            System.out.println("  Index " + i + " | Process " + id[i] + " | " + status + isCoord);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        n = sc.nextInt();
        id = new int[n];
        alive = new boolean[n];

        // Input process IDs
        for (int i = 0; i < n; i++) {
            System.out.print("Enter ID for process " + i + ": ");
            id[i] = sc.nextInt();
            alive[i] = true;
        }

        // Sort by ID (bubble sort)
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - 1; j++)
                if (id[j] > id[j + 1]) {
                    int temp = id[j];
                    id[j] = id[j + 1];
                    id[j + 1] = temp;
                }

        // Highest ID is coordinator by default
        coordinator = id[n - 1];
        System.out.println("\nInitial Coordinator: Process " + coordinator);
        showRing();

        int choice;
        while (true) {
            System.out.println("\n1. Start Election");
            System.out.println("2. Crash a process");
            System.out.println("3. Quit");
            System.out.print("Choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter index of process starting the election: ");
                    int init = sc.nextInt();

                    if (init < 0 || init >= n || !alive[init]) {
                        System.out.println("Invalid or crashed process.");
                        break;
                    }

                    // Pass election message around the ring
                    int[] electionMsg = new int[n];
                    int count = 0;
                    int current = (init + 1) % n;

                    System.out.println("\n--- Election Message Passing ---");
                    while (current != init) {
                        if (alive[current]) {
                            System.out.println("Process " + id[init] +
                                    " -> Process " + id[current] +
                                    " (passing election message)");
                            electionMsg[count++] = id[current];
                        } else {
                            System.out.println("Process " + id[current] +
                                    " is DEAD - skipped");
                        }
                        current = (current + 1) % n;
                    }

                    // Initiator adds itself
                    electionMsg[count++] = id[init];
                    System.out.println("Message returns to initiator Process " + id[init]);

                    // Highest ID in message wins
                    int max = -1;
                    for (int i = 0; i < count; i++)
                        if (electionMsg[i] > max) max = electionMsg[i];

                    coordinator = max;
                    System.out.println("\n*** New Coordinator: Process " + coordinator +
                            " (highest ID in ring wins - Ring Rule) ***");

                    showRing();
                    break;

                case 2:
                    showRing();
                    System.out.print("Enter index of process to crash: ");
                    int cp = sc.nextInt();

                    if (cp < 0 || cp >= n) {
                        System.out.println("Invalid index.");
                    } else if (!alive[cp]) {
                        System.out.println("Process " + id[cp] + " is already crashed.");
                    } else {
                        alive[cp] = false;
                        System.out.println("Process " + id[cp] + " has been crashed.");
                        if (id[cp] == coordinator)
                            System.out.println("Coordinator crashed! Start an election.");
                        showRing();
                    }
                    break;

                case 3:
                    System.out.println("Program terminated.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}