import java.util.Scanner;

/**
 * 模板：
 * 找第一个
 * while(lo <= hi){
 * 	if(满足条件||右边全部不满足条件)
 * 		hi = mid-1;//继续往左
 * 	else
 * 		lo = mid+1;//继续往右
 * }
 * return lo <= n-1 && a[lo]满足条件? lo : -1;
 * 找最后一个
 * while(lo <= hi){
 * 	if(满足条件||左边全部不满足条件)
 * 		lo = mid+1;//继续往右
 * 	else
 * 		hi = mid-1;//继续往左
 *  }
 *  return hi>=0 && a[hi]满足条件? hi : -1;
 *  
 * @author fangyj
 *
 */
public class BinarySearch {
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int[] a = new int[n];
		for(int i = 0 ; i < n ; i++){
			a[i] = scan.nextInt();
		}
		int k = scan.nextInt();
		BinarySearch bs = new BinarySearch();
		System.out.println("random pos:"+bs.binarySearch(a, k));
		System.out.println("first equal pos:"+bs.binarySearchFirstEqual(a, k));
		System.out.println("first equal or greater pos:"+bs.binarySearchFirstEqualOrGreater(a, k));
		System.out.println("first equal or less pos:"+bs.binarySearchFirstEqualOrLess(a, k));
		System.out.println("last equal pos:"+bs.binarySearchLastEqual(a, k));
		scan.close();
	}
	
	//查找某个数，返回任意一个,不存在返回-1
	public int binarySearch(int[] a ,int k){
		int lo = 0 ,hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(k < a[mid]){
				hi = mid-1;
			}
			else if(a[mid] < k){
				lo = mid+1;
			}
			else return mid;
		}
		return -1;
	}
	//查找某个数，返回第一个位置的下标,不存在返回-1
	public int binarySearchFirstEqual(int[] a ,int k){
		int lo = 0 ,hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(k <= a[mid]){//mid满足了等于k的条件，但由于要找第一个，还是继续往左半边找
				hi = mid-1;
			}
			else if(a[mid] < k){
				lo = mid+1;
			}
		}
		return lo <= a.length-1 && a[lo] == k ? lo : -1;
	}	
	//查找某个数k，返回第一个大于等于k的下标
	public int binarySearchFirstEqualOrGreater(int[] a ,int k){
		int lo = 0 ,hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(k <= a[mid]){//mid满足了大于等于k的条件，但由于要找第一个，还是继续往左半边找
				hi = mid-1;
			}
			else if(a[mid] < k){
				lo = mid+1;
			}
		}
		return lo <= a.length-1 && a[lo] >= k ? lo : -1;
	}
	//查找某个数k，返回第一个小于等于k的下标
	public int binarySearchFirstEqualOrLess(int[] a ,int k){
		int lo = 0 ,hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(k < a[mid]){//mid不满足条件
				lo = mid+1;
			}
			else if(a[mid] <= k){//mid满足了小于等于k的条件，但由于要找第一个，还是继续往左边找
				hi = mid-1;
			}
		}
		return lo <= a.length-1 && a[lo] <= k ? lo : -1;
	}
	//查找某个数k，返回第一个小于k的下标
	public int binarySearchFirstLess(int[] a ,int k){
		int lo = 0 ,hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(k <= a[mid]){//mid不满足条件
				lo = mid+1;
			}
			else if(a[mid] < k){//mid满足了小于等于k的条件，但由于要找第一个，还是继续往左边找
				hi = mid-1;
			}
		}
		return lo <= a.length-1 && a[lo] < k ? lo : -1;
	}
	
	//查找某个数，返回最后一个位置的下标,不存在返回-1
	public int binarySearchLastEqual(int[] a ,int k){
		int lo = 0 ,hi = a.length-1;
		while(lo <= hi){
			int mid = lo + (hi-lo)/2;
			if(k < a[mid]){
				hi = mid-1;
			}
			else if(a[mid] <= k){//mid满足了等于k的条件，但由于要找最后一个，还是继续往右半边找
				lo = mid+1;
			}
		}
		return hi >= 0 && a[hi] == k ? hi : -1;
	}
	//其他略
}
