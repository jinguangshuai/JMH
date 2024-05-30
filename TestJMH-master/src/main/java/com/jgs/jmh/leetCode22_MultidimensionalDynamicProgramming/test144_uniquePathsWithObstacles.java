package com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/30 - 05 - 30 - 14:47
 * @Description:com.jgs.jmh.leetCode22_MultidimensionalDynamicProgramming
 * @version:1.0
 */

/**
 * * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * <p>
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish”）。
 * <p>
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 * <p>
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 */
public class test144_uniquePathsWithObstacles {

    public static int uniquePathsWithObstacles1(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        if (obstacleGrid[0][0] == 0) {
            dp[0][0] = 1;
        } else {
            return 0;
        }
        //初始化第一行
        for (int i = 1; i < col; i++) {
            if (obstacleGrid[0][i] != 1) {
                dp[0][i] = 1;
            } else {
                //如果第一行i位置存在1，那么i及之后的位置全部为0，并结束循环
                break;
            }
        }
        //初始化第一列
        for (int i = 1; i < row; i++) {
            if (obstacleGrid[i][0] != 1) {
                dp[i][0] = 1;
            } else {
                //如果第一列i位置存在1，那么i及之后的位置全部为0，并结束循环
                break;
            }
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (obstacleGrid[i][j] != 1) {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                } else {
                    continue;
                }
            }
        }
        return dp[row - 1][col - 1];
    }


    //动态规划优化为一维数组   滚动数组思想
    //「滚动数组思想」是一种常见的动态规划优化方法，
    // 在我们的题目中已经多次使用到，例如「剑指 Offer 46. 把数字翻译成字符串」、
    // 「70. 爬楼梯」等，当我们定义的状态在动态规划的转移方程中只和某几个状态相关的时候，
    // 就可以考虑这种优化方法，目的是给空间复杂度「降维」。
    public static int uniquePathsWithObstacles2(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[] dp = new int[col];
        dp[0] = obstacleGrid[0][0] == 0 ? 1 : 0;
        //遍历数组的方向从上往下
        for (int i = 0; i < row; i++) {
            //细节上从左往右，每个f[j]默认继承上一行f[j]的值，只需要添加上来自左边的值
            for (int j = 0; j < col; j++) {
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                    continue;
                }
                //保证从第j-1列到第j列可以走通，则可以方法数+1
                //每个f[j]默认继承上一行f[j]的值，只需要添加上来自左边的值dp[j] = dp[j] + dp[j - 1];
                if (j - 1 >= 0 && obstacleGrid[i][j - 1] == 0) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }
        return dp[col - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid = {{0, 0, 1}, {0, 0, 0}, {0, 0, 0}};
//        int[][] obstacleGrid = {{0}};
        System.out.println(uniquePathsWithObstacles1(obstacleGrid));
        System.out.println(uniquePathsWithObstacles2(obstacleGrid));
    }
}
