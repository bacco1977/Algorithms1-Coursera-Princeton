/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;

public class Permutation {
    public static void main(String[] args) {
        int k = Integer.parseInt(args[0]); // number of strings to print
        RandomizedQueue<String> queue = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (queue.size() < k) { // haven't added k strings yet
                queue.enqueue(s);
            }
            else {
                int r = StdRandom.uniformInt(
                        queue.size() + 1); // random number between 0 and queue size
                if (r < k) { // replace an existing string with the new one
                    queue.dequeue();
                    queue.enqueue(s);
                }
            }
        }

        Iterator<String> iterator = queue.iterator();
        while (iterator.hasNext() && k > 0) {
            StdOut.println(iterator.next());
            k--;
        }
    }
}
