import java.util.*;

public class Berkeley {

    // Convert h, m, s to total seconds
    static int toSeconds(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }

    // Convert total seconds back to readable h:m:s string
    static String toHMS(int totalSec) {
        totalSec = ((totalSec % 86400) + 86400) % 86400; // handle negatives
        return (totalSec / 3600) + ":" + ((totalSec % 3600) / 60) + ":" + (totalSec % 60);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Step 1: Master reads its own system time
        Calendar cal = Calendar.getInstance();
        int masterTime = toSeconds(cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));
        System.out.println("Master time: " + toHMS(masterTime));

        // Input node times
        System.out.print("Enter number of nodes: ");
        int n = sc.nextInt();
        int[] nodeTime = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.println("Node " + (i + 1) + " time (h m s): ");
            nodeTime[i] = toSeconds(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }

        // Step 2: Master sends its time, nodes reply with their difference
        System.out.println("\nMaster sent its time to all nodes: " + toHMS(masterTime));
        int[] diff = new int[n];
        int sumOfDiffs = 0;
        for (int i = 0; i < n; i++) {
            diff[i] = nodeTime[i] - masterTime; // positive = node is ahead, negative = behind
            sumOfDiffs += diff[i];
            System.out.println("Node " + (i + 1) + " difference: " + diff[i] + "s");
        }

        // Step 3: Compute average correction (master includes itself with diff = 0)
        int avg = sumOfDiffs / (n + 1);
        System.out.println("Average correction: " + avg + "s");

        // Apply correction — everyone converges to masterTime + avg
        System.out.println("\n--- Synchronized Clocks ---");
        System.out.println("Master --> " + toHMS(masterTime + avg));
        for (int i = 0; i < n; i++)
            System.out.println("Node " + (i + 1) + " --> " + toHMS(masterTime + avg));

        sc.close();
    }
}