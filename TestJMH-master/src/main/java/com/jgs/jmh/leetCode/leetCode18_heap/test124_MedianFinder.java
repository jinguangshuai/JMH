package com.jgs.jmh.leetCode.leetCode18_heap;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/23 - 05 - 23 - 11:05
 * @Description:com.jgs.jmh.leetCode18_heap
 * @version:1.0
 */

/**
 * *中位数是有序整数列表中的中间值。如果列表的大小是偶数，则没有中间值，中位数是两个中间值的平均值。
 * <p>
 * 例如 arr = [2,3,4] 的中位数是 3 。
 * 例如 arr = [2,3] 的中位数是 (2 + 3) / 2 = 2.5 。
 * 实现 MedianFinder 类:
 * <p>
 * MedianFinder() 初始化 MedianFinder 对象。
 * <p>
 * void addNum(int num) 将数据流中的整数 num 添加到数据结构中。
 * <p>
 * double findMedian() 返回到目前为止所有元素的中位数。与实际答案相差 10-5 以内的答案将被接受。
 */
public class test124_MedianFinder {
    //优先级队列每次获取最小的元素
    public static class MedianFinder1 {
        PriorityQueue<Integer> queueMin;
        PriorityQueue<Integer> queueMax;

        public MedianFinder1() {
            //建立小根堆
            queueMin = new PriorityQueue<Integer>((x, y) -> (x - y));
            //建立大根堆
            queueMax = new PriorityQueue<Integer>((x, y) -> (y - x));
        }
        //两个优先队列，大顶堆堆顶 < 小顶堆的堆顶——并且通过判断当前两个堆中元素个数的奇偶情况，判断元素的加入顺序；
        //注意：我们不能将元素只加入其中一个堆，这样可能破坏两个堆之间：大顶堆堆顶 < 小顶堆的堆顶的关系；必须将加入的元素在两个堆之间都过一遍，使堆维持其特性
        //如果当前所有元素个数为偶数：先加入小顶堆，调整之后再将小顶堆的堆顶加入到大顶堆——这样调整后大顶堆的元素个数最多比小顶堆多一个，最终输出中位数的时候输出大顶堆的堆顶即可
        //如果当前所有元素个数为奇数：先加入大顶堆，调整后再将大顶堆的堆顶加入小顶堆；
        //将一个集合划分为两个长度相等的子集，其中一个子集中的元素总是大于另一个子集中的元素。

        //所有的元素必须在两个堆之间都过一遍，否则会破坏两个堆的关系 大根堆顶<=小根堆顶
        public void addNum(int num) {
            //如果当前所有元素个数为偶数:先加入小顶堆，调整之后再将小顶堆的堆顶加入到大顶堆
            //这样调整后大顶堆的元素个数最多比小顶堆多一个，最终输出中位数的时候输出大顶堆的堆顶即可
            if (queueMin.size() == queueMax.size()) {
                //个数为偶数，则先加入小根堆，拿到最小值，在放到大根堆上
                queueMin.add(num);
                queueMax.add(queueMin.poll());
            } else {
                //如果当前所有元素个数为奇数：先加入大顶堆，调整后再将大顶堆的堆顶加入小顶堆
                //如果元素的总个数为奇数，则先加入大根堆，获取最大值，在放到小根堆上
                queueMax.add(num);
                queueMin.add(queueMax.poll());
            }

        }

        public double findMedian() {
            if (queueMax.size() == queueMin.size()) {
                return (queueMin.peek() + queueMax.peek()) / 2.0;
            } else {
                return queueMax.peek();
            }
        }
    }

    //官方解法：方法一：优先队列
    public static class MedianFinder2 {
        //存储小于等于中位数的值
        PriorityQueue<Integer> queueLess;
        //存储大于中位数的值
        PriorityQueue<Integer> queueMore;

        public MedianFinder2() {
            //建立大根堆，存储小于等于中位数的值
            queueLess = new PriorityQueue<Integer>((x, y) -> (y - x));
            //建立小根堆，存储大于中位数的值
            queueMore = new PriorityQueue<Integer>((x, y) -> (x - y));
        }

        public void addNum(int num) {
            //如果queueLess为空的话，则优先加入queueLess
            //如果当前要添加的数字小于queueLess的队头，先加入queueLess
            if (queueLess.isEmpty() || num <= queueLess.peek()) {
                queueLess.offer(num);
                //如果queueLess的数量比queueMore的数量大，并且数量超过1，
                //例如queueLess数量为4，queueMore数量为2，则需要queueMore添加一个queueLess的数字，保证平衡
                if (queueMore.size() + 1 < queueLess.size()) {
                    queueMore.add(queueLess.poll());
                }
            } else {
                //如果新增的数字大于queueLess的队头，则添加到queueMore
                //新的中位数将大于等于原来的中位数
                queueMore.add(num);
                //如果queueMore的数量大于queueLess的数量，则需要将queueMore最小的数移动到queueLess
                if (queueMore.size() > queueLess.size()) {
                    queueLess.add(queueMore.poll());
                }
            }
        }

