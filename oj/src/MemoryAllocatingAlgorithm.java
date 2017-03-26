import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MemoryAllocatingAlgorithm {
	private static int n,totalSize;
	private static class Block implements Comparable{
		int data;
		int len;
		Block next;
		Block prev;
		public Block(int data, int len) {
			super();
			this.data = data;
			this.len = len;
		}
		public String toString(){
			String s =  "("+data +"," + len +")";
			if(prev != null ) s = "<-"+s;
			if(next != null) s = s+"->";
			return s;
		}
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			Block that = (Block) o;
			return this.data - that.data;
		}
	}

    public static void memory1(int[] sizes){
    	int data = 1;
    	Block memory = new Block(0,totalSize);  	
    	Block head = new Block(-1,0);
    	head.next = memory;
    	Block tail = new Block(-1,0);
    	tail.prev = memory;
    	memory.prev = head;
    	memory.next = tail;
    	
    	Block[] pos = new Block[n];
    	int delData = 1;
    	for(int size : sizes){
    		while(true){
    			Block empty = findEmpty(head, size);
    			if(empty == null){//没有可用的块
    				while(true){
    					Block delPos = pos[delData - 1];
    					delete(head, delPos, size);
        				delData++;
        				printInfo(head);
        				if(delPos.len >= size) break;
    				}
    			}
    			else{//将一个块拆成两个        			
    				empty.data = data;
    				pos[data - 1] = empty;
    				if(empty.len > size){
    					Block next = empty.next;
	    				Block b = new Block(0,empty.len - size);
	    				if(next != null)
	        				next.prev = b;
	    				
	    				empty.len = size;
	        				    				
	        			b.next = next;
	        			b.prev = empty;
	        			empty.next = b;     			
	        			
    				}
    				break;
    			}			
    		} 
    		//printInfo(head);
    		data++;    	
    	} 	
    	print(head);		
    }
    
    private static void print(Block head) {
    	Block p = head.next;
		PriorityQueue<Block> queue = new PriorityQueue();
		int[] start = new int[n];
		int s = 0;
    	while(p != null && p.data != -1){
    		if(p.data == 0) {
    			s += p.len;
    			p = p.next;    			
    			continue;
    		}
    		start[p.data - 1] = s;
    		s += p.len;
    		queue.add(p);
    		p = p.next;
    	}
    	while(!queue.isEmpty()){
    		Block b = queue.poll();
    		System.out.println(b.data +" "+ start[b.data - 1]);
    	}
	}
	private static void delete(Block head,  Block delPos , int size) {
		//System.out.println("delete  " + delPos.data);		
		int space = delPos.len;
		if(delPos.prev != null && delPos.prev.data == 0){
			space += delPos.prev.len;
			delPos.prev = delPos.prev.prev;
			delPos.prev.next = delPos;
		}	
		if(delPos.next != null && delPos.next.data == 0){
			space += delPos.next.len;
			delPos.next = delPos.next.next;	
			delPos.next.prev = delPos;
		}
		
		delPos.data = 0;
		delPos.len = space;
		
	}
	private static Block findEmpty(Block head, int size) {
		Block p = head;
		while(p != null){
			if(p.data == 0 && p.len >= size) return p;
			p = p.next;
		}
		return null;
	}
	private static void printInfo(Block head){
		Block p = head;
		while(p != null){
			System.out.print(p);
			p = p.next;
		}
		System.out.println();
	}
    public static void main(String[] args){
    	Scanner scan = new Scanner(System.in);    	
    	n = scan.nextInt();
    	totalSize = scan.nextInt();
    	int[] sizes = new int[n];
    	for(int i = 0; i < n ; i++){
    		sizes[i] = scan.nextInt();
    	}
    	
        memory1(sizes);
    }
}
