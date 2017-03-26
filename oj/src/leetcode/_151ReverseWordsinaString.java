package leetcode;

public class _151ReverseWordsinaString {
	public static void main(String[] args){
		System.out.println(reverseWords("  hb b  bgf"));
	}
    public static String reverseWords(String s){
        char[] str = s.toCharArray();
        int l = 0;
        while(l < str.length && str[l] == ' ')
            l++;
        for(int r = l+1; r < str.length ; r++){
            if(str[r] == ' ' && str[r - 1] != ' '){
                reverse(str, l , r-1);
                l = r + 1;
                while(l < str.length && str[l] == ' ')
                    l++;  
                r = l;
            }
        }
        reverse(str, l , str.length - 1);
        reverse(str,0,str.length - 1); 
        System.out.println(str);
        return trim(str);
    }
    private static String trim(char[] str) {
        int index = 0;int i = 0;
        for(;i < str.length; ++i){    
        	if(str[i] != ' ' || (i > 0 && str[i - 1]!= ' '))//i is space
        		str[index++] = str[i];
        }
        if(index > 0 && str[index - 1] == ' ')
        	index--;
		return new String(str).substring(0,index);
	}
	public static void reverse(char[] s ,int i , int j){
        int l = i;
        int r = j;
        while(l <= r){
            char t = s[l];
            s[l++] = s[r];
            s[r--] = t;
        }
    }
}
