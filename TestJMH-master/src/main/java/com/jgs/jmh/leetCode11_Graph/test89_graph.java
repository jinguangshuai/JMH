package com.jgs.jmh.leetCode11_Graph;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/7 - 05 - 07 - 10:10
 * @Description:com.jgs.jmh.leetCode11_Graph
 * @version:1.0
 */
public class test89_graph {

    public static class UnionFindGraph {
        private int[] parent; // 存储每个节点的父节点
        private int[] size;   // 存储以每个节点为根的子树大小
        private int count;    // 记录当前连通分量的数量

        public UnionFindGraph(int n) {
            count = n;
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        // 查找节点x的根节点（代表元素）
        private int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 路径压缩
            }
            return parent[x];
        }

        // 合并节点x和节点y所在的连通分量
        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
//                if (size[rootX] > size[rootY]) {
//                    parent[rootY] = rootX;
//                } else if (size[rootX] < size[rootY]) {
//                    parent[rootX] = rootY;
//                } else {
//                    parent[rootY] = rootX;
//                    size[rootX] += 1;
//                }
                if (size[rootX] < size[rootY]) {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                } else {
                    //rootX >= rootY
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                }
                count--;
            }
        }

        // 判断节点x和节点y是否属于同一个连通分量
        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        // 返回图中的连通分量数量
        public int getCount() {
            return count;
        }

        public static void main(String[] args) {
            UnionFindGraph ufg = new UnionFindGraph(5);

            ufg.union(0, 1);
            ufg.union(1, 2);
            ufg.union(3, 4);

            System.out.println(ufg.connected(0, 2)); // 输出 true
            System.out.println(ufg.connected(0, 3)); // 输出 false
            System.out.println(ufg.getCount());      // 输出 2
        }
    }
}
