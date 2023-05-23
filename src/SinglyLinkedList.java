/**
 * This class represents a singly linked list that stores PopularName objects.
 */
public class SinglyLinkedList {
    private Node head; // The first node in the linked list
    private Node tail; // The last node in the linked list

    /**
     * This class represents a node in the linked list.
     */
    private class Node {
        private PopularName data; // The data stored in the node
        private Node next; // Reference to the next node in the linked list

        /**
         * Constructs a new node with the given data.
         * 
         * @param data The PopularName object to be stored in the node.
         */
        public Node(PopularName data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Constructs an empty singly linked list.
     */
    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    /**
     * Checks if the linked list is empty.
     * 
     * @return true if the linked list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts a PopularName object into the linked list in its correct order based
     * on name.
     * 
     * @param element The PopularName object to be inserted.
     */
    public void insertInOrder(PopularName element) {
        // creates a new Node with the given element as its data
        Node newNode = new Node(element);

        // If the list is empty or the new element should be inserted at the beginning
        if (isEmpty() || element.getName().compareTo(head.data.getName()) <= 0) {
            newNode.next = head;
            head = newNode;

            // If the list was empty, update the tail reference
            if (tail == null) {
                tail = newNode;
            }
        } else {
            Node current = head;

            // Find the correct position to insert the new element
            while (current.next != null && element.getName().compareTo(current.next.data.getName()) > 0) {
                current = current.next;
            }

            newNode.next = current.next;
            current.next = newNode;

            // If the new element was inserted at the end, update the tail reference
            if (current == tail) {
                tail = newNode;
            }
        }
    }

    /**
     * Retrieves the PopularName object with the given name from the linked list.
     * 
     * @param name The name to search for.
     * @return The PopularName object if found, or null if not found.
     */
    public PopularName getStatistics(String name) {
        Node current = this.head;

        // Traverse the linked list to find the name
        while (current != null) {
            if (current.data.getName().equals(name)) {
                return current.data; // Return the PopularName object if name matches
            }
            current = current.next;
        }
        return null; // Name not found in the linked list
    }

    /**
     * Retrieves the index of a PopularName object in the sorted linked list.
     * 
     * @param list The linked list containing the name.
     * @param name The PopularName object to search for.
     * @return The index of the name if found, or -1 if not found.
     */
    public int getIndexInSortedList(SinglyLinkedList list, PopularName name) {
        Node current = list.head;
        int index = 0;

        // Traverse the linked list to find the name and return its index
        while (current != null) {
            if (current.data == name) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1; // returns -1 if not found
    }

    /**
     * Calculates the total number of babies in the linked list.
     * 
     * @return The total number of babies.
     */
    public int getTotalBabies() {
        int totalMaleBabies = 0;
        Node current = head;

        // Traverse the linked list and sum up the number of babies
        while (current != null) {
            PopularName name = current.data;
            totalMaleBabies += name.getNumberOfBabies();
            current = current.next;
        }
        return totalMaleBabies;
    }

    /**
     * Retrieves the most popular PopularName object from the linked list based on
     * the number of babies.
     * 
     * @return The most popular PopularName object, or null if the list is empty.
     */
    public PopularName getMostPopularName() {
        if (isEmpty()) {
            return null; // Empty list, no names available
        }

        PopularName mostPopular = head.data;
        Node current = head.next;

        // Traverse the linked list and find the most popular name
        while (current != null) {
            PopularName name = current.data;
            if (name.getNumberOfBabies() > mostPopular.getNumberOfBabies()) {
                mostPopular = name;
            }
            current = current.next;
        }

        return mostPopular;
    }

    /**
     * Prints the names starting with the specified initial and gender from the
     * linked list.
     * 
     * @param gender  The gender of the names to be printed ("male" or "female").
     * @param initial The initial character to filter the names.
     */
    public void listByInitial(char initial) {
        Node current = head;
        boolean found = false;

        // Traverse the linked list and print the names that match the given gender and
        // initial
        while (current != null) {
            PopularName name = current.data;
            if (name.getName().charAt(0) == initial) {
                System.out.println(name.getName() + ": " + "[" + name.getNumberOfBabies() + "]");
                found = true;
            }
            current = current.next;
        }

        if (!found) {
            System.out.println("No names found starting with '" + initial + ".");
        }
    }
}