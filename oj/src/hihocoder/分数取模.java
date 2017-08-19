package hihocoder;
import java.text.NumberFormat;
import java.util.*;

public class 分数取模 {
	static Scanner in = new Scanner(System.in);
	Thread t; 
	public static void main(String[] args) {
		long a = in.nextInt();
		long b = in.nextInt();
		long p = in.nextInt();
		long niyuan = euclid(b,p);
		long res = (a * niyuan) % p;
		System.out.println(res);
	}
	public static long euclid(long b, long p){ 
		if(p == 0) return 1;
		long _p = p;
		long x1, y1, t1, t2; 
	    x1 = 1;y1 = 0;      
	    while(p > 0){            
	        t1 = x1 - b/p*y1; x1 = y1; y1 = t1;      
	        t2 = b%p;   b = p;   p = t2;  
	    }  
	    return x1 < 0 ? x1 + _p : x1;
	}
	public static long[] _euclid(long b, long p) {// b * x + p * y = 1
		if(p == 0) {
			return new long[]{1,0};
		}
		long[] res = new long[2];
		long[] next = _euclid(p, b%p);
		res[0] = next[1];
		res[1] = (next[0] - b/p*next[1]);
		return res;
	}
}