        public double findMedian() {
            //当小根堆的数量比大根堆多一个的时候，中位数为小根堆的队头
            if (queueLess.size() > queueMore.size()) {
                return queueLess.peek();
            }
            return (queueLess.peek() + queueMore.peek()) / 2.0;
        }
    }


    /**
     * *如果数据流中所有整数都在0到100范围内，你将如何优化你的算法？
     * 可以使用建立长度为 101的桶，每个桶分别统计每个数的出现次数，同时记录数据流中总的元素数量，每次查找中位数时，先计算出中位数是第几位，从前往后扫描所有的桶得到答案。
     * 这种做法相比于对顶堆做法，计算量上没有优势，更多的是空间上的优化。
     * 和上一问解法类似，对于 1% 采用哨兵机制进行解决即可，在常规的最小桶和最大桶两侧分别维护一个有序序列，即建立一个代表负无穷和正无穷的桶。
     */
    public static class MedianFinder3 {
        TreeMap<Integer, Integer> head = new TreeMap<>(), tail = new TreeMap<>();
        int[] arr = new int[101];
        // a代表小于0的数字  b代表0-100的数字  c代表大于100的数字
        int a, b, c;
        public void addNum(int num) {
            if (num >= 0 && num <= 100) {
                arr[num]++;
                b++;
            } else if (num < 0) {
                head.put(num, head.getOrDefault(num, 0) + 1);
                a++;
            } else if (num > 100) {
                tail.put(num, tail.getOrDefault(num, 0) + 1);
                c++;
            }
        }
        public double findMedian() {
            int size = a + b + c;
            if (size % 2 == 0) return (find(size / 2) + find(size / 2 + 1)) / 2.0;
            return find(size / 2 + 1);//注意
        }
        int find(int n) {
            //如果 size /2 小于a，说明中位数处于小于0的位置，遍历key，直至n<=0即为最终的中位数
            if (n <= a) {
                for (int num : head.keySet()) {
                    n -= head.get(num);
                    if (n <= 0) return num;
                }
            } else if (n <= a + b) {
                //如果 size /2 小于a+b，说明中位数处于0-100的位置，遍历0-100，直至n<=0即为最终的中位数
                n -= a;
                for (int i = 0; i <= 100; i++) {
                    n -= arr[i];
                    if (n <= 0) return i;
                }
            } else {
                //如果 size /2 小于a+b+c，说明中位数处于大于100的位置，遍历tail，直至n<=0即为最终的中位数
                n -= a + b;
                for (int num : tail.keySet()) {
                    n -= tail.get(num);
                    if (n <= 0) return num;
                }
            }
            return -1; // never
        }
    }



    //不看
    //有序集合+双指针
    public static class MedianFinder4 {
        TreeMap<Integer, Integer> nums;
        int n;
        int[] left;
        int[] right;

        public MedianFinder4() {
            nums = new TreeMap<>();
            n = 0;
            left = new int[2];
            right = new int[2];
        }

        public void addNum(int num) {
            nums.put(num, nums.getOrDefault(num, 0) + 1);
            if (n == 0) {
                //初始集合为空，直接让左右指针指向num所在的位置
                left[0] = right[0] = num;
                left[1] = right[1] = 1;
                //如果有序集合的元素总数个数为奇数的时候，左右指针同时指向中位数
            } else if ((n & 1) != 0) {
                //如果当前值小于中位数，则left左移
                if (num < left[0]) {
                    decrease(left);
                } else {
                    //如果当前值大于等于中位数 ，则right右移
                    increase(right);
                }
            } else {
                if (num > left[0] && num < right[0]) {
                    //left右移
                    increase(left);
                    //right左移
                    decrease(right);
                } else if (num >= right[0]) {
                    //left右移
                    increase(left);
                } else {
                    //right左移
                    //如果num恰好等于left指向的值，那么num将被插入到left右侧，使得left和right间距增大
                    //所以我们还需要额外让left指向移动后的right；
                    decrease(right);
                    System.arraycopy(right, 0, left, 0, 2);
                }
            }
            n++;
        }

        public double findMedian() {
            return (left[0] + right[0]) / 2.0;
        }
        //指针右移
        public void increase(int[] arr) {
            arr[1]++;
            if (arr[1] > nums.get(arr[0])) {
                //nums.ceilingKey返回大于等于指定键值  的最小值  nums的key为 1 3 5 7 输入4返回5，输入5返回5，输入8返回null
                arr[0] = nums.ceilingKey(arr[0] + 1);
                arr[1] = 1;
            }
        }
        //指针左移
        public void decrease(int[] arr) {
            arr[1]--;
            if (arr[1] == 0) {
                //nums.floorKey返回小于等于指定键值  的最大值  nums的key为 1 3 5 7 输入4返回3，输入5返回5，输入0返回null
                arr[0] = nums.floorKey(arr[0] - 1);
                arr[1] = nums.get(arr[0]);
            }
        }
    }


}
