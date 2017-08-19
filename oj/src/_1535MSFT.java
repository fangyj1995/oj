import java.util.Arrays;
import java.util.Scanner;
/*
描述

Little Ho has a string S consisting of only lowercase letters.

All letters in S are distinct except for 'm', 's', 'f' and 't'. Which means the four magical letters 'm', 's', 'f' and 't' can show up at most twice, while other letters can show up at most once.

Now little Ho wants to change S into T by swapping a pair of letters in S.

He wants to know the minimum amounts of such swaps he needs to do so.

输入

The first line contains an integer N, indicating the number of test cases.

Each test case contains two lines. The first contains the string S and the second contains the string T.

It is guaranteed that changing S into T is possible.

For 30% of the data, 1 ≤ length(S) == length(T) ≤ 10.

For 100% of the data, 1 ≤ N ≤ 10, 1 ≤ length(S) == length(T) ≤ 30.

输出

For each test case output the minimum amounts of swaps needed to turn S into T on a separate line.
样例输入2
msra
asmr
fsmambfcs
mfsmbfcsa
样例输出2
6

 */
public class _1535MSFT {
	static Scanner in = new Scanner(System.in);
	static int n;
	public static void main(String[] args) {
		n = in.nextInt();
		for(int i = 0; i < n; i++) {
			char[] s = in.next().toCharArray();
			char[] t = in.next().toCharArray();
			System.out.println(steps(s,t));
		}
	}
	public static int id(char c) {
		if(c == 'm') return 26;
		if(c == 's') return 27;
		if(c == 'f') return 28;
		if(c == 't') return 29;
		return c-'a';
	}
	public static int steps(char[] s, char[] t) {
		int n = s.length;
		char[] magicals = new char[]{'m','s','f','t'};
		int[] ss = new int[n];
		int[] tt = new int[n];
		int[] cnt = new int[26];
		int[] map = new int[30];
		Arrays.fill(map, -1);
		for(int i = 0; i < n; i++) {
			if(cnt[t[i]-'a'] == 0) {
				map[t[i]-'a'] = i;
			}else {
				map[id(t[i])] = i;
			}
			tt[i] = i;
			cnt[t[i]-'a']++;
		}
		int[] index = new int[30];
		Arrays.fill(index, -1);
		for(int i = 0; i < n; i++) {
			if(index[s[i]-'a'] == -1) {
				ss[i] = map[s[i]-'a'];
				index[s[i]-'a'] = i;
			}
			else {
				ss[i] = map[id(s[i])];
				index[id(s[i])] = i;
			}			
		}
		return dfs(ss,index, magicals, 0);
	}
	public static int dfs(int[] s, int[] index, char[] magicals, int v) {
		if(v == 4) {
			return swaps(s);
		}
		int min = dfs(s,index,magicals,v+1);
		if(index[26+v] != -1) {
			int index1 = index[magicals[v] - 'a'];
			int index2 = index[id(magicals[v])];
			swap(s, index1, index2);
			min = Math.min(min, dfs(s,index,magicals,v+1));
			swap(s, index1, index2);
		}		
		return min;
	}
    public static int swaps(int[] s) {
    	int[] index = new int[s.length];
    	for(int i = 0; i < s.length; i++) {
    		index[s[i]] = i;
    	}
    	int cnt = 0;
    	int[] copy = s.clone();
    	for(int i = 0; i < copy.length; i++) {
    		if(copy[i] != i) {
    			int t = copy[i];
    			swap(copy, i, index[i]);
    			swap(index, t, i);
    			cnt++;
    		}
    	}
    	return cnt;
    }
	public static void swap(int[] s, int i, int j) {
		int t = s[i];
		s[i] = s[j];
		s[j] = t;
	}
}
