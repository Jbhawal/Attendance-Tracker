import java.util.*;
import java.io.*;
import java.time.LocalDate;
import java.time.DayOfWeek;

public class Attendance {
    static final String[] subjects = {"CO", "DAA", "DM", "OS", "NM"};
    static Scanner sc = new Scanner(System.in);

    // Setting constants for subjects
    static final int CO = 0, DAA = 1, DM = 2, OS = 3, NM = 4;
    static int[] myAttendance = new int[5];
    static int[] totalAttendance = new int[5];

    public static void saveAttendanceToFile() {
        //for history records. so all the entries.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("records.txt",true))) {
            
            LocalDate currentDate = LocalDate.now();
            DayOfWeek currentDay = currentDate.getDayOfWeek();

            writer.write("Date- " + currentDate + " (" + currentDay + ")");  //YY-MM-DD
            writer.newLine();

            // System.out.print("Enter date in format yyyy-mm-dd (DAY): ");
            // String dateInput = sc.nextLine();
            // writer.write("Date: " + dateInput);
            // writer.newLine();

            for (int i = 0; i < 5; i++) {
                writer.write(subjects[i] + ": " + myAttendance[i] + "/" + totalAttendance[i] + "\n");
            }
            writer.write("\n");
            writer.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred while saving attendance data.");
            e.printStackTrace();
        }
        
        //for latest attendance.
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt"));
            
            for (int i = 0; i < 5; i++) {
                int percentage = totalAttendance[i] == 0 ? 0 : (myAttendance[i] * 100) / totalAttendance[i];
                writer.write(subjects[i] + ": " + myAttendance[i] + "/" + totalAttendance[i] + ": " + percentage + "%"+ "\n");
            }
            writer.write("\n");
            writer.close();
        } 
        catch (IOException e) {
            System.out.println("An error occurred while saving attendance data.");
            e.printStackTrace();
        }
        System.out.println("Attendance data saved successfully.");
    }

    public static void loadAttendanceFromFile() {
        File file = new File("attendance.txt");
        if (!file.exists()) {
            System.out.println("No previous attendance data found.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            
            int i = 0;
            while ((line = reader.readLine()) != null && i < 5) {
                if (line.contains(":")) {
                    String[] parts = line.split(":")[1].trim().split("/");
                    myAttendance[i] += Integer.parseInt(parts[0]);
                    totalAttendance[i]+= Integer.parseInt(parts[1]);
                    i++;
                }
                else{
                    continue;
                }
            }
            System.out.println("Previous attendance loaded successfully.\n");
        } catch (IOException e) {
            System.out.println("Error reading attendance file.");
            e.printStackTrace();
        }
    }

    public static boolean didAttend(String message) {
        System.out.println(message + " Attend? (y/n): ");
        return sc.nextLine().equalsIgnoreCase("y");
    }

    public static void markAttendance(int subjectIndex, int count) {
        totalAttendance[subjectIndex] += count;
        if (didAttend(subjects[subjectIndex] + " - " + count)) {
            myAttendance[subjectIndex] += count;
        }
    }

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
        markAttendance(CO, 1);
    }

    public static void tuesday() {
        markAttendance(OS,1);
        markAttendance(DM,2);
    }

    public static void wednesday() {
        markAttendance(OS,2);
        markAttendance(DAA,2);
        markAttendance(CO,1);
    }

    public static void thursday() {
        markAttendance(CO,2);
        markAttendance(NM,2);
        markAttendance(DM,2);
    }

    public static void friday() {
        markAttendance(OS,1);
        markAttendance(DAA,2);
        markAttendance(NM,1);
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
            markAttendance(subIndex, totalCount);
        }
    }

    public static void main(String[] args) {
        loadAttendanceFromFile();
        boolean running = true;

        while (running) {
            System.out.println("mon, tues, wed, thurs, fri, extra, exit :");
            String choice = sc.nextLine();

            switch (choice.toLowerCase()) {
                case "mon":
                    monday();
                    saveAttendanceToFile();
                    break;
                case "tues":
                    tuesday();
                    saveAttendanceToFile();
                    break;
                case "wed":
                    wednesday();
                    saveAttendanceToFile();
                    break;
                case "thurs":
                    thursday();
                    saveAttendanceToFile();
                    break;
                case "fri":
                    friday();
                    saveAttendanceToFile();
                    break;
                case "extra":
                    extra();
                    saveAttendanceToFile();
                    break;
                case "exit":
                    System.out.println("Bye bye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid input. Try again!");
            }
        }
    }
}
