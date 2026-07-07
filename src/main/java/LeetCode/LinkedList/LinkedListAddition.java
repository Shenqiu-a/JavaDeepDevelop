package LeetCode.LinkedList;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能：链表两数相
 * 作者：yml
 * 日期：2026/3/1914:56
 */

@Slf4j
public class LinkedListAddition {

    static class MyListNode {
        int val;
        MyListNode next;
        MyListNode(int x) {
            val = x;
        }
        MyListNode(int x, MyListNode next) {
            val = x;
            this.next = next;
        }
        MyListNode() {}
    }

    public static void main(String[] args) {
        MyListNode node1 = new MyListNode(2);
        MyListNode node2 = new MyListNode(3, node1);
        MyListNode l1 = new MyListNode(4, node2);
        MyListNode node4 = new MyListNode(5);
        MyListNode l2 = new MyListNode(6, node4);
        MyListNode result = addTwoNumbers(l1, l2);
        while(result != null) {
            log.info("{}", result.val);
            result = result.next;
        }
    }

    private static MyListNode addTwoNumbers(MyListNode l1, MyListNode l2) {
        MyListNode pre = new MyListNode(0);
        MyListNode cur = pre;
        int jinwei = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + jinwei;

            jinwei = sum / 10;
            sum = sum % 10;
            cur.next = new MyListNode(sum);

            cur = cur.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (jinwei == 1) {
            cur.next = new MyListNode(jinwei);
        }
        return pre.next;
    }
}
