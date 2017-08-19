package hihocoder;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
/**
 * 
 * @author fangyj
 *
时间限制:10000ms

单点时限:1000ms

内存限制:256MB


描述

给定一个{1..N}的排列A1, A2, ... AN，每一次操作可以将相邻的两个数一起移动（保持两个数相邻且前后顺序不变）到任意位置。询问至少经过多少次操作，可以使得原序列变为1, 2, ..., N。

例如对于54321，把32一起移动到最左端得到32541；再把25一起移动到最右端得到34125；再把12一起移动到最左端得到12345。

输入

第1行：1个正整数 T，表示输入数据的组数

第2..T+1行：每行一个字符串，表示初始排列

对于30%的数据：T = 1, 1 ≤ N ≤ 5

对于100%的数据：1 ≤ T ≤ 5, 1 ≤ N ≤ 8

输出

第1..T行：每行一个整数，第i行表示第i个排列变化为1, 2, ..., N所需要的最少步数。若无可行方案，输出"-1"。
样例输入2
54321
321
样例输出3
-1

 */
public class _1539数组重排3 {
	static Scanner in = new Scanner(System.in);
	static int n;
	static HashMap<String, Integer> map;
	public static void main(String[] args) {
		int t  = in.nextInt();
		in.nextLine();
		for(int i = 0; i < t; i++) {
			String nums = in.nextLine();
			n = nums.length();
			map = new HashMap<>();
			System.out.println(bfs(nums));
		}
	}
	public static int bfs(String nums) {
		Queue<String> q = new LinkedList<>();
		q.add(nums);
		map.put(nums, 0);
		while(!q.isEmpty()) {
			String s = q.poll();
			if(check(s.toCharArray())) return map.get(s);
			for(int v = 0; v+1 < n; v++) {
				for(int i = 0; i < v; i++) {
					String left = s.substring(0, i);
					String right = s.substring(i, v);
					String str = left + s.charAt(v)+s.charAt(v+1) + right + s.substring(v+2);
					if(!map.containsKey(str)) {
						q.add(str);
						map.put(str, map.get(s)+1);
					}
				}
				for(int i = v+3; i <= n; i++) {
					String left = s.substring(v+2,i);
					String right = s.substring(i);
					String str = s.substring(0,v)+left + s.charAt(v)+s.charAt(v+1)+ right;	
					if(!map.containsKey(str)) {
						q.add(str);
						map.put(str, map.get(s)+1);
					}
				}
			}
		}
		return -1;
	}
	public static boolean check(char[] nums) {
		for(int i = 0; i < n; i ++) {
			if(nums[i] != i+'0'+1)
				return false;
		}
		return true;
	}
}
