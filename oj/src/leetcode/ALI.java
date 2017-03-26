package leetcode;

public class ALI {
	static int L , R , M;
	public static void main(String[] args) {
		boolean found = split4(new int[]{2, 5, 1, 1, 1, 1, 4, 1, 7, 3, 7});
	}
	public static boolean split4(int[] a){		
		int l = 0 ;int r = a.length - 1;
		int lSum = a[l], rSum = a[r];
		while(l < r){
			if     (lSum > rSum) rSum += a[--r];
			else if(rSum > lSum) lSum += a[++l];			
			else break;
		}
		if(l == r || lSum != rSum) return false;
		l++;r--;
		lSum = a[++l]; rSum = a[--r];
		if(l == r) return false;
		while(l<=r){	
			if(lSum > rSum) rSum += a[--r];
			else if(rSum > lSum) lSum += a[++l];
			else{
				rSum += a[--r];
				lSum += a[++l];
			}
			if(lSum == rSum && l+2 == r) 
				return true;
		}
		
		return false;
	}
	private static boolean splitHalf(int[] a) {
		int lo = L, hi = R;
		int n = hi - lo + 1;
		int[] sum = new int[n];
		boolean [] marked = new boolean[n];
		sum[0] = a[lo];
		for(int i = 1 ; i < sum.length ; i++){
			sum[i] = a[lo + i] + sum[i - 1];
		} 
		int i = lo + (hi - lo)/2;
		int left = 1,right = -1;
		boolean found = false;
		while(i <= hi - 1 && i >= lo){
			int index = i - lo;
			if(marked[index]) break;
			left = sum[index - 1];
			right = sum[n - 1] - sum[index];
			marked[index] = true;
			if	   (left < right) i++;
			else if(left > right) i--;
			else{
				found = true;
				break;		
			}
		}
		if(found && i <= hi - 1 && i >= lo && left == right)
			M = i;
		return found;
	}
}
