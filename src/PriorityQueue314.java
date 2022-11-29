import java.util.ArrayList;

public class PriorityQueue314<E extends Comparable<? super E>> {
    private ArrayList<E> priorityQueue;

    public PriorityQueue314() {
        priorityQueue = new ArrayList<>();
    }
    
    /**
     * Add an item to the queue based on priority
     * @param item the item to be added
     */
    public void enqueue(E item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (priorityQueue.size() == 0) {
            priorityQueue.add(item);
        } else {
            // get temporary insert index barring ties
            int indexToAdd = bSearchIndex(item);
            // break ties
            while (indexToAdd > 0 && item.compareTo(priorityQueue.get(indexToAdd - 1)) == 0) {
                indexToAdd--;
            }
            priorityQueue.add(indexToAdd, item);
        }
    }

    /**
     * Find the index of item in list, if doesn't exist, get the index where it would be had it
     * been in list
     * Partially uses binary search algorithm developed in class
     * @param item the item to search for
     * @return the index of item in list or index where it would be
     */
    private int bSearchIndex(E item) {
        int low = 0;
        int high = priorityQueue.size() - 1;
        int mid = low + ((high - low) / 2);
        boolean found = false;
        while (!found && low <= high) {
            int compareResult = item.compareTo(priorityQueue.get(mid));
            if (compareResult == 0) {
                found = true;
            } else if (compareResult < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
            mid = low + ((high - low) / 2);
        }
        return mid;
    }

    /**
     * Remove first element from the queue
     * @return the element removed
     */
    public E dequeue() {
        if (priorityQueue.isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue when no elements");
        }
        return priorityQueue.remove(priorityQueue.size() - 1);
    }

    /**
     * Gets the first element in queue without removing
     * @return the first element in queue
     */
    public E front() {
        if (priorityQueue.isEmpty()) {
            throw new IllegalArgumentException("No values in queue");
        }
        return priorityQueue.get(priorityQueue.size() - 1);
    }

    /**
     * Checks if queue is empty
     * @return true if size = 0, false if not
     */
    public boolean isEmpty() {
        return priorityQueue.isEmpty();
    }

    /**
     * Gets size
     * @return size of queue
     */
    public int size() {
        return priorityQueue.size();
    }

    // for debugging, remove at the end
    public String toString() {
        return priorityQueue.toString();
    }
}
