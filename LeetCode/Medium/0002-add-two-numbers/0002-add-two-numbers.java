/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
        int digit = 1;
        int plus = 0;
        ListNode result = new ListNode();
        ListNode node = result;


        while (l1 != null || l2 != null) {

            if (l1 == null) {
                int sum = l2.val + plus;
                if (sum < 10) {
                    node.val = sum;
                    plus = 0;
                }
                else {
                    node.val = sum - 10;
                    plus = 1;
                }
                l2 = l2.next;
            }
            else if (l2 == null) {
                int sum = l1.val + plus;
                if (sum < 10) {
                    node.val = sum;
                    plus = 0;
                }
                else {
                    node.val = sum - 10;
                    plus = 1;
                }
                l1 = l1.next;
            }
            else {
                int sum = l1.val + l2.val + plus;
                if (sum < 10) {
                    node.val = sum;
                    plus = 0;
                }
                else {
                    node.val = sum - 10;
                    plus = 1;
                }
                l1 = l1.next;
                l2 = l2.next;
            }

            if (l1 == null && l2 == null) {
                if (plus != 0) {
                    node.next = new ListNode();
                    node = node.next;
                    node.val = plus;
                }
                break;
            }

            node.next = new ListNode();
            node = node.next;
        }

        return result;
    }

}