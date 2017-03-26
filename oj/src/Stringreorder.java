import java.io.*;
import java.util.*;


public class Stringreorder {
	static int R = 128;
	public static void main(String[] args) throws IOException {
		Scanner scan = new Scanner(System.in);
		while(scan.hasNextLine())
		{
			String str  = scan.nextLine();
			System.out.println(reOrder(str));
		}		
	}
	private static String reOrder(String str) {
		int[] count = new int[R];
		for(int i = 0 ; i < str.length();i++){
			char c = str.charAt(i);
			if(illegal(c)) return "<invalid input string>";
			count[c - '0']++;
		}
		StringBuilder sb = new StringBuilder();
		boolean flag = true;
		while(flag){
			flag = false;
			for(int c = 0 ; c < R; c++){
				if(count[c] != 0){
					flag = true;
					sb.append((char)(c+'0'));
					count[c]--;
				}
			}
		}		
		return sb.toString();
	}
	private static boolean illegal(char c) {
		return (c < '0')||(c > 'z')||(c > '9' && c < 'a');
	}
	
}
