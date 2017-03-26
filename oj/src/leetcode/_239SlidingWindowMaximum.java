package leetcode;

import java.util.LinkedList;

public class _239SlidingWindowMaximum {
	public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums == null || nums.length < k || k < 1)    return new int[0];
        int[] max = new int[nums.length-k+1];
        int index = 0;
        LinkedList<Integer> queue = new LinkedList<Integer> ();
        for(int i = 0 ; i < nums.length ; i++){
            while(!queue.isEmpty() && nums[i] >= nums[queue.getLast()] ){
                queue.removeLast();
            }
            queue.offer(i);
            if(queue.peek() == i-k)
                queue.poll();
            if(i >= k-1){
                max[index++] = nums[queue.peek()];
            }
        }
        return max;
    }
}
