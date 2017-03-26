package hihocoder;
import java.io.*;
import java.text.ParseException;
import java.util.*;

public class 修补木桶 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n,m,l;
	static int[] heights;
	public static void main(String[] args) throws IOException, ParseException {
	  	n = nextInt();
	  	m = nextInt();
	  	l = nextInt();
	  	heights = new int[n];
	  	int max = Integer.MIN_VALUE;
	  	for(int i = 0 ; i < n ; i++)
	  	{
	  		heights[i] = nextInt();
	  		max = Math.max(max, heights[i]);
	  	}
	  	int res = slove(max);
	  	System.out.println(res);
	}
	private static int slove(int max) {
		int lo = 1;
		int hi = max;		
		while(lo <= hi){
			int mid = lo+(hi-lo)/2;
			if(can(mid)) {
				lo = mid+1;
			}
			else{
				hi = mid-1;
			}
		}
		return hi;
	}
	private static boolean can(int h) {
		for(int start = 0 ; start < n; start++){
			int cur = start;
			int len = 0;
			int cnt = 0;
			while(len < n){
				if(heights[cur%n] < h){
					cur += l;
					len += l;
					cnt++;
				}
				else{ 
					cur++;
					len++;
				}
			}
			if(cnt <= m) return true;
		}
		return false;
	}


}

