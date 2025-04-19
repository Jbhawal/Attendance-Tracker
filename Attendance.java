import java.util.*;

public class Attendance {
    static Scanner sc = new Scanner(System.in);

    // Constants for subjects
    static final int CO = 0, DAA = 1, DM = 2, OS = 3, NM = 4;
    static int[] myAttendance = new int[5];
    static int[] totalAttendance = new int[5];

    public static int getSubjectIndex(String subject) {
        switch (subject) {
            case "CO": return 0;
            case "DAA": return 1;
            case "DM": return 2;
            case "OS": return 3;
            case "NM": return 4;
            default: return -1; // Invalid subject
        }
    }
    

    public static void monday() {
        System.out.println("CO-1, Attend?(y/n): ");
        totalAttendance[CO]++;
        String COattend = sc.nextLine();
        if (COattend.equalsIgnoreCase("y")) {
            myAttendance[CO]++;
        }
    }

    public static void tuesday() {
        System.out.println("OS-1, Attend?(y/n): ");
        totalAttendance[OS]++;
        String OSattend = sc.nextLine();
        if (OSattend.equalsIgnoreCase("y")) {
            myAttendance[OS]++;
        }
        System.out.println("DM-2, Attend?(y/n): ");
        totalAttendance[DM] += 2;
        String DMattend = sc.nextLine();
        if (DMattend.equalsIgnoreCase("y")) {
            myAttendance[DM] += 2;
        }
    }

    public static void wednesday() {
        System.out.println("OS-2, Attend?(y/n): ");
        totalAttendance[OS] += 2;
        String OSattend = sc.nextLine();
        if (OSattend.equalsIgnoreCase("y")) {
            myAttendance[OS] += 2;
        }
        System.out.println("DAA-2, Attend?(y/n): ");
        totalAttendance[DAA] += 2;
        String DAAattend = sc.nextLine();
        if (DAAattend.equalsIgnoreCase("y")) {
            myAttendance[DAA] += 2;
        }
        System.out.println("CO-1, Attend?(y/n): ");
        totalAttendance[CO]++;
        String COattend = sc.nextLine();
        if (COattend.equalsIgnoreCase("y")) {
            myAttendance[CO]++;
        }
    }

    public static void thursday() {
        System.out.println("CO-2, Attend?(y/n): ");
        totalAttendance[CO] += 2;
        String COattend = sc.nextLine();
        if (COattend.equalsIgnoreCase("y")) {
            myAttendance[CO] += 2;
        }
        System.out.println("NM-2, Attend?(y/n): ");
        totalAttendance[NM] += 2;
        String NMattend = sc.nextLine();
        if (NMattend.equalsIgnoreCase("y")) {
            myAttendance[NM] += 2;
        }
        System.out.println("DM-2, Attend?(y/n): ");
        totalAttendance[DM] += 2;
        String DMattend = sc.nextLine();
        if (DMattend.equalsIgnoreCase("y")) {
            myAttendance[DM] += 2;
        }
    }

    public static void friday() {
        System.out.println("OS-1, Attend?(y/n): ");
        totalAttendance[OS]++;
        String OSattend = sc.nextLine();
        if (OSattend.equalsIgnoreCase("y")) {
            myAttendance[OS]++;
        }
        System.out.println("DAA-2, Attend?(y/n): ");
        totalAttendance[DAA] += 2;
        String DAAattend = sc.nextLine();
        if (DAAattend.equalsIgnoreCase("y")) {
            myAttendance[DAA] += 2;
        }
        System.out.println("NM-1, Attend?(y/n): ");
        totalAttendance[NM]++;
        String NMattend = sc.nextLine();
        if (NMattend.equalsIgnoreCase("y")) {
            myAttendance[NM]++;
        }
    }

    public static void extra() {
        Boolean extra = true;
        while(extra){
            System.out.println("Which subject? (CO, DAA, DM, OS, NM) or exit: ");
            String subject = sc.nextLine().toUpperCase();
            if (subject.equals("EXIT")) {
                break;
            }
            int subIndex=getSubjectIndex(subject);
            if (subIndex == -1) {
                System.out.println("Invalid subject!");
                continue;
            }
            System.out.println("How many? ");
            int totalCount = sc.nextInt();
            sc.nextLine(); // Consume the newline character
            totalAttendance[subIndex] += totalCount;
            System.out.println("Attend?(y/n): ");
            String attend = sc.nextLine();
            if (attend.equalsIgnoreCase("y")) {
                myAttendance[subIndex] += totalCount;
            }
        }
    }

    public static void calculate() {
        String[] subjects = {"CO", "DAA", "DM", "OS", "NM"};
        System.out.println("\n--- Attendance Summary ---");
        for (int i = 0; i < 5; i++) {
            if (totalAttendance[i] > 0) {
                int percentage = (myAttendance[i] * 100) / totalAttendance[i];
                System.out.println(subjects[i] + ": " + myAttendance[i] + "/" + totalAttendance[i] + " = " + percentage + "%");
            } else {
                System.out.println(subjects[i] + ": No classes yet.");
            }
        }
    }

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("mon, tues, wed, thurs, fri, extra, show, exit :");
            String choice = sc.nextLine();

            switch (choice.toLowerCase()) {
                case "mon":
                    monday();
                    break;
                case "tues":
                    tuesday();
                    break;
                case "wed":
                    wednesday();
                    break;
                case "thurs":
                    thursday();
                    break;
                case "fri":
                    friday();
                    break;
                case "extra":
                    extra();
                    break;
                case "show":
                    calculate();
                    break;
                case "exit":
                    System.out.println("Exiting...");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again!");
            }
        }
    }
}
