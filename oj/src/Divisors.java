import java.util.Scanner;

public class Divisors {
	private static final int[] primes = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41};
	private static long n;
    private static int max;
    private static long res;
    
    public static long divisors(long n){
    	if(n == 1) return 1;
    	Divisors.n = n;
        dfs(0,Integer.MAX_VALUE,1, 1);
        return res;
    }
    private static void dfs(int curP,  int maxN, long product, int lastNum){
        if(curP > primes.length - 1) return;       
        int i = 1;
        while(i <= maxN){
        	product *= primes[curP];
        	if(product > n)	return;
            int num = lastNum * (i + 1);             
            if(num > max || (num == max && product < res)){
                max = num;
                res = product;
            }
            dfs(curP + 1, i, product, num);
            i++;
        }           
    }
    public static void main (String[] args){
        long n = new Scanner(System.in).nextLong();
        long res = divisors(n);
        System.out.println(res);
    }
}
