import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class LuckySubStrings {
	private static int[] fi= new int[]{1,1,2,3,5,8,13,21};
	public static void main(String[] args) {
		String s = new Scanner(System.in).nextLine();
		Set<String> res = luckSubStrings(s);
		for(String sub: res){
			System.out.println(sub);
		}
	}
	public static boolean isFibonacci(int i){
		int lo = 0 ; int hi = fi.length - 1;
		while(lo <= hi){
			int mid = (lo + hi)/2;
			if(i < fi[mid]) hi= mid - 1;
			else if(i > fi[mid]) lo = mid + 1;
			else return true;
		}
		return false;
	}
	private static Set<String> luckSubStrings(String s) {
		Set<String> res = new TreeSet<String> (); 
		char[] str = s.toCharArray();
		for(int i = 0 ; i < s.length(); i++){
			int cnt = 0;
			int[] freq = new int[26];
			for(int j = i ; j < s.length(); j++){
				if(freq[str[j]-'a'] == 0){
					freq[str[j] - 'a']++;
					cnt++;
				}
				if(isFibonacci(cnt)) res.add(s.substring(i, j+1));
			}
		}
		return res;
	}
}
