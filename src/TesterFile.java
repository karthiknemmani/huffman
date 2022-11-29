public class TesterFile {
    public static void main(String[] args) {
        PriorityQueue314<Integer> pq = new PriorityQueue314<>();
        pq.enqueue(3);
        pq.enqueue(5);
        pq.enqueue(0);
        pq.enqueue(0);
        pq.enqueue(1);
        pq.enqueue(7);
        pq.enqueue(8);
        pq.enqueue(6);
        pq.enqueue(0);
        pq.enqueue(5);
        System.out.println(pq);
    }
}
