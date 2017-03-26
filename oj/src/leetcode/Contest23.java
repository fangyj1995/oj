package leetcode;

import java.util.Arrays;
import java.util.List;

public class Contest23 {
 public class TreeNode {
     int val;
	 TreeNode left;
	 TreeNode right;
	 TreeNode(int x) { val = x; }
 }
	public static void main(String[] args) {
		Contest23 run = new Contest23();
        run.str2tree("-4(2(3)(1))(6(5)(7))");
	}
    public TreeNode str2tree(String s) {
    	if(s=="") return null;
        return construct(s,0,s.length() - 1);
    }
    public TreeNode construct(String s, int begin ,int end){
    	if(begin > end) return null;
    	System.out.println(begin+" "+end); 
    	int scan = begin;
    	if(s.charAt(begin) == '-')
    		scan++;	
    	while(scan <= end && Character.isDigit(s.charAt(scan))){
    		scan++;
    	}  	
    	int val = Integer.parseInt(s.substring(begin,scan));
		TreeNode t = new TreeNode(val);
		
		int l = scan;//第一个左括号
		if(l <= end && s.charAt(l)== '('){
			int r = findright(s,l);
			t.left = construct(s,l+1,r-1);
			l = r+1;
			if(l <= end && s.charAt(l)== '(')
			{
				r = findright(s,l);
				t.right = construct(s,l+1,r-1);
			}
		}
    	return t;
    }
    private int findright(String s, int l) {
    	int cnt = 1;
		int i = l+1;
		while(cnt != 0){
			char c = s.charAt(i++);
			if(c =='(') cnt++;
			else if(c ==')') cnt--;
		}
		return --i;
	}
    
	public int findMinDifference(List<String> timePoints) {
    	int mod = 23*60+60;
    	int[] seconds = new int[timePoints.size()];
    	int cnt = 0 ;
    	for(String time:timePoints){
    		String[] t = time.split(":");
    		int m = Integer.parseInt(t[0]);
    		int s = Integer.parseInt(t[1]);
    		s += m*60;
    		seconds[cnt] = s;
    		cnt++;
    	}
    	Arrays.sort(seconds);
    	int min = seconds[timePoints.size()-1] - seconds[0];
    	min = Math.min(min, mod-min);
    	int last = seconds[0];
    	for(int i = 1 ; i < timePoints.size(); i++){
    		int diff = seconds[i] - last;  
    		diff = Math.min(diff, mod-diff);
    		min = Math.min(min, diff);
    		last = seconds[i];
    	}
    	return min;
    }
	
    public String reverseStr(String s, int k) {
    	int i = 0 ;
    	int j = i + 2*k - 1;
    	int len = s.length();
    	char[] str = s.toCharArray();
        while(j <= len-1){
        	reverse(str,i,j-k);
        	i = j+1;
        	j = i + 2*k - 1;
        }
        if(i + k - 1 < len - 1)
        	reverse(str,i,i+k-1);        	        
        else
        	reverse(str,i,len-1);
        return new String(str);
    }
    private void reverse(char[] str ,int s,int e){   	
    	int i = s ; int j = e;
    	while(i < j){
    		char temp = str[i];
    		str[i++] = str[j];
    		str[j--] = temp;
    	}
    }
}
