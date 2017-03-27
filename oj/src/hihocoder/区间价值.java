package hihocoder;

import java.io.*;
import java.text.ParseException;
import java.util.*;
/**
 * #1483 : 区间价值
	时间限制:10000ms
	单点时限:1000ms
	内存限制:256MB
	
	描述
	给定n个数A1...An，小Ho想了解AL..AR中有多少对元素值相同。小Ho把这个数目定义为区间[L,R]的价值，用v[L,R]表示。	
	例如1 1 1 2 2这五个数所组成的区间的价值为4。
	现在小Ho想知道在所有的的v[L,R](1 <= L <= R <= n)中，第k小的值是多少。
	
	输入
	第一行一个数T(T<=10)，表示数据组数。
	对于每一组数据：
	第一行两个数n，k（1<=n<=200,000,1<=k<=n*(n+1)/2）
	第二行n个数A1…An(1<=Ai<=1,000,000,000)
	
	输出
	一个数表示答案。
	
	样例输入
	2
	4 7
	1 1 2 3
	3 6
	100 100 100
	
	样例输出
	0
	3
 * @author fangyj
 *
 */
public class 区间价值 {
	static Scanner scan = new Scanner(System.in);	
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
		//map用来记录每个数出现的个数
		HashMap<Integer,Integer> map = new HashMap<>();
		int i = 1, j = 1;
		long pairs = 0,  ans = 0;
		while(j <= n){
			if(map.containsKey(nums[j])){
				int newPairs = map.get(nums[j]);
				pairs += newPairs;
				map.put(nums[j], newPairs+1);//nums[j]的个数加一
			}
			else	map.put(nums[j], 1);
			
			while(pairs > value){
				int delPairs = map.get(nums[i]);
				pairs -= (delPairs-1);
				map.put(nums[i],delPairs-1);//nums[i]的个数减一
				i++;
			}
			ans += (j-i+1);
			j++;
		}
		return ans;
	}
	
}













