package com.jgs.jmh.leetCode15_devide;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther：jinguangshuai
 * @Data：2024/5/16 - 05 - 16 - 9:14
 * @Description:com.jgs.jmh.leetCode15_devide
 * @version:1.0
 */
public class test109_sortList {
    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
    //转为list排序，不推荐
    public static ListNode sortList1(ListNode head) {
        if (null == head) {
            return null;
        }
        List<ListNode> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }
        process1(list, 0, list.size() - 1);
        for (int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i + 1);
        }
        list.get(list.size() - 1).next = null;
        return list.get(0);
    }

    public static void process1(List<ListNode> list, int left, int right) {
        if (left == right) {
            return;
        }
        int mid = (left + right) / 2;
        process1(list, left, mid);
        process1(list, mid + 1, right);
        merge1(list, left, right, mid);
    }

    public static void merge1(List<ListNode> list, int left, int right, int mid) {
        List<ListNode> newList = new ArrayList<>();
        int p1 = left;
        int p2 = mid + 1;
        while (p1 <= mid && p2 <= right) {
            if (list.get(p1).val >= list.get(p2).val) {
                newList.add(list.get(p2));
                p2++;
            } else {
                newList.add(list.get(p1));
                p1++;
            }
        }
        while (p1 <= mid) {
            newList.add(list.get(p1++));
        }
        while (p2 <= right) {
            newList.add(list.get(p2++));
        }
        for (int i = 0; i < newList.size(); i++) {
            list.set(left + i, newList.get(i));
        }
    }


    //归并排序  分治   推荐
    //先将链表递归分为两个部分，然后进行合并排序，最后合并结果
    public static ListNode sortList2(ListNode head) {
        return mergeSort2(head);
    }
    public static ListNode mergeSort2(ListNode node) {
        if (null == node || null == node.next) {
            return node;
        }
        //1.找到链表中间的节点
        ListNode centerNode = findCenter(node);
        ListNode node1 = node;
        ListNode node2 = centerNode.next;
        //2.从链表中间断开成前后两个链表
        centerNode.next = null;
        //3.两个链表进行排序
        node1 = mergeSort2(node1);
        node2 = mergeSort2(node2);
        return mergeTwoListNode(node1, node2);
    }

    public static ListNode mergeTwoListNode(ListNode node1, ListNode node2) {
        if (null == node1) {
            return node2;
        } else if (null == node2) {
            return node1;
        }
        if (node1.val <= node2.val) {
            //如果node1值小于node2，那么node1的下一个节点只能从node1.next和node2挑选
            node1.next = mergeTwoListNode(node1.next, node2);
            return node1;
        } else {
            //如果node1值大于node2，那么node2的下一个节点只能从node1和node2.next挑选
            node2.next = mergeTwoListNode(node1, node2.next);
            return node2;
        }
    }

    public static ListNode findCenter(ListNode node) {
        ListNode slow = node;
        ListNode fast = node.next;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    //官方自顶向下归并排序
    //（1）找到链表的中点，以中点为分界，将链表拆分成两个子链表。
    //（2）对两个子链表分别排序。
    //（3）将两个排序后的子链表合并，得到完整的排序后的链表。
    public static ListNode sortList3(ListNode head) {
        return sortList3(head, null);
    }

    public static ListNode sortList3(ListNode head, ListNode tail) {
        if (null == head) {
            return head;
        }
        if (head.next == tail) {
            head.next = null;
            return head;
        }
        ListNode slow = head, fast = head;
        while (fast != tail) {
            slow = slow.next;
            fast = fast.next;
            if (fast != tail) {
                fast = fast.next;
            }
        }
        ListNode mid = slow;
        ListNode list1 = sortList3(head, mid);
        ListNode list2 = sortList3(mid, tail);
        ListNode sorted = merge3(list1, list2);
        return sorted;
    }

    public static ListNode merge3(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = head1, temp2 = head2;
        while (null != temp1 && null != temp2) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (null != temp1) {
            temp.next = temp1;
        }
        if (null != temp2) {
            temp.next = temp2;
        }
        return dummy.next;
    }

    //自底向上归并排序
    //(1)用subLength表示每次需要排序的子链表的长度，初始subLength=1
    //（2）每次将链表拆分成若干个长度subLength的子链表，最后一个子链表的长度可以小于subLength
    //按照每两个子链表一组进行合并，合并即可得到若干个长度为subLength*2的有序子链表
    public static ListNode sortList4(ListNode head) {
        if (null == head) {
            return head;
        }
        int length = 0;
        ListNode node = head;
        while (null != node) {
            length++;
            node = node.next;
        }
        ListNode dummy = new ListNode(0, head);
        for (int subLength = 1; subLength < length; subLength = subLength * 2) {
            ListNode pre = dummy, cur = dummy.next;
            while (null != cur) {
                //subLength
                ListNode node1 = cur;
                for (int i = 1; i < subLength && cur.next != null; i++) {
                    cur = cur.next;
                }
                //记录cur的下一个链表
                ListNode node2 = cur.next;
                //断为子链表
                cur.next = null;
                //从下一个子节点进行分割
                cur = node2;
                for (int i = 1; i < subLength && null != cur && null != cur.next; i++) {
                    cur = cur.next;
                }
                //记录下一次需要subLength的位置
                ListNode next = null;
                //将链表截为一段段长度为subLength的子链表
                if (null != cur) {
                    next = cur.next;
                    cur.next = null;
                }
                ListNode merged = merge4(node1, node2);
                //将排序好的自链表追加到虚拟节点的后边
                //例如 4  2   pre->2->4
                //例如 3  1   4->1->3
                pre.next = merged;
                while (null != pre.next) {
                    pre = pre.next;
                }
                cur = next;
            }
        }
        return dummy.next;
    }

    public static ListNode merge4(ListNode node1, ListNode node2) {
        ListNode dummy = new ListNode(0);
        ListNode temp = dummy, temp1 = node1, temp2 = node2;
        while (null != temp1 && null != temp2) {
            if (temp1.val <= temp2.val) {
                temp.next = temp1;
                temp1 = temp1.next;
            } else {
                temp.next = temp2;
                temp2 = temp2.next;
            }
            temp = temp.next;
        }
        if (null != temp1) {
            temp.next = temp1;
        }
        if (null != temp2) {
            temp.next = temp2;
        }
        return dummy.next;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        ListNode listNode = sortList4(head);
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }
}
