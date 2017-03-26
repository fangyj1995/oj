package hihocoder;
import java.util.*;

//#1038 : 01背包
public class Hihoweek6 {
	static int n , m;
	static int[] need;
	static int[] value;
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		n = scan.nextInt();
		m = scan.nextInt();
		need = new int[n];
		value = new int[n];
		for(int i = 0 ; i < n ; i++){
			need[i] = scan.nextInt();
			value[i] = scan.nextInt();
		}
		solve();
	}
	private static void solve() {
		int[] best = new int[m + 1];
		//best[0] = 0
		for(int i = 0 ; i < n; i++){			
			for(int j = m ;  j >= need[i] ; j--){
				// =号右边的best[j]此时保存的是i = i - 1时的数据			
				best[j] = Math.max(best[j],best[j - need[i]] + value[i]);
			}
		}
		System.out.println(best[m]);
	}
}
