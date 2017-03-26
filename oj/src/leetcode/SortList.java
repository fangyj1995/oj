package leetcode;

public class SortList {
	public static ListNode sortList(ListNode head) {
		if(head==null||head.next==null) return head;
        return sort(head,null);
    }
    public static ListNode sort(ListNode head,ListNode tail){//tail是最后一个节点的next值	
    	if(head==tail||head.next==tail) return head;  //递归地终止条件     
        ListNode lhead=head, rhead=null, mid=head, pre=head, cur=head.next;       
        int val=mid.val; 
        boolean alreadysort=true;
        int lastval=pre.val;
        while(cur!=tail&&cur!=null){
            
         if(cur.val<lastval) alreadysort=false;
         lastval=cur.val;
         
         if(cur.val<val){//值小于
            pre.next=cur.next;
            cur.next=lhead;
            lhead=cur;
            cur=pre.next;
         }
         else {
        	 pre=cur;
        	 cur=cur.next;
         }
        
       } 
       if(alreadysort) return head;//对最坏情况的优化，如果已经有序，直接返回
       rhead=mid.next;  
       mid.next=sort(rhead,tail);
       return sort(lhead,head);
    }
    public static void printList(ListNode l){
    	while(l!=null){
			System.out.print(l.val+" ");
			l=l.next;
		}
		System.out.println();	
    }
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode begin=head,end=head;
        ListNode beginPre=new ListNode(0);
        beginPre.next=begin;
        for(int i=0;i<m-1;i++){
            beginPre=beginPre.next;
            begin=begin.next;
        } 
        for(int i=0;i<n-1;i++){
            end=end.next;
        }
        printList(head);
        ListNode endNext=end.next;
        end.next=null;
        beginPre.next=reverse(begin);
        begin.next=endNext;
        return beginPre.next;
     }
    public static ListNode reverse(ListNode l){
        if(l==null||l.next==null) return l;
        ListNode pre=l,cur=l.next;
        pre.next=null;
        while(cur!=null){
        	printList(l);
            ListNode next=cur.next;
            cur.next=pre;
            pre=cur;
            cur=next;
        }
        return pre;
    }
	public static void main(String[] args) {
		ListNode l=new ListNode(5);
		l.next=new ListNode(10);
		l.next.next=new ListNode(11);
		l.next.next.next=new ListNode(2);
		l.next.next.next.next=new ListNode(1);
		l.next.next.next.next.next=new ListNode(3);
		//printList(l);
		l=reverseBetween(l,1,2);
		printList(l);
	}

}
