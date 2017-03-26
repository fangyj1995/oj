package leetcode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutation {
	public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res=new ArrayList<List<Integer>>();
        LinkedList<Integer> temp=new LinkedList<Integer>();
        Set<Integer> set=new HashSet<Integer> ();
        for(int i=0;i<nums.length;i++)
        {
            set.add(nums[i]);
        }
        back(res,temp,set);
        return res;
    }
    public static void back(List<List<Integer>> res,LinkedList<Integer> temp,Set<Integer> set){
        if(set.isEmpty())
        {
            List<Integer> list=new ArrayList<Integer>(temp);
            res.add(list);
            return;
        }
        for(Object o:set.toArray())
        {
            int i=(Integer)o;
        	System.out.println(i);
            temp.add(i);
            set.remove(i);
            back(res,temp,set);
            set.add(i);
            temp.removeLast();
        }
    }
    public static void main (String[] args){
    	int[] nums=new int[]{1,2,3};
    	permute(nums);
    }
}
