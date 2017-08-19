package leetcode;

import java.io.IOException;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if(head == null)	return head;
        ListNode next = head, end = head,cur = head;        
        for(int i = 0 ; i < k-1; i++){
            end = end.next;
            if(end == null)
                return head;
        }
        
        ListNode lastTail = cur;
        next = end.next;
        end.next = null;
        ListNode reverseHead = reverse(cur);
        
        while(next != null){
            cur = next;
            end = next;
            for(int i = 0 ; i < k-1; i++){
                end = end.next;
                if(end == null)    
                	break;
            }
            if(end == null) {
                lastTail.next = cur;
                break;
            }
            next = end.next;
            end.next = null;
         
            lastTail.next = reverse(cur);
            lastTail = cur;
        }
        
        return reverseHead;
    }

    public ListNode reverse(ListNode head){
        if(head == null || head.next == null)   return head;
        ListNode pre = head;
        ListNode cur = pre.next;
        pre.next = null;
        while(cur != null){
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
    
	public static void main(String[] args) throws IOException{	
		ReverseKGroup rkg = new ReverseKGroup();
		ListNode list = new ListNode(1);
		ListNode head = list;
		list.next = new ListNode(2);
		list = list.next;
		list.next = new ListNode(3);
		list = list.next;
		list.next = new ListNode(4);
		list = list.next;
		list.next = new ListNode(5);
		rkg.reverseKGroup(head, 2);
	}
}
