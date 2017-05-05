//Author: Clarence Guo
import java.util.ArrayList;

public class FindSimilarWord {

    //find the minimum between two int values.
    public static int min(int x, int y) {
        if (x <= y) {
            return x;
        } else {
            return y;
        }
    }

    // find the most biggest value in a int Array.
    public static int theHighestSameTimesIndex(int[] SameTimes, int len) {
        int x = SameTimes[0], m = len;
        if (sumOfArray(SameTimes, len) != 0) {
            for (int p = 0; p < len; p++) {
                if (x <= SameTimes[p]) {
                    x = SameTimes[p];
                    m = p;
                }
            }
        }
        return m;
    }

    //find the sum of the whole values in a int Array.
    public static int sumOfArray(int[] SameTimes, int len) {
        int Sum = 0;
        for (int x = 0; x < len; x++) {
            Sum = Sum + SameTimes[x];
        }
        return Sum;
    }

    //find the index (in the ArrayList named wordlist) of the most likely correct word that users want to type correctly.
    public static int SWN(String WORD, ArrayList<DManager> wordlist, int len) {
        int WordLen = WORD.length(), ArrayWordLen, LEN, m, p;
        int[] SameTimes = new int[len];
        String x1, x2;
        for (int w = 0; w < len; w++) {
            p = 0;
            if (WORD.substring(0, 1).equalsIgnoreCase(wordlist.get(w).getWord().substring(0, 1))) {
                ArrayWordLen = wordlist.get(w).getWord().length();
                LEN = min(WordLen, ArrayWordLen);
                for (int y = 0; y < LEN; y++) {
                    x1 = WORD.substring(y, y + 1);
                    x2 = wordlist.get(w).getWord().substring(y, y + 1);
                    if (x1.equalsIgnoreCase(x2)) {
                        p++;
                    }
                }
                SameTimes[w] = p;
            }
        }
        m = theHighestSameTimesIndex(SameTimes, len);
        return m;
    }
}