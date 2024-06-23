package com.jgs.jmh.leetCode.leetCode08_BinaryTree;

/**
 * @Auther：jinguangshuai
 * @Data：2024/4/25 - 04 - 25 - 14:46
 * @Description:com.jgs.jmh.leetCode08_BinaryTree
 * @version:1.0
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 *
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，
 * * 并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2^h 个节点。
 */
public class test80_countNodes {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode() {
        }

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    //非递归
    public static int countNodes(TreeNode root) {
        if(null == root){
            return 0;
        }
        int sum = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            sum++;
            if(null != node.left){
                queue.add(node.left);
            }
            if(null != node.right){
                queue.add(node.right);
            }
        }
        return sum;
    }
    static int sum = 0;
    public static int countNodes1(TreeNode root) {
        if(null == root){
            return 0;
        }
        sum++;
        countNodes1(root.left);
        countNodes1(root.right);
        return sum;
    }

    //官方解法  二叉树 + 位运算
    /* 二分求解
    - 举个例子高度从0开始：
      - 第二层的所有节点索引为 4、5、6、7，对应的二进制值为 100、101、110、111
      - 第三层的所有节点索引为 8、9、10、11、12、13、14、15，对应的二进制值为 1000、1001、1010、1011、1100、1101、1110、1111
    - 不难看出一个规律：去掉最高位 1 后，根据二进制中 1 和 0 可以判断该索引在树中的位置( 0 代表在左子树中， 1 代表在右子树中)：
     - 4的位置 00 => 左子树的左子树根节点索引；
     - 11的位置 011 => 左子树的右子树的右子树根节点索引；
    所以只需要根据这个路径推断看这个节点在树中是否存在即可。 */
    public static int countNodes2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int level = 0;
        TreeNode node = root;
        //遍历左节点，直到获得最大深度
        while (node.left != null) {
            level++;
            node = node.left;
        }
        //计算满二叉树的最小节点数目和最大节点数据
        //如果最后一层只有一个叶子节点，则为2^0+2^1+..+2^(level-1)+1=2^level  如果最后一层为满叶子节点2^0+2^1+..+2^(level-1)+2^level=2^(level+1) -1
        int low = 1 << level, high = (1 << (level + 1)) - 1;
        while (low < high) {
            int mid = (high - low + 1) / 2 + low;
            if (exists(root, level, mid)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return low;
    }
    //k为中点  level为固定层数
    //现在这个树中的值都是节点的编号，最底下的一层的编号是[2^h ，2^h - 1]，现在h = 2，也就是4, 5, 6, 7。
    //4, 5, 6, 7对应二进制分别为 100 101 110 111 不看最左边的1，从第二位开始，0表示向左，1表示向右，正好可以表示这个节点相对于根节点的位置。
    //比如4的 00 就表示从根节点 向左 再向左。6的 10 就表示从根节点 向右 再向左
    //
    //那么想访问最后一层的节点就可以从节点的编号的二进制入手。从第二位开始的二进制位表示了最后一层的节点相对于根节点的位置。
    //那么就需要一个bits = 2^(h - 1) 上面例子中的bits就是2，对应二进制为010。这样就可以从第二位开始判断。（树三层高，需要向左或向右走两次才能到叶子）

    //比如看5这个节点存不存在，先通过位运算找到编号为5的节点相对于根节点的位置。010 & 101 发现第二位是0，说明从根节点开始，第一步向左走。
    //之后将bit右移一位，变成001。001 & 101 发现第三位是1，那么第二步向右走。
    //最后bit为0，说明已经找到编号为5的这个节点相对于根节点的位置，看这个节点是不是空，不是说明存在，exist返回真
    //编号为5的节点存在，说明总节点数量一定大于等于5。所以二分那里low = mid

    //再比如看7存不存在，010 & 111 第二位为1，第一部从根节点向右；001 & 111 第三位也为1，第二步继续向右。
    //然后判断当前节点是不是null，发现是null，exist返回假。
    //编号为7的节点不存在，说明总节点数量一定小于7。所以high = mid - 1
    public static boolean exists(TreeNode root, int level, int k) {
        //1左移 level-1位，如果level为3，则移动两位 100，为4  如果level为2，则移动1位，为10
        //获取倒数第二层的节点，在与下层节点比较时，第一位的1已经被抹去
        int bits = 1 << (level - 1);
        TreeNode node = root;
        while (node != null && bits > 0) {
            //如果bits & k等于0，则证明node存在左子树
            if ((bits & k) == 0) {
                node = node.left;
            } else {
                node = node.right;
            }
            bits >>= 1;
        }
        return node != null;
    }




    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(11);
        root.right.left.left = new TreeNode(12);
        root.right.left.right = new TreeNode(13);
        root.right.right.left = new TreeNode(14);
        root.right.right.right = new TreeNode(15);
        System.out.println("------------");
        System.out.println(countNodes2(root));
    }
}
