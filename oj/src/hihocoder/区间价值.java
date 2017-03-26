package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;

public class 区间价值 {
	static Scanner scan = new Scanner(System.in);
//	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
//	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
//	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
//	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
//	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int[] nums;
	public static void main(String[] args) throws IOException, ParseException {
		int cases = scan.nextInt() ;		
		for(int c = 0 ; c < cases ; c++){
			int n = scan.nextInt();
			long k = scan.nextLong();
			nums = new int[n+1];
			
			for(int i = 1; i <= n; i++){
				nums[i] = scan.nextInt();
			}
			
			long low = 0;
			long high = (long)(n+1)*(long)(n)/2 + 10;
			while(low < high){
				long mid = low + (high-low)/2;
				long cnt = count(n,mid);
				if(cnt < k){
					low = mid + 1;
				}
				else{
					high = mid;
				}
			}
			System.out.println(low);
		}
		
	}
	//价值小于等于value的区间的个数
	public static long count(int n, long value){
		HashMap<Integer,Integer> map = new HashMap<>();
		int i = 1;
		int j = 1;
		long pairs = 0;
		long ans = 0;
		//pairs表示j~i之间所有区间的价值
		for(; i <= n ; i++){
			int num = nums[i];
			if(map.containsKey(num)){
				int temp = map.get(num);
				pairs += temp;
				map.put(num, temp+1);
			}
			else 
				map.put(num, 1);
			//元素i的加入导致了区间价值大于value;
			//元素i的加入加入了i个区间，找出这些区间中价值大于value的个数，减去即可
			//这些区间一定是一元素i结尾的，以区间起点枚举个数
			while(pairs > value){//从头开始不断删除元素，直到价值等于value
				//temp 为没有[j,i]区间的话会减少的配对
				int temp = map.get(nums[j])-1;//删除一个nums[j]就会减少map.get(nums[j])-1个配对
				pairs -= temp;
				map.put(nums[j], temp);
				j++;
			}
			//每删除一个数nums[j]，就删除了j个区间
			//每加入一个数nums[i]，就加入了i个区间（它和前面i-1个数构成的区间，它自己单独作为一个区间），只有一个数的区间价值为0
			//如果j=1，即加入元素i后最大区间价值仍不超过value,则小于等于value的区间个数+i
			//(i-j+1)就是通过调整j,i发现的价值小于value的区间的个数
			ans += (i-j+1);//小于等于value的区间个数，新加入的区间中有j个是大于value的	
		}
		return ans;
	}
	
}













