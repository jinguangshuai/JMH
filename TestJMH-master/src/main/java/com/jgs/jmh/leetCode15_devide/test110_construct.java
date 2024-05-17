package com.jgs.jmh.leetCode15_devide;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/16 - 05 - 16 - 16:25
 * @Description:com.jgs.jmh.leetCode15_devide
 * @version:1.0
 */

/**
 * * 给你一个 n * n 矩阵 grid ，矩阵由若干 0 和 1 组成。请你用四叉树表示该矩阵 grid 。
 * <p>
 * 你需要返回能表示矩阵 grid 的 四叉树 的根结点。
 * <p>
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * <p>
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False。注意，
 * * 当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 */
public class test110_construct {
    static class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;


        public Node() {
            this.val = false;
            this.isLeaf = false;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = null;
            this.topRight = null;
            this.bottomLeft = null;
            this.bottomRight = null;
        }

        public Node(boolean val, boolean isLeaf, Node topLeft, Node topRight, Node bottomLeft, Node bottomRight) {
            this.val = val;
            this.isLeaf = isLeaf;
            this.topLeft = topLeft;
            this.topRight = topRight;
            this.bottomLeft = bottomLeft;
            this.bottomRight = bottomRight;
        }
    }

    public static Node construct1(int[][] grid) {
        if (null == grid || grid.length == 0) {
            return null;
        }
        Node process = process1(grid, 0, 0, grid.length, grid[0].length);
        return process;
    }

    public static Node process1(int[][] grid, int row1, int col1, int row2, int col2) {
        boolean same = true;
        for (int i = row1; i < row2; i++) {
            for (int j = col1; j < col2; j++) {
                if (grid[i][j] != grid[row1][col1]) {
                    same = false;
                    break;
                }
            }
            if (!same) break;
        }
        // same为true，则方格内所有节点与左上节点都一致，此节点则为叶子节点isLeaf为true，val值则为左上节点的值
        if (same) {
            return new Node(grid[row1][col1] == 1, true);
        }
        //否则的话，same为false，说明还有延伸下去的节点，则isLeaf为false，根据题意，val值可为任意数字，本题默认为true
        Node ret = new Node(true, false,
                //以grid的长度为4举例
                // 00 01 02 03
                // 10 11 12 13
                // 20 21 22 23
                // 30 31 32 33
                //row1, col1, (row1 + row2) / 2, (col1 + col2) /  表示左上方格第一位数字与其余三位对比
                // 00 01
                // 10 11
                process1(grid, row1, col1, (row1 + row2) / 2, (col1 + col2) / 2),
                //row1, (col1 + col2) / 2, (row1 + row2) / 2, col2 表示右上方格第一位数字与其余三位对比
                // 02 03
                // 12 13
                process1(grid, row1, (col1 + col2) / 2, (row1 + row2) / 2, col2),
                //(row1 + row2) / 2, col1, row2, (col1 + col2) / 2 表示左下方格第一位数字与其余三位对比
                // 20 21
                // 30 31
                process1(grid, (row1 + row2) / 2, col1, row2, (col1 + col2) / 2),
                //(row1 + row2) / 2, (col1 + col2) / 2, row2, col2 表示右下方格第一位数字与其余三位对比
                // 22 23
                // 32 33
                process1(grid, (row1 + row2) / 2, (col1 + col2) / 2, row2, col2)
        );
        return ret;
    }


    //暴力搜索优化
    //利用二维数组的前缀和：当某一部分均为0时，它的和为0；某一部分均为1时，它的和为这一部分的面积大小。
    public static Node construct2(int[][] grid) {
        int n = grid.length;
        int[][] pre = new int[n + 1][n + 1];
        //计算二维数组的前缀和
        //计算公式为s[i][j] = s[i-1][j] + s[i][j-1] + grid[i-1][j-1] - s[i-1][j-1]
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                pre[i][j] = pre[i - 1][j] + pre[i][j - 1] + grid[i - 1][j - 1] - pre[i - 1][j - 1];
            }
        }
        return dfs2(grid, pre, 0, 0, n, n);
    }

    public static Node dfs2(int[][] grid, int[][] pre, int row1, int col1, int row2, int col2) {
        int total = getSum(pre, row1, col1, row2, col2);
        //如果前缀和为0，则全部为0，认为总的为叶子节点，则val为0（false），isLeaf为1（true）
        if (total == 0) {
            return new Node(false, true);
            //如果前缀和为(row2 - row1) * (col2 - col1)，则全部为1，认为总的为叶子节点，则val为1（true），isLeaf为1（true）
        } else if (total == (row2 - row1) * (col2 - col1)) {
            return new Node(true, true);
        }

        Node ret = new Node(
                true,
                false,
                dfs2(grid, pre, row1, col1, (row1 + row2) / 2, (col1 + col2) / 2),
                dfs2(grid, pre, row1, (col1 + col2) / 2, (row1 + row2) / 2, col2),
                dfs2(grid, pre, (row1 + row2) / 2, col1, row2, (col1 + col2) / 2),
                dfs2(grid, pre, (row1 + row2) / 2, (col1 + col2) / 2, row2, col2)
        );
        return ret;
    }

    public static int getSum(int[][] pre, int row1, int col1, int row2, int col2) {
        return pre[row2][col2] - pre[row2][col1] - pre[row1][col2] + pre[row1][col1];
    }


    public static void main(String[] args) {
        int[][] grid = {{0, 1}, {1, 0}};
//        int[][] grid = {{1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,1,1,1,1},
//                {1,1,1,1,1,1,1,1},
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,0,0},
//                {1,1,1,1,0,0,0,0}};
        Node construct = construct1(grid);
        System.out.println(construct);
    }
}
