package leetcode;

public class AddTwoNumbersII {
	public static void main(String [] args){
		ListNode l1;
		l1=new ListNode(1);
		ListNode l2;
		l2=new ListNode(9);
		l2.next=new ListNode(9);

		ListNode r=addTwoNumbers(l1,l2);
		printList(r);
	}
	public static void printList(ListNode l){
		while(l!=null){
			System.out.print(l.val+" ");
			l=l.next;
		}
		System.out.println();
	}
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode r=new ListNode(0);
        l1=reverse(l1);
        l2=reverse(l2);
        while(l1!=null&&l2!=null){
              int sum=l1.val+l2.val+r.val;                        
              r=update(r,sum);
              l1=l1.next;l2=l2.next;              
        }
        while(l2!=null){ 
        	 int sum=r.val+l2.val;
        	 r=update(r,sum);
        	 l2=l2.next;
        }
        while(l1!=null){
        	int sum=r.val+l1.val;
        	r=update(r,sum);
        	l1=l1.next;
        }               
        return r;
    }
	public static ListNode update(ListNode l,int sum)
	{
		 int remain=sum%10;
         int forward=sum/10;             
         l.val=remain;
         if(forward>0) {l=insertBefore(l,forward);System.out.println("进一位");     }         
         else  l=insertBefore(l,0);
         return l;
	}
	public static ListNode insertBefore(ListNode r,int val ){
		ListNode newNode=new ListNode(val);
		newNode.next=r;
		r=newNode;
		return r;
	}
    public static ListNode reverse(ListNode l){
        if(l.next==null) return l;
        ListNode old=l; 
        l=l.next;
        old.next=null;      
        ListNode next=l.next;
        while(l!=null)
        {
        	ListNode oldtemp=old;
        	l.next=oldtemp;//!!
            old=l;//!!
            if(next==null) break;
            l=next;
            next=next.next;
            
        }
      return l;
    }
    public static int length(ListNode l){
        int count=0;
        ListNode h=l;
        while(h!=null){
            h=h.next;
            count++;
        }
        return count;
    }
}
 class ListNode {
	    int val;
	    ListNode next;
	    ListNode(int x) { val = x; }
	 }
