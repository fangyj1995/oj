import java.math.BigInteger;
import java.util.Arrays;
import java.util.Scanner;

public class NumericKeyPad {
	static int[][] map = new int[][]{
		{0},
		{0,1,2,3,4,5,6,7,8,9},
		{0,2,3,5,6,8,9},
		{3,6,9},
		{0,4,5,6,7,8,9},
		{0,5,6,8,9},
		{6,9},
		{0,7,8,9},
		{0,8,9},
		{9}		
	};
	static boolean found;
	static StringBuilder sb;
	static String res;
	static String k;
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		scan.nextLine();
		for(int i = 0 ; i < n ; i++){
			k = scan.nextLine();
			sb = new StringBuilder();
			res = null;
			found = false;
			maxNum();
			System.out.println(res);			
		}
	}
	private static void maxNum() {
		int first = String.valueOf(k).charAt(0) - '0';
		for(int s = first; s >= 0 ; s--)
			dfs(true,0,s);
	}
	private static void dfs(boolean last,int d, int v) {
		if(found) return;
		if(d == k.length()) {
			found = true;
			res = sb.toString();
			return;			
		}
		char press = (char)(v + '0');
		if(last && press > k.charAt(d)) return;		
		sb.append(press);		
		last = last && (press == k.charAt(d));
		for(int w = map[v].length - 1 ; w >= 0 ; w--){
			dfs(last, d+1, map[v][w]);			
		}	
		sb.deleteCharAt(sb.length() - 1);
	}
	
}
