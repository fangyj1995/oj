import java.util.HashMap;
import java.util.Scanner;

/*
 * 
#1534 : Array Partition
时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

Given an integer array A1, A2 ... AN, you are asked to split the array into three continuous parts: A1, A2 ... Ap | Ap+1, Ap+2, ... Aq | Aq+1, Aq+2, ... AN. 

Let S1, S2 and S3 denote the sums of each part:  

S1 = A1 + A2 + ... + Ap  

S2 = Ap+1 + Ap+2 + ... + Aq

S3 = Aq+1 + Aq+2 + ... + AN

A partition is acceptable if and only if the differences between S1, S2 and S3 (i.e. |S1 - S2|, |S2 - S3|, |S3 - S1|) are no more than 1.  

Can you tell how many different partitions are acceptable? 

输入

The first line contains an integer N.  

The second line contains N integers, A1, A2 ... AN.  

For 30% of the data, N ≤ 100  

For 70% of the data, N ≤ 1000  

For 100% of the data, N ≤ 100000, -1000000 ≤ Ai ≤ 1000000

输出

The number of acceptable partitions.

样例解释

The three acceptable partitions are:  

3 | 2 | 1 0 2  

3 | 2 1 | 0 2  

3 | 2 1 0 | 2
样例输入5
3 2 1 0 2
样例输出3

 */
public class ArrayPartition {
	static Scanner in = new Scanner(System.in);
	static int[] nums;
	static int n;
	static long sum;
	public static void main(String[] args) {
		n = in.nextInt();
		if(n < 3) {
			System.out.println(0);
			return;
		}
		nums = new int[n];
		for(int i = 0; i < n; i++) {
			nums[i] = in.nextInt();
			sum += nums[i];
		}
		long part = sum / 3;
		if(sum < 0) {
			part = (sum-3)/3;
		}
		long r = sum - part * 3;
		long res = 0;
		if(r == 0) 
			res = partition(part,part);
		else if(r == 1) 
			res = partition(part+1, part) + partition(part,part+1) + partition(part,part);
		else if(r == 2) 
			res = partition(part,part+1) + partition(part+1,part+1) + partition(part+1,part);
		System.out.println(res);
	}
	public static long partition(long a, long b) {
		HashMap<Long, Long> sumCntMap = new HashMap<>();
		long res = 0;
		long sum = 0;
		for(int i = 0; i < n-1; i++) {//has to be n-1
			sum += nums[i];
			if(sum == a+b) {
				Long cnt = sumCntMap.get(a);
				if(cnt != null )res += cnt;
			}
			Long cnt = sumCntMap.get(sum);
			sumCntMap.put(sum, cnt == null ? 1: cnt+1);
		}
		return res;
	}
}
