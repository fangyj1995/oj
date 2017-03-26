package hihocoder;
import java.io.*;
public class 二分归并排序求逆序对 {
	static StreamTokenizer in = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));  
	static PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));        
	public static int nextInt()throws IOException {in.nextToken();return (int)in.nval;}  
	public static double nextDouble()throws IOException {in.nextToken();return (double)in.nval;} 
	public static String next()throws IOException {in.nextToken();return (String)in.sval;} 
	
	static int n;
	static int[] nums;
	public static void main(String[] args) throws IOException{
		n = nextInt();
		nums = new int[n+1];
		for(int i = 1 ; i <= n ; i++){
			nums[i] = nextInt();
		}
		int[] aux = new int[n+1];
		long res = reverses(1,n,aux);
		System.out.println(res);
	}
	private static long reverses(int lo,int hi,int[] aux) {		
		if(lo >= hi) return 0;	
		int mid = lo + ((hi - lo)>>1);
		long leftNum = reverses(lo,mid,aux);
		long rightNum = reverses(mid+1,hi,aux);
		long cnt = leftNum+rightNum;
		if(nums[mid] <= nums[mid+1])//已经有序
			return cnt;	
		int i = lo;int j = mid+1;
		int index = lo;
		while(i <= mid && j <= hi && index <= hi){
			if(nums[i] <= nums[j]){
				cnt += (j-mid-1);
				aux[index++] = nums[i++];
			}
			else
				aux[index++] = nums[j++];
		}
		//左半边剩下的数和右半边所有的数都是逆序对
		while(i <= mid && index <= hi){
			cnt += (j-mid-1);
			aux[index++] = nums[i++];
		}
		while(j <= hi && index <= hi)
			aux[index++] = nums[j++];
		for(i = lo ; i <= hi; i++)
			nums[i] = aux[i];
		return cnt;
	}
}



