import java.io.File;
import java.util.Scanner;

/**
 * ConsoleIO class handles the console-based
 * interaction for processing popular names data.
 * 
 * @author Ahmad Shaja AZIMI
 */
public class ConsoleIO {
    // Linked list to store male names
    private static SinglyLinkedList males = new SinglyLinkedList();
    // Linked list to store female names
    private static SinglyLinkedList females = new SinglyLinkedList();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Main method for running the console-based program.
     */
    public static void main(String[] args) {
        System.out.println("Enter file name: ");
        String fileName = sc.nextLine();
        Scanner inputStream = null;
        try {
            File file = new File(fileName);
            if (file.exists()) {
                inputStream = new Scanner(file);
                System.out.println("File has been uploaded");
            } else {
                System.out.println("File not found");
            }
        } catch (Exception e) {
            System.out.println("Error while reading file " + fileName);
            inputStream.close();
        }
        try {
            // reads data from file
            while (inputStream.hasNextLine()) {
                String line = inputStream.nextLine();
                String[] order = line.split(",");
                int rank = Integer.parseInt(order[0]);
                String maleName = order[1];
                int maleNumbers = Integer.parseInt(order[2]);
                String femaleName = order[3];
                int femaleNumbers = Integer.parseInt(order[4]);

                // add them to the linked list based on thier gender
                males.insertInOrder(new PopularName(maleName, rank, maleNumbers));
                females.insertInOrder(new PopularName(femaleName, rank, femaleNumbers));
            }

        } catch (Exception e) { // catchs the possible exception
            System.out.println("Error reading data from file");
            System.exit(0);
        }
        // MENU
        boolean roll = true;
        while (roll) {

            // pops up the menu and prompts user to select one of the options
            System.out.println("\n");
            System.out.println("1 Name Statistics");
            System.out.println("2 Most Popular Name");
            System.out.println("3 List by Initial");
            System.out.println("4 Exit");
            System.out.println("\n");

            System.out.println("Enter your choice: ");
            String choice = sc.nextLine();
            switch (choice) {
                case "1":
                    nameStatistics();
                    break;
                case "2":
                    mostPopularName();
                    break;
                case "3":
                    doListByInitial();
                    break;
                case "4":
                    roll = false;
                    System.out.println("Exiting........");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    /**
     * Provides statistics for a specific name based on gender.
     */
    private static void nameStatistics() {

        System.out.println("Enter a gender (male/female):");
        String gender = sc.nextLine();

        if (gender.equalsIgnoreCase("male")) {
            System.out.println("Enter a male name: ");
            String maleName = sc.nextLine();
            PopularName statistics = males.getStatistics(maleName);
            int index = males.getIndexInSortedList(males, statistics);
            if (index >= 0) {
                if (statistics != null) {
                    System.out.println("\n" + statistics.getName() + ":");
                    System.out.println("Index in sorted list: " + index);
                    System.out.println("Rank in popularity: " + statistics.getRank());
                    System.out.println("Number of babies with this name: " + statistics.getNumberOfBabies());
                    double mPercentage = (double) statistics.getNumberOfBabies() / males.getTotalBabies() * 100;
                    System.out.printf("Percentage of male names with this name: %.2f%%", mPercentage);
                } else {
                    System.out.println("Name not found in the linked list.");
                }
            }

        } else if (gender.equalsIgnoreCase("female")) {
            System.out.println("Enter a female name: ");
            String femaleName = sc.nextLine();

            PopularName statistics = females.getStatistics(femaleName);
            int index = females.getIndexInSortedList(females, statistics);
            if (index >= 0) {
                if (statistics != null) {
                    System.out.println("Index in sorted list: " + index);
                    System.out.println("Rank in popularity: " + statistics.getRank());
                    System.out.println("Number of babies with this name: " + statistics.getNumberOfBabies());
                    double fPercentage = (double) statistics.getNumberOfBabies() / females.getTotalBabies() * 100;
                    System.out.printf("Percentage of female names with this name: %.2f%%", fPercentage);
                } else {
                    System.out.println("Name not found in the linked list.");
                }
            }
        } else {
            System.out.println("Invalid gender.");
        }
    }

    /**
     * Retrieves the most popular name based on gender.
     */
    private static void mostPopularName() {
        System.out.println("Enter a gender (male/female):");
        String gender = sc.nextLine();

        SinglyLinkedList selectedList;
        if (gender.equalsIgnoreCase("male")) {
            selectedList = males;
        } else {
            selectedList = females;
        }

        PopularName mostPopularName = selectedList.getMostPopularName();
        if (mostPopularName != null) {
            System.out.println("Most popular " + gender + " name is " + mostPopularName.getName() + ": "
                    + mostPopularName.getNumberOfBabies());
        } else {
            System.out.println("No " + gender + " names found in the linked list.");
        }
    }

    /**
     * Lists names starting with a specific initial based on gender.
     */
    private static void doListByInitial() {
        System.out.println("Enter a gender (male/female):");
        String gender = sc.nextLine();
        System.out.println("Enter an initial:");
        char initial = sc.nextLine().charAt(0);

        SinglyLinkedList list;
        if (gender.equalsIgnoreCase("male")) {
            list = males;
        } else if (gender.equalsIgnoreCase("female")) {
            list = females;
        } else {
            System.out.println("Invalid gender.");
            return;
        }
        System.out.println("List of names starting with " + initial + ":");
        list.listByInitial(initial);
    }
}