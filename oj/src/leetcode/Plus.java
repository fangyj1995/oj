package leetcode;
import java.util.*;

public class Plus{
	 public static void main (String[] args)
	 {
		 int nums[]={2,3,4};
		 int [] r=twoSum(nums,6);
		 System.out.println(Arrays.toString(r));
	 }
	    public static int[] twoSum(int[] numbers, int target) {
	        int[] r=new int[2];
	        int n=numbers.length;
	        for(int i=0;i<n;i++){
	            int k=target-numbers[i];
	            int index=binaryS(numbers,i+1,k);
	            if(index!=-1)
	            {
	                r[0]=i+1;
	                r[1]=index+1;
	                return r;
	            }
	        }
	        return r;
	    }
	    public static int binaryS(int[] nums,int lo,int k){
	       int hi=nums.length-1;
	       while(lo<=hi){	    	 
	            int mid=(lo+hi)/2;
	            System.out.println(lo+" "+mid+" "+hi);
	            if(k<nums[mid]) 
	                hi=mid-1;
	            else if(k>nums[mid]) 
	                lo=mid+1;
	            else
	                return mid;	            
	        }
	        return -1;
	    }
	 public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
	       Map<Integer,Integer> map=new HashMap<Integer,Integer>();
	       int t1=t;
	       if(t==0)
	         t=1;
	        for(int i=0;i<nums.length;i++)
	        {
	            int bucket=nums[i]/t;
	            boolean find=false;
	            System.out.println("Now the bucket is "+bucket);
	            if(map.containsKey(bucket)){
	            	 int diff1=i-map.get(bucket);int diff2=nums[i]-nums[map.get(bucket)];
	            	 System.out.println(diff1+"  "+diff2);
	                 if((i-map.get(bucket))<=k&&Math.abs((long)(nums[i]-nums[map.get(bucket)]))<=t1) 
	                  return true;
	            }   
	            if(map.containsKey(bucket+1)){
	            	 System.out.println(nums[i]-nums[map.get(bucket+1)]);
	                if((i-map.get(bucket+1))<=k&&Math.abs((long)(nums[i]-nums[map.get(bucket+1)]))<=t1) 
	                  return true;
	            }
	            if(map.containsKey(bucket-1)){	         
	              if((i-map.get(bucket-1))<=k&&Math.abs((long)(nums[i]-nums[map.get(bucket-1)]))<=t1) 
	                  return true;
	            }
	            map.put(bucket,i);
	            System.out.println("no found ,put nums["+i+"]"+nums[i]+" in bucket"+bucket);
	            System.out.println(i);
	            System.out.println("bucket 1 "+map.get(1));
	        }
	        return false;
	    }
}
