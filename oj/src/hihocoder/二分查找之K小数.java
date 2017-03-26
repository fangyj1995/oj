package hihocoder;


import java.io.*;
import java.text.ParseException;
import java.util.*;


public class 二分查找之K小数 {
//	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int k;
	static int[] nums;
	public static void main(String[] args) throws IOException{
		n = nextInt();
		k = nextInt();
		nums = new int[n];
		for(int i = 0 ; i < n ; i++){
			nums[i] = nextInt();
		}
		int lo = 0;
		int hi = n-1;
		k--;
		while(lo <= hi){
			//返回切分元素的索引
			//数组左边都小于等于切分元素，右边都大于切分元素
			int index = partition(lo,hi);//把数组切分成[lo,rank-1]<=rank<=[rank+1,hi]
			//与切分元素比较
			if(k > index){
				lo = index + 1;
			}
			else if(k < index){
				hi = index - 1;
			}
			else{
				System.out.println(nums[index]);
				return;
			}
		}
		System.out.println(-1);
	}
	private static int partition(int lo, int hi) {
		int v = nums[lo];
		int i = lo;
		int j = hi+1;
		while(i < j){
			while(i<hi && nums[++i] < v);
			while(j>lo && nums[--j] > v);
			
			if(i >= j) break;
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;		
		}		
		nums[lo] = nums[j]; 
		nums[j] = v;
		return j;
	}

}



