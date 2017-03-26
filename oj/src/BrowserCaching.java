import java.io.*;

import java.text.ParseException;
import java.util.*;


public class BrowserCaching {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	private static class Item{
		Item pre;
		Item next;
		String url;
		public Item(Item pre, Item next, String url) {
			super();
			this.pre = pre;
			this.next = next;
			this.url = url;
		}
		
	}
	static Item head = null , tail = null;
	public static void main(String[] args) throws IOException, ParseException {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int size = scan.nextInt();
		scan.nextLine();
		int cnt = 0;
		Map<String,Item> map = new HashMap<>();
		for(int i = 0 ; i < n; i++){
			String url = scan.nextLine();
			if(map.containsKey(url)){//命中
				Item item = moveToFirst(map.get(url));
				map.put(url, item);
				System.out.println("Cache");
			}
			else{//未命中
				if(cnt == size){//满
					String del = deleteLast();
					map.remove(del);
				}
				else cnt++;						
				Item item = add(url);
				map.put(url, item);
				System.out.println("Internet");
			}
		}
	}
	private static Item add(String url) {//表头插入
		Item item = new Item(null,head,url);
		if(head == null){
			tail = item;
		}
		else
			head.pre = item;
		head = item;
		return item;
	}
	private static String deleteLast() {//删除表尾元素
		Item item = tail;
		if(tail.pre != null){
			tail.pre.next = null;
			tail = tail.pre;
		}
		else{
			head = tail = null;
		}
		return item.url;
	}
	private static Item moveToFirst(Item item) {//移到表头
		if(item == head) return head;
		if(item.pre != null) item.pre.next = item.next;
		if(item.next != null) item.next.pre = item.pre;
		if(item == tail) tail = item.pre;
		
		item.next = head;
		item.pre = null;
		head.pre = item;
		head = item;	
		return head;
	}
	
}
