package leetcode;

public class RemoveLinkedListElements {
	public static ListN removeElements(ListN head, int val) {
        if(head==null) return head;
        while(head!=null&&head.val==val)
          head=head.next;
        if(head==null) return head;
        ListN pre=head; 
        ListN l=pre.next;
        while(l!=null){
        	System.out.println(l.val+" "+l.next);
            while(l!=null&&l.val==val)
            {
               l=l.next;
            }            
            pre.next=l;
            pre=l;
            if(l==null) return head;
            l=l.next;
        }
        return head;
    }
	public static void main(String[] args) {
		ListN l=new ListN(2);
		l.next=new ListN(4);
		l.next.next=new ListN(3);
		l.next.next.next=new ListN(7);
		l.next.next.next.next=new ListN(2);
		l.next.next.next.next.next=new ListN(2);
		l.next.next.next.next.next.next=new ListN(2);
		l=removeElements(l,2);
		while(l!=null)
			{
			System.out.print(l.val);l=l.next;
			}
	}

}
class ListN {
	     int val;
	     ListN next;
	     ListN(int x) { val = x; }
}
