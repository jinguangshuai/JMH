package com.jgs.jmh.course.class11;

public class Code08_CardsInLine {

    public static int win1(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        return Math.max(
                f(arr, 0, arr.length - 1),//自己作为先手获得的最大分数
                s(arr, 0, arr.length - 1));//自己作为先手，则对手为后手获得的最大分数
    }

    //先手拿获得的最大分数
    public static int f(int[] arr, int L, int R) {
        if (L == R) {
            return arr[L];
        }
        //自己作为先手，一定会拿最大的先手数字，加上之后在后手情况下（调用s函数），在后手区间（L+1...R）上获得的最大分数
        return Math.max(
                arr[L] + s(arr, L + 1, R),//arr[L]，自己先手拿了L的，则自己的后手只能拿L+1...R之间的数字，
                arr[R] + s(arr, L, R - 1));//arr[R]，自己先手拿了R的，则自己的后手只能拿L...R-1之间的数字
    }

    //后手拿获得的分数，由于后手所能获得的分数是由前手经过思考之后产生的，所以后手获得先手之后的最小分数
    //后手拿获得的最大分数
    public static int s(int[] arr, int L, int R) {
        if (L == R) {
            return 0;
        }
        //f(arr, L + 1, R)  代表先手挑走了arr[L]，只剩下L+1...R这个范围，自己则作为先手
        //f(arr, L, R - 1)) 代表先手挑走了arr[R]，只剩下L...R-1这个范围，自己则作为先手
        //上述两个过程是对手给你留下的，对手一定会给自己留最差的
        return Math.min(
                f(arr, L + 1, R), //arr[L]，对手先拿了L的，自己后手只能拿L+1到R之间的数字，此时自己作为L+1到R之前的先手
                f(arr, L, R - 1));//arr[R]，对手先拿了R的，自己后手只能拿L到R-1之间的数字，此时自己作为L到R-1之前的先手
    }

    public static int win3(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int N = arr.length;
        int[][] f = new int[N][N];
        int[][] s = new int[N][N];

		for (int i = 0; i < N; i++) {
			f[i][i]= arr[i];
			//可省略不写，因为初始化就为0
			//s[j][j] = 0;
		}

        for (int i = 1; i < arr.length; i++) {
            int L = 0;
            int R = i;
            while (L < N && R < N) {
                f[L][R] = Math.max(arr[L] + s[L + 1][R], arr[R] + s[L][R - 1]);
                s[L][R] = Math.min(f[L + 1][R], f[L][R - 1]);

                L++;
                R++;
            }
        }
        return Math.max(f[0][N - 1], s[0][N - 1]);
    }


    public static int win2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            f[j][j] = arr[j];
            //可省略不写，因为初始化就为0
            //s[j][j] = 0;
            //int i = j - 1; i >= 0; i-- 屏蔽L大于R的情况
            for (int i = j - 1; i >= 0; i--) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1]);
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1]);
            }
        }
        return Math.max(f[0][arr.length - 1], s[0][arr.length - 1]);
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 1, 5, 7, 11, 35, 36};
        System.out.println(win1(arr));
        System.out.println(win2(arr));
        System.out.println(win3(arr));

//		int[] arr = { 4,4,4,4 };
//		System.out.println(f(arr,0,3));
//		System.out.println(s(arr,0,3));

    }

}
