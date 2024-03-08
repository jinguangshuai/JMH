package com.mashibing.jmh.class11;

/**
 * @Auther：jinguangshuai
 * @Data：2024/2/28 - 02 - 28 - 20:15
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class knapsackTest {

    public static int getResult(int[] w, int[] v, int bag) {
        return process(w, v, 0, 0, bag);

    }

    public static int process(int[] w, int[] v, int index, int alreadyUse, int bag) {
        if (alreadyUse > bag) { //base case
            return -1;
        }
        if (index == w.length) { //base case
            return 0;
        }

        int p1 = process(w, v, index + 1, alreadyUse, bag);
        int p2Next = process(w, v, index + 1, alreadyUse + w[index], bag);
        int p2 = -1;
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);
    }

    public static int getResult1(int[] w, int[] v, int bag) {
        return process(w, v, 0, bag);

    }

    public static int process(int[] w, int[] v, int index, int rest) {
        if (rest < 0) {
            return -1;
        }
        if (index == w.length) {
            return 0;
        }

        int p1 = process(w, v, index + 1, rest);

        int p2Next = process(w, v, index + 1, rest - w[index]);
        int p2 = -1;
        if (p2Next != -1) {
            p2 = v[index] + p2Next;
        }
        return Math.max(p1, p2);

    }

    public static void main(String[] args) {
        int[] w = new int[]{3, 4, 7, 6, 7};

        int[] v = new int[]{7, 8, 8, 9, 3};
        System.out.println(getResult(w, v, 10));
        System.out.println(getResult1(w, v, 10));

    }


}
