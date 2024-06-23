package com.jgs.jmh.course.class11;

/**
 * @Auther：jinguangshuai
 * @Data：2024/3/1 - 03 - 01 - 16:22
 * @Description:com.mashibing.jmh.class11
 * @version:1.0
 */
public class CardGetMaxScore {
    public static int getResult(int[] arr){
        if(null==arr&&arr.length==0){
            return 0;

        }
        return Math.max(first(arr,0,arr.length),second(arr,0,arr.length));
    }
    
    public static int first(int[] arr,int L,int R){
        if(L == R){
            return arr[L];
        }
        int m = arr[L] + second(arr,L+1,R);
        int n = arr[R] + second(arr,L,R-1);
        return Math.max(m,n);
    }

    public static int second(int[] arr,int L,int R){
        if(L == R){
            return 0;
        }
        int m = first(arr,L+1,R);
        int n = first(arr,L,R-1);
        return Math.min(m,n);

    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,3,5,7,9,11};
        System.out.println(getResult(arr));
    }

}
