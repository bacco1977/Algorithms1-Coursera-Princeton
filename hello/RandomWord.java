/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomWord {
    public static void main(String[] args) {
        String champion = "";
        int counter = 0;
        while (!StdIn.isEmpty()) {
            String word = StdIn.readString();
            counter++;
            // replace with probability 1/counter
            if (counter == 1) {
                champion = word;
            }
            else if (counter > 1 && StdRandom.bernoulli((double) 1 / counter)) {
                champion = word;
            }
        }
        StdOut.println(champion);

    }
}
