import java.io.*;
import java.util.*;


public class Fibonacci {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static String next()throws IOException {in.nextToken();return (String)in.sval;}       
	static int mod = 1000000007;
	static long cnt = 0;
	static int n;
	static class Item{
		long cnt;
		int last;
		public Item(long cnt, int last) {
			super();
			this.cnt = cnt;
			this.last = last;
		}
	}
	static Item[] map= new Item[200000];
	public static void main(String[] args) throws IOException {
		n = nextInt();
		int numOfOne = 0;
		for(int i = 0;i < n;i++){
			int m = nextInt();
			if(m == 1){
				cnt++;
				cnt = (cnt +(long)numOfOne)%mod;
				//添加numOfOne个等待2的项
				if(numOfOne != 0){
					if(map[2]!= null){
						Item item = map[2];
						item.cnt= (item.cnt+(long)numOfOne)%mod;
					}
					else{
						Item item = new Item(numOfOne,1);
						map[2] = item;
					}
				}
				numOfOne++;
			}
			else if(map[m] != null){
				Item waitm = map[m];//得到正在等待m的项的个数
				cnt =(cnt+waitm.cnt)%mod;								
				int next = waitm.last + m;//添加waitm.cnt个等待next的项
				if(map[next] != null){
					Item item = map[next];
					item.cnt= (item.cnt+waitm.cnt)%mod;
				}
				else{
					Item newItem = new Item(waitm.cnt,m);
					map[next]=newItem;
				}					
			}
		}
		System.out.println(cnt%mod);
	}
	
}
