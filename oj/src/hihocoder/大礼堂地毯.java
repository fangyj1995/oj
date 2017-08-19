package hihocoder;

import java.util.Arrays;
import java.util.Scanner;

public class 大礼堂地毯 {
	static Scanner in = new Scanner(System.in);
	static int n,m,k, h, w;
	static char[][] basic;
	public static void main(String[] args) {
		n = in.nextInt();
		m = in.nextInt();
		k = in.nextInt();
		basic = new char[n][];
		for(int i = 0; i < n; i++) {
			basic[i] = in.next().toCharArray();
		}
		for(int i = 0; i < k; i++) {
			h = in.nextInt();
			w = in.nextInt();
			char[][] pic = new char[h][];
			for(int j = 0; j < h; j++)
				pic[j] = in.next().toCharArray();
			if(!isPart(basic, pic)) {
				System.out.println("NO");
			}
			else 
				System.out.println("YES");
		}
	}
	public static char[][] getCircle(char[][] pic) {
		if(h <= n && w <= m) return pic;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(pic[i][j] != pic[i%n][j%m])
					return null;
			}
		}
		char[][] circle = new char[Math.min(n,h)][Math.min(m,w)];
		for(int i = 0; i < Math.min(n,h); i++) {
			for(int j = 0; j < Math.min(m,w); j++) {
				circle[i][j] = pic[i][j];
			}
		}
		return circle;
	}
	public static boolean isPart(char[][] temp, char[][] pic) {
		char[][] circle = getCircle(pic);
		if(circle == null) return false;
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				if(isPart(temp, circle, i, j))
					return true;
			}
		}
		return false;
	}
	
	public static boolean isPart(char[][] temp, char[][] circle, int r, int c) {
		int h = circle.length;
		int w = circle[0].length;
		for(int i = 0; i < h; i++) {
			for(int j = 0; j < w; j++) {
				if(circle[i][j] != temp[(r+i)%n][(c+j)%m])
					return false;
			}
		}
		return true;
	}
}
